/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.Serializable;

/**
 * Account map to use with serializable data transfered from the Bluno for the Bicycle Bluetooth Diagnostics Module
 */
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private int weight;
	private int age;
	private double wheelDiameter;
	
	public Account(String username, double weight, double age, double wheelDiameter){
		this.username = username;
		this.weight = (int)weight;
		this.age = (int)age;
		this.wheelDiameter = wheelDiameter;
	}
	
	public String getUsername(){
		return username;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getAge(){
		return age;
	}
	
	public double getWheelDiameter(){
		return wheelDiameter;
	}
}
