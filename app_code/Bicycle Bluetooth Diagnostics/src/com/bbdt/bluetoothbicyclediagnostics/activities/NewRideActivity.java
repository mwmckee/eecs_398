/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.activities;

import java.util.ArrayList;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.bluno.BlunoLibrary;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.ConfirmEndRideDialog;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;
import com.bbdt.bluetoothbicyclediagnostics.serializable.HeartRateMap;
import com.bbdt.bluetoothbicyclediagnostics.serializable.ListMap;
import com.bbdt.bluetoothbicyclediagnostics.serializable.RideData;
import com.bbdt.bluetoothbicyclediagnostics.serializable.RotationMap;

public class NewRideActivity extends BlunoLibrary{	
	private static final char TAG_SUFFIX 			= 'E'; 
	private static final String TAG_ROTATION 		= "ROT";
	private static final String TAG_HEART 			= "HRT";
	private static final String TAG_PRESSURE		= "PRS";
	private static final String TAG_ACCELEROMETER 	= "ACC";
	
	private static final String CONFIRM_END_RIDE_TAG = "Confirm End Ride";
	
	private static final int HEART_SAMPLE_RATE = 10;
	private int heartSamples = 0;
	
	private final long START_TIME;
	
	private SensorType sensorType = SensorType.rotation;
	
	private double distanceIncrement = 20;
	
	private RotationMap rotationData = new RotationMap();
	private HeartRateMap heartRateData = new HeartRateMap();
	private ListMap pressureData = new ListMap();
	private ListMap gradientData = new ListMap();	

	private double calculatePressure(double value){
		return ((value * 4.9) - 200) / 44.1;
	}
	
	private enum Step{
		getTag,
		getData,
		getData2,
		getEndTag
	}
	
	private Step step = Step.getTag;
	
	private enum SensorType{
		rotation,
		heart,
		pressure,
		accelerometer
	}
	
	public NewRideActivity(){
		START_TIME = System.currentTimeMillis();
		
		rotationData.times.add(START_TIME);
		rotationData.distances.add(0.0);
		rotationData.rpmData.add(0.0);
		rotationData.speeds.add(0.0);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_ride);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		onCreateProcess();														//onCreate Process by BlunoLibrary
        
        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200
        
        buttonScanOnClickProcess();
	}

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();														//onResume Process by BlunoLibrary
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }
	
	protected void onStop() {
		super.onStop();
		onStopProcess();														//onStop Process by BlunoLibrary
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(
			connectionStateEnum theconnectionStateEnum) {
		// TODO Auto-generated method stub
	}
	
	private StringBuilder dataBuffer = new StringBuilder("");
	public void onSerialReceived(String data){
		dataBuffer.append(data);
		
		StringBuilder nextData = new StringBuilder("");
		for(int i = 0; i < dataBuffer.length(); i++){
		 	if(dataBuffer.charAt(i) == '\r'){
		 		processData(nextData.toString());
		 		nextData = new StringBuilder("");
		 	}
		 	else if(dataBuffer.charAt(i) == '\n'){
		 		// do nothing to skip new line
		 	}
		 	else{
		 		nextData.append(dataBuffer.charAt(i));
		 	}
		}		
		dataBuffer = new StringBuilder(nextData.toString());
	}
	
	public void processData(String data) {
		long time = START_TIME - System.currentTimeMillis();
		Log.e("Data!", new String(data));
		
		if(data == null || data.length() == 0){
			return;
		}
		
		switch(step){
			case getTag:
				step = Step.getData;

				if(data.equals(TAG_ROTATION)){
					sensorType = SensorType.rotation;			
				}
				else if(data.equals(TAG_HEART)){
					sensorType = SensorType.heart;
				}
				else if(data.equals(TAG_PRESSURE)){
					sensorType = SensorType.pressure;
				}
				else if(data.equals(TAG_ACCELEROMETER)){
					sensorType = SensorType.accelerometer;
				}
				else{
					step = Step.getTag;
					Log.e("Data Error: ", "Expected tag, but recieved this \"" + new String(data) +"\"");
				}
				break;
			case getData:
				step = Step.getEndTag;
				try{
					int intData = Integer.parseInt(data);
					switch(sensorType){
					case rotation:
						if(intData == 0){
							ArrayList<Long> times = rotationData.times;
							ArrayList<Double> distances = rotationData.distances;
							ArrayList<Double> rpmData = rotationData.rpmData;
							ArrayList<Double> speeds = rotationData.speeds;
							
							times.add(time);
							distances.add(distances.get(distances.size() - 1) + distanceIncrement);
							rpmData.add(1.0/millisToMinutes(time - times.get(times.size() - 1)));
							double distance1 = distances.get(distances.size() - 1);
							double distance2 = distances.get(distances.size() - 2);
							long time1 = times.get(times.size() - 1);
							long time2 = times.get(times.size() - 2);
							speeds.add((distance1 - distance2)/((double)(time1 - time2)));
						}
						break;
					case heart:
						
						break;
					case pressure:
						pressureData.times.add(time);
						pressureData.values.add(calculatePressure(intData));
						Log.e("Pressure", "Value: " + calculatePressure(intData));
						break;
					case accelerometer:
						step = Step.getData2;
						break;
					}
				}catch(NumberFormatException e){
					step = Step.getData;
					Log.e("Parse Error", "Number Format Exception: did not get good data");
				}
				break;
			case getData2:
				step = Step.getEndTag;
				try{
					
				}catch(NumberFormatException e){
					step = Step.getData2;
					Log.e("Parse Error", "Number Format Exception: did not get good data");
				}
				break;
			case getEndTag:
				if(data.charAt(data.length() - 1) == TAG_SUFFIX){			
					String endTag = (new StringBuilder(data)).deleteCharAt(data.length() - 1).toString();
					if((endTag.equals(TAG_ROTATION) && sensorType == SensorType.rotation)
							|| (endTag.equals(TAG_HEART) && sensorType == SensorType.heart)
							|| (endTag.equals(TAG_PRESSURE) && sensorType == SensorType.pressure)
							|| (endTag.equals(TAG_ACCELEROMETER) && sensorType == SensorType.accelerometer)){
						step = Step.getTag;
					}
					else{
						step = Step.getEndTag;
						Log.e("Data Error: ", "Received wrong end tag. Expected \"" + sensorType + "\" but received: \"" + new String(data) + "\"");
					}
				}
				else{
					step = Step.getEndTag;
					Log.e("Data Error: ", "Received wrong end tag. Expected \"" + sensorType + "\" but received: \"" + new String(data) + "\"");
				}
				break;
		}
	}
	
	private double millisToMinutes(long millisTime){
		return (millisTime / 1000.0) / 60.0;
	}
	
	public void scan(View view){
       
	}
	
	public void endRideClick(View view){
		endRide();
	}
	
	private void endRide(){
		FragmentManager manager = this.getFragmentManager();
		manager.popBackStack();
		new ConfirmEndRideDialog().show(manager, CONFIRM_END_RIDE_TAG);
	}
	
	public void yesClick(View view){
		FileHandler.saveRide(new RideData(rotationData, heartRateData, pressureData, gradientData), this);
		finish();
	}
	
	public void noClick(View view){
		FragmentManager manager = this.getFragmentManager();
		ConfirmEndRideDialog dialog = (ConfirmEndRideDialog)manager.findFragmentByTag(CONFIRM_END_RIDE_TAG);
		dialog.dismiss();
	}
	
	public void onBackPressed() {
		endRide();
//		Intent setIntent = new Intent(Intent.ACTION_MAIN);
//		setIntent.addCategory(Intent.CATEGORY_HOME);
//		setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(setIntent);
		
	}

}