package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.EditAccountAccountsListDialog;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.CreateAccountDialog;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.EditAccountDialog;
import com.bbdt.bluetoothbicyclediagnostics.dialogs.SwitchAccountAccountsListDialog;
import com.bbdt.bluetoothbicyclediagnostics.serializable.Account;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;

public class ManageAccountsActivity extends Activity{
	private boolean exitOnCreateAccount = false;
	
	private static final String CREATE_DIALOG_TAG = "Create Dialog";
	private static final String EDIT1_DIALOG_TAG = "List Dialog";
	private static final String EDIT2_DIALOG_TAG = "Edit Dialog";
	private static final String SWITCH_DIALOG_TAG = "Switch Dialog";
	
	private static final String TOAST_FILL_ALL_FIELDS = "Please fill in all fields.";
	private static final String TOAST_ACCOUNT_EXISTS = "The username already exists.";
	private static final String TOAST_CANNOT_DELETE_LAST_ACCOUNT = "Cannot delete the last account in the system.";
	
	/**
	 * Callback to start the activity
	 * Checks if it was prompted to automatically create an account
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_manage_accounts);
		
		Intent intent = this.getIntent();
		Bundle extras = intent.getExtras();
		if(extras != null && extras.containsKey(MainActivity.EXTRA_CREATE_ACCOUNT) && extras.getBoolean(MainActivity.EXTRA_CREATE_ACCOUNT)){
			exitOnCreateAccount = true;
			createAccount();
		}
		
		setCurrentUserText();
	}
	
	private void setCurrentUserText(){
		Account account = FileHandler.getDefaultAccount(this);
		if(account != null){
			TextView currentUser = (TextView)this.findViewById(R.id.current_user);
			currentUser.setText(account.getUsername());
		}
	}
	
	/**
	 * Save an account
	 * @param view
	 */
	public void saveAccount() {
		FragmentManager manager = this.getFragmentManager();
		
		CreateAccountDialog dialog = (CreateAccountDialog)manager.findFragmentByTag(CREATE_DIALOG_TAG);
		
		EditText username = ((EditText)dialog.getDialog().findViewById(R.id.dialog_username));
		EditText weight = ((EditText)dialog.getDialog().findViewById(R.id.dialog_weight));
		EditText age = ((EditText)dialog.getDialog().findViewById(R.id.dialog_age));
		EditText wheelDiameter = ((EditText)dialog.getDialog().findViewById(R.id.dialog_wheel));
		
		if(!username.getText().toString().equals("")
				&& !weight.getText().toString().equals("")
				&& !age.getText().toString().equals("")
				&& !wheelDiameter.getText().toString().equals("")){
			
			Account account = new Account(
					username.getText().toString(), 
					Double.parseDouble(weight.getText().toString()),
					Double.parseDouble(age.getText().toString()),
					Double.parseDouble(wheelDiameter.getText().toString()));
			
			if(FileHandler.saveAccount(account, this)){
				dialog.dismiss();
			}
			else{
				Toast.makeText(this, TOAST_ACCOUNT_EXISTS, Toast.LENGTH_SHORT);
			}
			
			if(exitOnCreateAccount){
				finish();
			}
		}
		else{
			Toast.makeText(this, TOAST_FILL_ALL_FIELDS, Toast.LENGTH_SHORT).show();
		}
		
		setCurrentUserText();
	}
	
	public void saveEditedAccountClick(View view){
		FragmentManager manager = this.getFragmentManager();
		
		EditAccountDialog dialog = (EditAccountDialog)manager.findFragmentByTag(EDIT2_DIALOG_TAG);
		
		TextView username = ((TextView)dialog.getDialog().findViewById(R.id.dialog_username));
		EditText weight = ((EditText)dialog.getDialog().findViewById(R.id.dialog_weight));
		EditText age = ((EditText)dialog.getDialog().findViewById(R.id.dialog_age));
		EditText wheelDiameter = ((EditText)dialog.getDialog().findViewById(R.id.dialog_wheel));
		
		if(!username.getText().toString().equals("")
				&& !weight.getText().toString().equals("")
				&& !age.getText().toString().equals("")
				&& !wheelDiameter.getText().toString().equals("")){
			
			Account account = new Account(
					username.getText().toString(), 
					Double.parseDouble(weight.getText().toString()),
					Double.parseDouble(age.getText().toString()),
					Double.parseDouble(wheelDiameter.getText().toString()));
			
			FileHandler.replaceAccount(account, this);
			dialog.dismiss();
		}
		else{
			Toast.makeText(this, TOAST_FILL_ALL_FIELDS, Toast.LENGTH_SHORT).show();
		}
		
		setCurrentUserText();
	}
	
	public void saveAccountClick(View view){
		saveAccount();
	}
	
	private void createAccount(){
		FragmentManager manager = getFragmentManager();
		manager.popBackStack();			
		new CreateAccountDialog().show(manager, CREATE_DIALOG_TAG);
	}
	
	public void createAccountClick(View view){
		createAccount();
	}
	
	public void editAccountClick(View view) {
		FragmentManager manager = getFragmentManager();
		manager.popBackStack();			
		new EditAccountAccountsListDialog().show(manager, EDIT1_DIALOG_TAG);
	}
	
	public void switchAccountClick(View view){
		FragmentManager manager = getFragmentManager();
		manager.popBackStack();			
		new SwitchAccountAccountsListDialog().show(manager, SWITCH_DIALOG_TAG);
	}
	
	public void selectAccountSwitchClick(View view) {
		FragmentManager manager = this.getFragmentManager();
		((SwitchAccountAccountsListDialog)manager.findFragmentByTag(SWITCH_DIALOG_TAG)).getDialog().dismiss();
		
		
		Button button = (Button) view;
		FileHandler.setDefaultAccount(button.getText().toString(),this);
		setCurrentUserText();
	}
	
	public void selectAccountEditClick(View view){
		Button button = (Button) view;
		Account account = FileHandler.getAccount(button.getText().toString(), this);
		
		FragmentManager manager = getFragmentManager();
		EditAccountAccountsListDialog accountList = (EditAccountAccountsListDialog)manager.findFragmentByTag(EDIT1_DIALOG_TAG);
		accountList.dismiss();
		
		manager.popBackStack();		
		EditAccountDialog dialog = new EditAccountDialog();
		dialog.setAccount(account);
		dialog.show(manager, EDIT2_DIALOG_TAG);
	}
	
	public void deleteAccountClick(View view){
		FragmentManager manager = this.getFragmentManager();
		
		EditAccountDialog dialog = (EditAccountDialog)manager.findFragmentByTag(EDIT2_DIALOG_TAG);
		
		TextView username = ((TextView)dialog.getDialog().findViewById(R.id.dialog_username));
		EditText weight = ((EditText)dialog.getDialog().findViewById(R.id.dialog_weight));
		EditText age = ((EditText)dialog.getDialog().findViewById(R.id.dialog_age));
		EditText wheelDiameter = ((EditText)dialog.getDialog().findViewById(R.id.dialog_wheel));
		
		Account account = new Account(
				username.getText().toString(), 
				Double.parseDouble(weight.getText().toString()),
				Double.parseDouble(age.getText().toString()),
				Double.parseDouble(wheelDiameter.getText().toString()));
			
		
		if(FileHandler.deleteAccount(account, this)){
			dialog.dismiss();
		}
		else{
			Toast.makeText(this, TOAST_CANNOT_DELETE_LAST_ACCOUNT, Toast.LENGTH_SHORT).show();
		}
		
		setCurrentUserText();
	}
	
	public boolean getExitOnCreateAccount(){
		return this.exitOnCreateAccount;
	}
}