package com.bbdt.bluetoothbicyclediagnostics.activities;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.RideListDialog;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;
import com.bbdt.bluetoothbicyclediagnostics.serializable.RideData;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;

public class RidingHistoryActivity extends Activity {
	private static final String RIDE_LIST_DIALOG = "Ride List";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riding_history);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		FragmentManager manager = this.getFragmentManager();
		new RideListDialog().show(manager, RIDE_LIST_DIALOG);
	}
	
	public void selectRideClick(View view){
		Button button = (Button)view;
		FragmentManager manager = this.getFragmentManager();
		RideListDialog dialog = (RideListDialog)manager.findFragmentByTag(RIDE_LIST_DIALOG);
		manager.popBackStack();
		dialog.dismiss();
		
		RideData data = FileHandler.getRideData(button.getText().toString(), this);
		displayData(data);
	}
	
	private void displayData(RideData data){
		GraphData[] graphData = new GraphData[data.rotationData.times.size()];
		
		for(int i = 0; i < graphData.length; i++){
			graphData[i] = new GraphData(data.rotationData.times.get(i), data.rotationData.distances.get(i));
		}
		
		GraphViewSeries exampleSeries = new GraphViewSeries(graphData);
		GraphView graphView = new LineGraphView(
			    this // context
			    , "GraphViewDemo" // heading
			);
		graphView.addSeries(exampleSeries); // data
			 
		LinearLayout layout = (LinearLayout) findViewById(R.id.root);
			layout.addView(graphView);
	}
	
	private class GraphData implements GraphViewDataInterface{
		private double x;
		private double y;
		
		private GraphData(double x, double y){
			this.x = x;
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