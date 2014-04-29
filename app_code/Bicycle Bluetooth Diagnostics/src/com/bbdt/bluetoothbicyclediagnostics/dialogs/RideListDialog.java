package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.adapters.RidesListAdapter;

public class RideListDialog extends DialogFragment {		
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_rides_list, null));
		
		return builder.create();
	}
	
	public void onStart(){
		super.onStart();
		ListView ridesList = (ListView)this.getDialog().findViewById(R.id.rides_list);
		ridesList.setAdapter(new RidesListAdapter(getActivity()));
	}
}