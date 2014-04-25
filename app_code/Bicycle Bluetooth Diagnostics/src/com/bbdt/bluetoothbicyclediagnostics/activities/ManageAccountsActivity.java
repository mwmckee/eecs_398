package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.CreateAccountDialog;
import com.bbdt.bluetoothbicyclediagnostics.serializable.Account;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;

public class ManageAccountsActivity extends Activity {
	private CreateAccountDialog dialog = new CreateAccountDialog();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_accounts);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		Log.e("DEBUG", "Started for result?" + requestCode);
	}
	
	public void saveAccount(View view) {
		EditText username = ((EditText)dialog.getDialog().findViewById(R.id.dialog_username));
		EditText weight = ((EditText)dialog.getDialog().findViewById(R.id.dialog_weight));
		EditText age = ((EditText)dialog.getDialog().findViewById(R.id.dialog_age));
		EditText wheelDiameter = ((EditText)dialog.getDialog().findViewById(R.id.dialog_wheel));
		
		Account account = new Account(
				username.getText().toString(), 
				Double.parseDouble(weight.getText().toString()),
				Double.parseDouble(age.getText().toString()),
				Double.parseDouble(wheelDiameter.getText().toString()));
		
		FileHandler.saveAccount(account);
	}
	
	public void createAccountOnClick(View view) {
		//DialogFragment dialog = new CreateAccountDialog();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
	
	public void switchAccountOnClick(View view) {
		DialogFragment dialog = new CreateAccountDialog();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
	
	public void editAccountOnClick(View view) {
		DialogFragment dialog = new CreateAccountDialog();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
}