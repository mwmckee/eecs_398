package com.bbdt.bluetoothbicyclediagnostics;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private LinearLayout root;
	private Button manageAccountsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		LayoutInflater inflater = this.getLayoutInflater();
		root = (LinearLayout)inflater.inflate(R.layout.activity_main, null);
		manageAccountsButton = (Button)root.findViewById(R.id.manage_accounts_button);
		
		
		Log.e("NOT ERROR","On Create " + manageAccountsButton.isClickable());
	}
	
	public void logcat(View view){
		Log.e("", "Woot");
	}
	
	protected void onStart(){
		super.onStart();
		
		Log.e("NOT ERROR","On start " + manageAccountsButton.getTag());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}