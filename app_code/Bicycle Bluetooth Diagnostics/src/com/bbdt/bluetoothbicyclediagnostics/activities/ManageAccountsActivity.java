package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bbdt.bluetoothbicyclediagnostics.R;

public class ManageAccountsActivity extends Activity {
	private CreateAccountDialog dialog = new CreateAccountDialog();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_accounts);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	public void saveAccount(View view) {
		Log.e("DEBUG", "Save Account");
		
		LayoutInflater inflater = this.getLayoutInflater();//dialog.getDialog().getLayoutInflater();
		LinearLayout root = (LinearLayout)inflater.inflate(R.layout.dialog_create_account, null);
		Log.e("DEBUG", "" + root);
		EditText username = (EditText) root.findViewById(R.id.dialog_username);
		EditText weight = (EditText) root.findViewById(R.id.dialog_weight);
		EditText age = (EditText) root.findViewById(R.id.dialog_age);
		EditText wheel = (EditText) root.findViewById(R.id.dialog_wheel);
		//LinearLayout ll = (LinearLayout)inflater.inflate(R.id.root, null);
		//EditText age = (EditText)inflater.inflate(R.id.dialog_age, ll);
		Log.e("DEBUG", "STUFF" + username.getText().toString() + "ASFASFN");
		Log.e("DEBUG", "STUFF" + weight.getText().toString() + "ASFASFN");
		Log.e("DEBUG", "STUFF" + age.getText().toString() + "ASFASFN");
		Log.e("DEBUG", "STUFF" + wheel.getText().toString() + "ASFASFN");
		
		Log.e("DEBUG", "END");
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