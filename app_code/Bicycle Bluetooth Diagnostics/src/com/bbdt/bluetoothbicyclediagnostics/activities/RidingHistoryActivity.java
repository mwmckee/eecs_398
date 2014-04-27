/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.bbdt.bluetoothbicyclediagnostics.R;

public class RidingHistoryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riding_history);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}