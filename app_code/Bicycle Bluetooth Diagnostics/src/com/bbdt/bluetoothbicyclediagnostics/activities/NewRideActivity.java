package com.bbdt.bluetoothbicyclediagnostics.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.bluno.BlunoLibrary;
import com.bbdt.bluetoothbicyclediagnostics.bluno.BlunoLibrary.connectionStateEnum;

public class NewRideActivity extends BlunoLibrary{	
	private static final char TAG_SUFFIX 			= 'E'; 
	private static final String TAG_ROTATION 		= "ROT";
	private static final String TAG_HEART 			= "HRT";
	private static final String TAG_PRESSURE		= "PRS";
	private static final String TAG_ACCELEROMETER 	= "ACC";
	
	private static final int HEART_SAMPLE_RATE = 10;
	private int heartSamples = 0;
	
	private final long START_TIME;
	
	private SensorType sensorType = SensorType.end;
	
	private double distanceIncrement = 20;
	
	private RotationMap rotationData = new RotationMap();
	private HeartRateMap heartRateData = new HeartRateMap();
	private ListMap pressureData = new ListMap();
	private ListMap gradientData = new ListMap();
	
	private class RotationMap{
		ArrayList<Long> times = new ArrayList<Long>();
		ArrayList<Double> distances = new ArrayList<Double>();
		ArrayList<Double> rpmData = new ArrayList<Double>();
		ArrayList<Double> speeds = new ArrayList<Double>();
	}
	
	private class HeartRateMap{
		ArrayList<Long> times = new ArrayList<Long>();
		ArrayList<Long> sampleTimes = new ArrayList<Long>();
		ArrayList<Double> heartRates = new ArrayList<Double>();
	}
	
	private class ListMap{
		ArrayList<Long> times = new ArrayList<Long>();
		ArrayList<Double> values = new ArrayList<Double>();
	}

	private double calculatePressure(double value){
		return ((value * 4.9) - 200) / 44.1;
	}
	
	private enum SensorType{
		rotation,
		heart,
		pressure,
		accelerometer,
		end
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
	
	@Override
	public void onSerialReceived(String data) {
		long time = START_TIME - System.currentTimeMillis();
		Log.e("Data!", data);
		
		if(data == null || data.length() == 0){
			return;
		}
		
		if(sensorType == SensorType.end){
			if(data.equals("TAG_ROTATION")){
				sensorType = SensorType.rotation;			
			}
			else if(data.equals("TAG_HEART")){
				sensorType = SensorType.heart;
			}
			else if(data.equals("TAG_PRESSURE")){
				sensorType = SensorType.pressure;
			}
			else if(data.equals("TAG_ACCELEROMETER")){
				sensorType = SensorType.accelerometer;
			}
			else{
				Log.e("Data Error: ", "Expected tag, but recieved this \"" + data +"\"");
			}
		}
		else if(data.charAt(data.length() - 1) == TAG_SUFFIX){			
			String endTag = (new StringBuilder(data)).deleteCharAt(data.length() - 1).toString();
			if((endTag.equals("TAG_ROTATION") && sensorType == SensorType.rotation)
					|| (endTag.equals("TAG_HEART") && sensorType == SensorType.heart)
					|| (endTag.equals("TAG_PRESSURE") && sensorType == SensorType.pressure)
					|| (endTag.equals("TAG_ACCELEROMETER") && sensorType == SensorType.accelerometer)){
				sensorType = SensorType.end;
			}
			else{
				Log.e("Data Error: ", "Received wrong end tag. Expected \"" + sensorType + "\" but received: \"" + data + "\"");
			}
		}
		else{			
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
					break;
				case accelerometer:
					
					break;
				case end:
					Log.e("Data Error: ", "Data received without previous tag.");
					break;
				}
			}catch(NumberFormatException e){
				Log.e("Parse Error", "");
			}
		}
	}
	
	private double millisToMinutes(long millisTime){
		return (millisTime / 1000.0) / 60.0;
	}
	
	public void scan(View view){
       
	}
}