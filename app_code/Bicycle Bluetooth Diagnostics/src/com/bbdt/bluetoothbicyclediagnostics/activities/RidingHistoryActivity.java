/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.RideListDialog;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;
import com.bbdt.bluetoothbicyclediagnostics.serializable.RideData;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * Activity to monitor the riding history within the Bicycle Bluetooth Diagnostics module app
 *
 */
public class RidingHistoryActivity extends Activity {
	private static final String RIDE_LIST_DIALOG = "Ride List";
	
	/**
	 * On creation of instance, create ride history monitoring
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riding_history);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		FragmentManager manager = this.getFragmentManager();
		new RideListDialog().show(manager, RIDE_LIST_DIALOG);
	}
	
	/**
	 * Retrieves data for specified ride
	 * @param view
	 */
	public void selectRideClick(View view){
		Button button = (Button)view;
		FragmentManager manager = this.getFragmentManager();
		RideListDialog dialog = (RideListDialog)manager.findFragmentByTag(RIDE_LIST_DIALOG);
		manager.popBackStack();
		dialog.dismiss();
		
		RideData data = FileHandler.getRideData(button.getText().toString(), this);
		displayData(data);
	}
	
	/**
	 * Initiates graph of data for ride
	 * @param data
	 */
	private void displayData(RideData data){
		// get the root view of the layout
		LinearLayout graphRoot = (LinearLayout) findViewById(R.id.graph_root);
		graphRoot.removeAllViews();
		
		// the layout paramters for the views
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)this.getResources().getDimension(R.dimen.graph_height));
		
		
		
		// initialize arrays to hold data
		GraphData[] distData = new GraphData[data.rotationData.times.size()];
		GraphData[] speedData = new GraphData[data.rotationData.times.size()];
		GraphData[] rpmData = new GraphData[data.rotationData.times.size()];
		GraphData[] heartData = new GraphData[data.heartRateData.times.size()];
		GraphData[] pressureData = new GraphData[data.pressureData.times.size()];
		GraphData[] gradientData = new GraphData[data.gradientData.times.size()];
		
		
		Log.e("SIZE", "S " + heartData.length);
		// store rot data
		for(int i = 0; i < distData.length; i++){
			distData[i] = new GraphData(data.startTime, data.rotationData.times.get(i), data.rotationData.distances.get(i));
			speedData[i] = new GraphData(data.startTime, data.rotationData.times.get(i), data.rotationData.speeds.get(i));
			rpmData[i] = new GraphData(data.startTime, data.rotationData.times.get(i), data.rotationData.rpmData.get(i));
		}
		
		// store heart data
		for(int i = 0; i < heartData.length; i++){
			heartData[i] = new GraphData(data.startTime, data.heartRateData.times.get(i), data.heartRateData.heartRates.get(i));
		}

		// store pressure data
		for(int i = 0; i < pressureData.length; i++){
			pressureData[i] = new GraphData(data.startTime, data.pressureData.times.get(i), data.pressureData.values.get(i));
		}
		
		// store gradient data
		for(int i = 0; i < gradientData.length; i++){
			gradientData[i] = new GraphData(data.startTime, data.gradientData.times.get(i), data.gradientData.values.get(i));
		}
		
		// Create data series
		GraphViewSeries distSeries = new GraphViewSeries(distData);
		GraphViewSeries speedSeries = new GraphViewSeries(speedData);
		GraphViewSeries rpmSeries = new GraphViewSeries(rpmData);
		GraphViewSeries heartSeries = new GraphViewSeries(heartData);
		GraphViewSeries pressureSeries = new GraphViewSeries(pressureData);
		GraphViewSeries gradientSeries = new GraphViewSeries(gradientData);
				
		// create views and init titles
		GraphView distView = new LineGraphView(this , "Distance vs Time");
		GraphView speedView = new LineGraphView(this , "Speed vs Time");
		GraphView rpmView = new LineGraphView(this , "RPM vs Time");
		GraphView heartView = new LineGraphView(this , "Heart Rate vs Time");
		GraphView pressureView = new LineGraphView(this , "Pressure vs Time");
		GraphView gradientView = new LineGraphView(this , "Gradient vs Time");
		
		//set the layout parameters
		distView.setLayoutParams(params);
		speedView.setLayoutParams(params);
		rpmView.setLayoutParams(params);
		heartView.setLayoutParams(params);
		pressureView.setLayoutParams(params);
		gradientView.setLayoutParams(params);
		
		// add series to a view
		distView.addSeries(distSeries);
		speedView.addSeries(speedSeries);
		rpmView.addSeries(rpmSeries);
		heartView.addSeries(heartSeries);
		pressureView.addSeries(pressureSeries);
		gradientView.addSeries(gradientSeries);

		// add views to the root view
		graphRoot.addView(distView);
		graphRoot.addView(speedView);
		graphRoot.addView(rpmView);
		graphRoot.addView(heartView);
		graphRoot.addView(pressureView);
		graphRoot.addView(gradientView);
		
		graphRoot.setLayoutParams(graphRoot.getLayoutParams());
	}
	
	private class GraphData implements GraphViewDataInterface{
		private double x;
		private double y;
		
		private GraphData(long startTime, double x, double y){
			this.x = x/1000.0;
			this.y = y;
		}
		
		@Override
		public double getX() {
			// TODO Auto-generated method stub
			return x;
		}

		@Override
		public double getY() {
			// TODO Auto-generated method stub
			return y;
		}
	}
}