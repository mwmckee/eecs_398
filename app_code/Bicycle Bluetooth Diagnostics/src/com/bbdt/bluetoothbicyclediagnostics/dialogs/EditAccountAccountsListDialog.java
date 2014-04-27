package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.bbdt.bluetoothbicyclediagnostics.R;
import com.bbdt.bluetoothbicyclediagnostics.adapters.EditAccountAccountsListAdapter;

public class EditAccountAccountsListDialog extends DialogFragment {		
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_accounts_list, null));
		
		return builder.create();
	}
	
	
	public void onStart(){
		super.onStart();
		ListView accountsList = (ListView)this.getDialog().findViewById(R.id.accounts_list);
		accountsList.setAdapter(new EditAccountAccountsListAdapter(getActivity()));
	}
}