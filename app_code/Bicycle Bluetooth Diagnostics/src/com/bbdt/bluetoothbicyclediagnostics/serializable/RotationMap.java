/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class RotationMap implements Serializable{
	private static final long serialVersionUID = -2518857184715239387L;
	
	public ArrayList<Long> times = new ArrayList<Long>();
	public ArrayList<Double> distances = new ArrayList<Double>();
	public ArrayList<Double> rpmData = new ArrayList<Double>();
	public ArrayList<Double> speeds = new ArrayList<Double>();
}