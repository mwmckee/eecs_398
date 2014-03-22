package com.bbdt.bluetoothbicyclediagnostics.activities;

import com.bbdt.bluetoothbicyclediagnostics.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;

public class NewRideActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_ride);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}