package com.bbdt.bluetoothbicyclediagnostics;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainMenuButtonListener implements OnClickListener{	
	public void onClick(View view) {
		Toast.makeText(view.getContext(), "Hello!", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(view.getContext(), ManageAccountsActivity.class);
		view.getContext().startActivity(intent);
	}	
}