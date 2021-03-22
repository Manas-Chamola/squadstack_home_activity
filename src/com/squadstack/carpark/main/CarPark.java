package com.squadstack.carpark.main;

import java.util.HashMap;
import java.util.Map;

public class CarPark {
	
	Map<Integer, String[]> carParkMapping;
	boolean slots[];
	
	CarPark(int n){
		carParkMapping=new HashMap<>();
		slots=new boolean[n];
	}
	
	public Map<Integer, String[]> getCarParkMapping() {
		return carParkMapping;
	}
	public void setCarParkMapping(Map<Integer, String[]> carParkMapping) {
		this.carParkMapping = carParkMapping;
	}
	public boolean[] getSlots() {
		return slots;
	}
	public void setSlots(boolean[] slots) {
		this.slots = slots;
	}

}
