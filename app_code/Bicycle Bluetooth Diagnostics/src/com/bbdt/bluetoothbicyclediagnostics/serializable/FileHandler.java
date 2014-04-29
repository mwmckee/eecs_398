package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class FileHandler {
	private static final String accountsFile = "accounts.ser";
	private static final String ridesFile = "rides.ser";
	
	public static boolean saveAccount(Account account, Activity activity){
		ArrayList<Account> accounts = getAccounts(activity);
		// check username does not exist
		for(Account nextAccount: accounts){
			if(nextAccount.getUsername().equals(account.getUsername())){
				return false;
			}
		}
		
		accounts.add(0,account);
		
		writeAccounts(accounts, activity);
		return true;
	}
	
	public static boolean deleteAccount(Account account, Activity activity){
		ArrayList<Account> accounts = getAccounts(activity);
		
		if(accounts.size() == 1){
			// Can't delete last file
			return false;
		}
		else{
			Account accountToRemove = null;
			for(Account nextAccount: accounts){
				if(nextAccount.getUsername().equals(account.getUsername())){
					accountToRemove = nextAccount;
					break;
				}
			}
			
			accounts.remove(accountToRemove);
			
			writeAccounts(accounts, activity);
			return true;
		}
	}
	
	public static void replaceAccount(Account account, Activity activity){
		ArrayList<Account> accounts = getAccounts(activity);
		
		for(int i = 0; i < accounts.size(); i++){
			Account nextAccount = accounts.get(i);
			if(nextAccount.getUsername().equals(account.getUsername())){
				accounts.remove(i);
				accounts.add(i,account);
				break;
			}
		}
		
		writeAccounts(accounts, activity);
	}
	
	public static ArrayList<Account> getAccounts(Activity activity){
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			FileInputStream filein = activity.openFileInput(accountsFile);
			ObjectInputStream in = new ObjectInputStream(filein);
			
			Account account = (Account) in.readObject();
			while(account != null){
				accounts.add(account);
				account = (Account) in.readObject();
			}
			
			in.close();
			filein.close();
		} catch (FileNotFoundException e) {
			Log.e("FNFE", "GA: " + e.getMessage());
		} catch (StreamCorruptedException e) {
			Log.e("SCE", "GA: " + e.getMessage());
		} catch (IOException e) {
			Log.e("IOE", "GA: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("CNFE", "GA: " + e.getMessage());
		}
		
		return accounts;
	}
	
	public static ArrayList<RideData> getRides(Activity activity){
		ArrayList<RideData> rides = new ArrayList<RideData>();
		try {
			FileInputStream filein = activity.openFileInput(ridesFile);
			ObjectInputStream in = new ObjectInputStream(filein);
			
			RideData rideData = (RideData) in.readObject();
			while(rideData != null){
				rides.add(rideData);
				rideData = (RideData) in.readObject();
			}
			
			in.close();
			filein.close();
		} catch (FileNotFoundException e) {
			Log.e("FNFE", "GR: " + e.getMessage());
		} catch (StreamCorruptedException e) {
			Log.e("SCE", "GR: " + e.getMessage());
		} catch (IOException e) {
			Log.e("IOE", "GR: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("CNFE", "GR: " + e.getMessage());
		}
		
		return rides;
	}
	
	public static Account getDefaultAccount(Activity activity){
		ArrayList<Account> accounts = getAccounts(activity);
		
		if (accounts.size() > 0){
			return accounts.get(0);
		}
		else{
			return null;
		}
	}
	
	public static void saveRide(RideData data, Activity activity){
		ArrayList<RideData> rides = getRides(activity);
		
		rides.add(0, data);
		
		// write back all the accounts
		try {
			FileOutputStream fileOut = activity.openFileOutput(ridesFile, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for(RideData nextData: rides){
				out.writeObject(nextData);
			}
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			Log.e("FNFE", "SR: " + e.getMessage());
		} catch (IOException e) {
			Log.e("IOE", "SR: " + e.getMessage());
		} 
	}
	
	public static RideData getRideData(String rideName, Activity activity){
		ArrayList<RideData> rides = getRides(activity);
		for(RideData data: rides){
			if(data.getLabel().equals(rideName)){
				return data;
			}
		}
		
		return null;
	}
	
	public static Account getAccount(String accountName, Activity activity){
		ArrayList<Account> accounts = getAccounts(activity);
		for(Account account: accounts){
			if(account.getUsername().equals(accountName)){
				return account;
			}
		}
		
		return null;
	}
	
	public static void setDefaultAccount(String accountName, Activity activity){
		ArrayList<Account> accounts = getAccounts(activity);
		
		Account account = null;
		for(int i = 0; i <accounts.size(); i++){
			if(accounts.get(i).getUsername().equals(accountName)){
				account = accounts.remove(i);
			}
		}
		
		accounts.add(0,account);
		
		writeAccounts(accounts, activity);
	}
	
	private static void writeAccounts(ArrayList<Account> accounts, Activity activity){
		// write back all the accounts
		try {
			FileOutputStream fileOut = activity.openFileOutput(accountsFile, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for(Account nextAccount: accounts){
				out.writeObject(nextAccount);
			}
			out.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			Log.e("FNFE", "SA: " + e.getMessage());
		} catch (IOException e) {
			Log.e("IOE", "SA: " + e.getMessage());
		} 
	}
	
	/*
	private static void writeRides(ArrayList<RideData> rides, Activity activity){
		
	}
	*/
}