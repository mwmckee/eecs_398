package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

public class create_account_dialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflator = getActivity().getLayoutInflater();
		
		//builder.setView(inflater.inflate(R.layout.dialog)
		
		
//			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int id) {
//					// TODO Auto-generated method stub
//					//User clicked OK -- save their account
//					//return them to the previous menu	
//				}
//			})
//			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int id) {
//					// TODO Auto-generated method stub
//					//user clicked cancel. Clear forms, return to top menu
//				}
//			});
		return null;
	}
}
