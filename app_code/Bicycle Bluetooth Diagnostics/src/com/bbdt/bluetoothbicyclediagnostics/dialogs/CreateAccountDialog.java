/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.activities.ManageAccountsActivity;

/**
 * Dialog used to create a dialog used to create an account
 *
 */
public class CreateAccountDialog extends DialogFragment {	
	/**
	 * Creates dialog on create to create and account
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_create_account, null));
		return builder.create();
	}
	
	/**
	 * On dismissal of create account dialog, checks to make sure that account exists
	 */
	@Override
	public void onDismiss(DialogInterface dialogInterface) {
		
		ManageAccountsActivity activity = ((ManageAccountsActivity)this.getActivity());
		if(activity != null && ((ManageAccountsActivity)this.getActivity()).getExitOnCreateAccount()){
			this.getActivity().finish();
		}
	}
}