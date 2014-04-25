package com.bbdt.bluetoothbicyclediagnostics.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bbdt.bluetoothbicyclediagnostics.R;

public class CreateAccountDialog extends DialogFragment {
	public interface NoticeDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	
	NoticeDialogListener mListener;
	
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try {
//			mListener = (NoticeDialogListener) activity;
//		} catsafafsfch (ClassCastException e) {
//			throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
//		}
//	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_create_account, null));
		//builder.setMessage(R.string.create_account);
		/*
		builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					mListener.onDialogPositiveClick(CreateAccountDialog.this);
				}
			});
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					mListener.onDialogNegativeClick(CreateAccountDialog.this);
				}
			});
		*/
		return builder.create();
	}
	
	
	private class OnSaveListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int id) {
			
		}
	}
}