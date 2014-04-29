package com.bbdt.bluetoothbicyclediagnostics.serializable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A class for bundling ride data before saving it
 * @author Rachid
 *
 */
public class RideData implements Serializable{
	private static final long serialVersionUID = 2309472431926519677L;
	
	private final String label;
	
	public RotationMap rotationData;
	public HeartRateMap heartRateData;
	public ListMap pressureData;
	public ListMap gradientData = new ListMap();
	
	public RideData(RotationMap rotationData, HeartRateMap heartRateData, ListMap pressureData, ListMap gradientData){
		this.rotationData = rotationData;
		this.heartRateData = heartRateData;
		this.pressureData = pressureData;
		this.gradientData = gradientData;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd HH-mm-ss", Locale.US);
		this.label = format.format(new Date());
	}
	
	public String getLabel(){
		return new String(label);
	}
}
