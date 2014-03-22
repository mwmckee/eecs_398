package com.bbdt.bluetoothbicyclediagnostics.activities;

import com.bbdt.bluetoothbicyclediagnostics.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
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
}