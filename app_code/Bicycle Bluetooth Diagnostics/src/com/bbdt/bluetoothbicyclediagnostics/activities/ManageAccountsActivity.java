package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.bbdt.bluetoothbicyclediagnostics.R;

public class ManageAccountsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_accounts);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	public void createAccountOnClick(View view) {
		DialogFragment dialog = new NoticeDialogFragment();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
	
	public void switchAccountOnClick(View view) {
		DialogFragment dialog = new NoticeDialogFragment();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
	
	public void editAccountOnClick(View view) {
		DialogFragment dialog = new NoticeDialogFragment();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
}