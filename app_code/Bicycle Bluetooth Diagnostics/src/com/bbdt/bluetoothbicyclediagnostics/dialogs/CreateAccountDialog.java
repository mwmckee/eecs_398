package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.activities.ManageAccountsActivity;

public class CreateAccountDialog extends DialogFragment {	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_create_account, null));
		return builder.create();
	}
	
	@Override
	public void onDismiss(DialogInterface dialogInterface) {
		
		ManageAccountsActivity activity = ((ManageAccountsActivity)this.getActivity());
		if(activity != null && ((ManageAccountsActivity)this.getActivity()).getExitOnCreateAccount()){
			this.getActivity().finish();
		}
	}
}