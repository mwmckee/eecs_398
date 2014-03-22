package com.bbdt.bluetoothbicyclediagnostics.activities;

import com.bbdt.bluetoothbicyclediagnostics.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;

public class RidingHistoryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riding_history);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}