package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.AccountRequiredDialog;
import com.bbdt.bluetoothbicyclediagnostics.serializable.Account;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;

public class MainActivity extends FragmentActivity {
	/** Request codes for onactivityresult */
	private static final int REQUEST_CREATE_ACCOUNT = 1;
	private static final int REQUEST_GET_ACCOUNT_NAME = 2;
	
	
	/** Toast text */
	private static String TOAST_MAKE_ACCOUNT = "Please make a default account.";
	
	/** Tag used to tell manage accounts to make default account */
	public static final String EXTRA_CREATE_ACCOUNT = "Create";
	
	private Account account;
	private AccountRequiredDialog dialog;
	
	/**
	 * Callback for starting the activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		setContentView(R.layout.activity_main);
		
		account = FileHandler.getDefaultAccount(this);
		if(account == null){
			createAccount();
		}
		else{
			setCurrentUserText();
		}
	}

	private void setCurrentUserText(){
		account = FileHandler.getDefaultAccount(this);
		if(account != null){
			TextView currentUser = (TextView)this.findViewById(R.id.current_user);
			currentUser.setText(account.getUsername());
		}
	}

	/**
	 * Start a ManageAccountsActivity telling it to create an account
	 */
	private void createAccount(){
		Toast.makeText(this, TOAST_MAKE_ACCOUNT, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, ManageAccountsActivity.class);
		intent.putExtra(EXTRA_CREATE_ACCOUNT, true);
		this.startActivityForResult(intent, REQUEST_CREATE_ACCOUNT);
	}
	
	/**
	 * Get result from start activity for result call
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		switch(requestCode){
		case REQUEST_CREATE_ACCOUNT:
			account = FileHandler.getDefaultAccount(this);
			if(account == null){
				dialog = new AccountRequiredDialog();
				FragmentManager manager = getFragmentManager();	
				dialog.show(manager, "NoticeDialogFragment");
			}
			else{
				setCurrentUserText();
			}
			break;
		case REQUEST_GET_ACCOUNT_NAME:
			setCurrentUserText();
			break;
		}
	}
	
	/**
	 * Ok click method for the accountrequired dialog
	 * @param view
	 */
	public void confirmClick(View view){
		dialog.setExit(false);
		createAccount();
		dialog.dismiss();
	}
	
	/**
	 * exit click method for the acountrequired dialog
	 * @param view
	 */
	public void exitClick(View view){
		dialog.setExit(true);
		finish();
	}

	/** 
	 * On Click for manage accounts button
	 * @param view
	 */
	public void goToManageAccounts(View view){
		Intent intent = new Intent(view.getContext(), ManageAccountsActivity.class);
		this.startActivityForResult(intent, REQUEST_GET_ACCOUNT_NAME);
	}
	
	/**
	 * On Click for riding history button
	 * @param view
	 */
	public void goToRidingHistory(View view){
		Intent intent = new Intent(view.getContext(), RidingHistoryActivity.class);
		startActivity(intent);
	}
	
	/**
	 * On Click for new ride button
	 * @param view
	 */
	public void goToNewRide(View view){
		Intent intent = new Intent(view.getContext(), NewRideActivity.class);
		startActivity(intent);
	}
	

	/**
	 * Create the options menu (not used)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}