/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.serializable.Account;
import com.bbdt.bluetoothbicyclediagnostics.serializable.FileHandler;

public class EditAccountAccountsListAdapter extends BaseAdapter{
	private Activity activity;
	private ArrayList<Account> accounts;
	
	public EditAccountAccountsListAdapter(Activity activity){
		accounts = FileHandler.getAccounts(activity);
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return accounts.size();
	}

	@Override
	public Object getItem(int index) {
		return accounts.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		Button button = (Button)view;
		
		if(view == null){
			button = (Button)activity.getLayoutInflater().inflate(R.layout.list_item_account_edit_account, null);
			button.setText(accounts.get(index).getUsername());
		}
		
		return button;
	}
}
