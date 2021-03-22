package com.squadstack.carpark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.squadstack.carpark.main.CarPark;

public class CarParkService {
	
	public int getFreeSlot(CarPark carPark) {
		boolean[]slots=carPark.getSlots();
		int slot=-1;
		for(int i=0; i<slots.length; i++) {
			if(!slots[i]) {
				slot=i+1;
				break;
			}
		}
		
		if(slot>0&&slot<=slots.length)
			slots[slot-1]=true;
		
		return slot;
	}
	
	public int parkCar(CarPark carPark,String []parkDetails) {
		int slot=getFreeSlot(carPark);
		if(slot<=0)
			return slot;
		
		carPark.getCarParkMapping().put(slot, parkDetails);
		return slot;
	}
	
	public String[] leaveCar(CarPark carPark,int slot) {
		Map<Integer, String[]>carParkMapping=carPark.getCarParkMapping();
		String[] carDetails=carParkMapping.get(slot);
		boolean[]slots=carPark.getSlots();
		slots[slot-1]=false;
		carParkMapping.remove(slot);
		return carDetails;
	}
	
	public List<Integer> getSlotsForDriverOfAge(CarPark carPark, int age) {
		Map<Integer, String[]>carParkMapping= carPark.getCarParkMapping();
		List<Integer> slots= new ArrayList<>();
		for(Integer slot:carParkMapping.keySet()) {
			String[] carDetails=carParkMapping.get(slot);
			if(Integer.parseInt(carDetails[1])==age)
				slots.add(slot);
		}
		
		return slots;
	}
	
	public int getSlotForCarWithNum(CarPark carPark, String regNum) {
		Map<Integer, String[]>carParkMapping= carPark.getCarParkMapping();
		for(Integer slot:carParkMapping.keySet()) {
			String[] carDetails=carParkMapping.get(slot);
			if(carDetails[0].equals(regNum))
				return slot;
		}
		return -1;
	}
	
	public List<String> getRegNumsForDriverOfAge(CarPark carPark, int age) {
		Map<Integer, String[]>carParkMapping= carPark.getCarParkMapping();
		List<String> regNum= new ArrayList<>();
		for(Integer slot:carParkMapping.keySet()) {
			String[] carDetails=carParkMapping.get(slot);
			if(Integer.parseInt(carDetails[1])==age)
				regNum.add(carDetails[0]);
		}
		
		return regNum;
	}
}
