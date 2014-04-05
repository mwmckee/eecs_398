package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

import com.bbdt.bluetoothbicyclediagnostics.R;

public class MainActivity extends FragmentActivity implements NoticeDialogFragment.NoticeDialogListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** 
	 * On Click for manage accounts button
	 * @param view
	 */
	public void goToManageAccounts(View view){
		Intent intent = new Intent(view.getContext(), ManageAccountsActivity.class);
		view.getContext().startActivity(intent);
	}
	
	/**
	 * On Click for riding history button
	 * @param view
	 */
	public void goToRidingHistory(View view){
		Intent intent = new Intent(view.getContext(), RidingHistoryActivity.class);
		view.getContext().startActivity(intent);
	}
	
	/**
	 * On Click for new ride button
	 * @param view
	 */
	public void goToNewRide(View view){
		Intent intent = new Intent(view.getContext(), NewRideActivity.class);
		
		view.getContext().startActivity(intent);
	}

	public void ShowNoticeDialog() {
		DialogFragment dialog = new NoticeDialogFragment();
		//FragmentManager manager = getSupportFragmentManager();	//It should be this.
		FragmentManager manager = getFragmentManager();			//Not this.
		dialog.show(manager, "NoticeDialogFragment");
	}
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}