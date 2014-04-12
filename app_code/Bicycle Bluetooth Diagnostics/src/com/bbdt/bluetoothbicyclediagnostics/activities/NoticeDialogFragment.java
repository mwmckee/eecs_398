package com.bbdt.bluetoothbicyclediagnostics.activities;

import android.app.Activity;
import android.app.DialogFragment;

public class NoticeDialogFragment extends DialogFragment {
	public interface NoticeDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	
	NoticeDialogListener mListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (NoticeDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
		}
	}
	
	
	//need to figure out where to create R.string.dialog_create_account and R.string.save
//	@Override
//	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//		builder.setMessage(R.string.dialog_create_account)
//			.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int id) {
//					mListener.onDialogPositiveClick(NoticeDialogFragment.this);
//				}
//			})
//			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int id) {
//					mListener.onDialogNegativeClick(NoticeDialogFragment.this);
//				}
//			});
//		
//		return builder.create();
//	}
}