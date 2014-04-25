package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.bbdt.bluetoothbicyclediagnostics.R;

public class create_account_dialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.dialog_create_account, null));
//			.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int id) {
//					//TODO Save the data
//				}
//			})
//			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//				public void OnClick(DialogInterface dialog, int id) {
//					LoginDialogFragment.this.getDialog().cancel();
//				}
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
		return builder.create();	
	}
	
	
//	public void confirmSaveData() {
//		DialogFragment newFragment = new SaveDataDialogFragment();
//		newFragment.show(getFragmentManager(), "Save");
//	}
}
