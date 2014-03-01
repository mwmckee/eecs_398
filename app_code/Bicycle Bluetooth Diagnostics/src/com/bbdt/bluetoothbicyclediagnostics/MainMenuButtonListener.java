package com.bbdt.bluetoothbicyclediagnostics;

import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainMenuButtonListener implements View.OnClickListener{	
	
	private MainActivity mainActivity;
	public MainMenuButtonListener(MainActivity mainActivity){
		this.mainActivity = mainActivity;
	}
	
	public void onClick(View view) {
		Log.e("NOT ERROR","Click!");
		Intent intent = new Intent(mainActivity, ManageAccountsActivity.class);
		mainActivity.startActivity(intent);
	}	
}