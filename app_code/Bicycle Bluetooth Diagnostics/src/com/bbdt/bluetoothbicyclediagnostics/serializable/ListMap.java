package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class ListMap implements Serializable{
	private static final long serialVersionUID = 6273098168268109203L;
	
	public ArrayList<Long> times = new ArrayList<Long>();
	public ArrayList<Double> values = new ArrayList<Double>();
}