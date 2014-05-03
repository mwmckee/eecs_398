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
import android.widget.EditText;
import android.widget.TextView;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.serializable.Account;

/**
 * For the BBDM application, this dialog creates a view the edit a previously created dialog
 */
public class EditAccountDialog extends DialogFragment {	
	private Account account;
	
	public void setAccount(Account account){
		this.account = account;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_edit_account, null));
		
		return builder.create();
	}
	
	
	public void onStart(){
		super.onStart();
		TextView username = (TextView)this.getDialog().findViewById(R.id.dialog_username);
		EditText weight = (EditText)this.getDialog().findViewById(R.id.dialog_weight);
		EditText age = (EditText)this.getDialog().findViewById(R.id.dialog_age);
		EditText wheel = (EditText)this.getDialog().findViewById(R.id.dialog_wheel);
		
		username.setText(account.getUsername());
		weight.setText("" + account.getWeight());
		age.setText("" + account.getAge());
		wheel.setText("" + account.getWheelDiameter());
	}
}