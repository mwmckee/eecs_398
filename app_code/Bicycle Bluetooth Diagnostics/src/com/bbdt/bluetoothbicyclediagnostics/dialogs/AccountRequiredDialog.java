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

/**
 * For the Bicycle Bluetooth Diagnostics Application, this dialog prompts the user to create an account to proceed
 *
 */
public class AccountRequiredDialog extends DialogFragment {	
	/** Stores if the app should be closed */
	private boolean exit = true;

	/**
	 * Builds a dialog with a custom layout
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_account_required, null));
		
		return builder.create();
	}
	
	/**
	 * If the dialog was purposefully dismissed close the app
	 */
	@Override
	public void onDismiss(DialogInterface dialogInterface) {
		if(exit && this.getActivity() != null){
			this.getActivity().finish();
		}
	}
	
	/**
	 * This type of dialog should close the app if dismissed
	 * @param exit
	 */
	public void setExit(boolean exit){
		this.exit = exit;
	}
}