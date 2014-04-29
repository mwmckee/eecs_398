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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.adapters.SwitchAccountAccountsListAdapter;

/**
 * Dialog creation for BBDM application to switch an account to a previously created account
 *
 */
public class SwitchAccountAccountsListDialog extends DialogFragment {		
	/**
	 * On create, constructs a dialog with the list of previously made accounts
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_accounts_list, null));
		
		return builder.create();
	}
	
	/**
	 * Displays a list of previously made accounts
	 */
	public void onStart(){
		super.onStart();
		ListView accountsList = (ListView)this.getDialog().findViewById(R.id.accounts_list);
		accountsList.setAdapter(new SwitchAccountAccountsListAdapter(getActivity()));
	}
}