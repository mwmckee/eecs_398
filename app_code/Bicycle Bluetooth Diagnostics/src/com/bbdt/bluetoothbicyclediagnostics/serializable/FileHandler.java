package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;

import android.os.Environment;
import android.util.Log;

public class FileHandler {
	private static final String appPath =  Environment.getExternalStorageDirectory() + "/BBDM";
	private static final String accountsFilePath = appPath + "/accounts.ser";
	
	public static void init(){
		File saveFolder = new File(appPath);
		if(!saveFolder.exists()){
			saveFolder.mkdir();
		}
		
		Log.e("DEBUG", "Exists? " + saveFolder.exists());
	}
	
	public static void saveAccount(Account account){
		//String saveFilePath = accountsPath + "/" + account.getUsername() + extension;		
		
		/*
		Account herpderp = null;
		
		try {
			File saveFile = new File(saveFilePath);
			if(!saveFile.exists()){
				saveFile.createNewFile();
			}
			
			FileOutputStream fileOut = new FileOutputStream(saveFilePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(account);
			out.close();
			fileOut.close();
		
			
			
		} catch (FileNotFoundException e) {
			Log.e("FNFE", e.getMessage());
		} catch (IOException e) {
			Log.e("IOE", e.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("CNE", e.getMessage());
		}
		
		if(herpderp != null){
			Log.e("DEBUG", "U: " + herpderp.getUsername());
			Log.e("DEBUG", "A: " + herpderp.getAge());
			Log.e("DEBUG", "WE: " + herpderp.getWeight());
			Log.e("DEBUG", "WH: " + herpderp.getWheelDiameter());
		}
		*/
		
	}
	
	public static Account getDefaultAccount(){
		Account account = null;
		
		FileInputStream filein;
		try {
			filein = new FileInputStream(accountsFilePath);
			ObjectInputStream in = new ObjectInputStream(filein);
			account = (Account) in.readObject();
			in.close();
			filein.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return account;
	}
}