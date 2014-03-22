package com.bbdt.bluetoothbicyclediagnostics.activities;

import com.bbdt.bluetoothbicyclediagnostics.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;

public class ManageAccountsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_accounts);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}