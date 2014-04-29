package com.bbdt.bluetoothbicyclediagnostics.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;
import com.bbdt.bluetoothbicyclediagnostics.serializable.RideData;

public class RidesListAdapter extends BaseAdapter{
	private Activity activity;
	private ArrayList<RideData> rides;
	
	public RidesListAdapter(Activity activity){
		rides = FileHandler.getRides(activity);
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return rides.size();
	}

	@Override
	public Object getItem(int index) {
		return rides.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		Button button = (Button)view;
		
		if(view == null){
			button = (Button)activity.getLayoutInflater().inflate(R.layout.list_item_ride, null);
			button.setText(rides.get(index).getLabel());
		}
		
		return button;
	}
}
