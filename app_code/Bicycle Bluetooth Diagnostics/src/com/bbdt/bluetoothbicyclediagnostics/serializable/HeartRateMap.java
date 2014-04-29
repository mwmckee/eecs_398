/*
 * Project: Bicycle Bluetooth Diagnostics Module
 * Class:	EECS 398/399, Spring 2014
 * 
 * Team:	Brian Hayt, Matt McKee, Ken Akiki, Casey Stoessl, Rachid Lamouri
 */

package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Heart rate map to use with serializable data transfered from the Bluno for the Bicycle Bluetooth Diagnostics Module
 */
public class HeartRateMap implements Serializable{
	private static final long serialVersionUID = 6487849901007170625L;
	
	public ArrayList<Long> times = new ArrayList<Long>();
	public ArrayList<Long> sampleTimes = new ArrayList<Long>();
	public ArrayList<Double> heartRates = new ArrayList<Double>();
}