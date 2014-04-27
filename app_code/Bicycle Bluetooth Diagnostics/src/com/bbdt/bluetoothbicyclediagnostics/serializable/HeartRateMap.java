package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class HeartRateMap implements Serializable{
	private static final long serialVersionUID = 6487849901007170625L;
	
	public ArrayList<Long> times = new ArrayList<Long>();
	public ArrayList<Long> sampleTimes = new ArrayList<Long>();
	public ArrayList<Double> heartRates = new ArrayList<Double>();
}