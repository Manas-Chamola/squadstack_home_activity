package com.squadstack.carpark.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.squadstack.carpark.service.CarParkService;

public class CarParkMain {
	
	static CarPark carPark;
	static CarParkService carParkService=new CarParkService();
	
	public static void main(String []args) {
		executeProgram();
	}
	
	public static void executeProgram() {
		try  
		{  
		File file=new File("././resources/input.txt");    
		FileReader fr=new FileReader(file);    
		BufferedReader br=new BufferedReader(fr);     
		String line;
		
		while((line=br.readLine())!=null)  
		{  	
		String command[]=line.split(" ");
		
		if(command.length==2 && command[0].equals("Create_parking_lot")) {
			carPark=new CarPark(Integer.parseInt(command[1]));
			System.out.println( "Created parking of " + command[1] + " slots" );
		}
		else if(command.length==4 && command[0].equals("Park") && command[2].equals("driver_age") 
				&& Integer.parseInt(command[3])>=18 && Integer.parseInt(command[3])<=100 ) {
			String[] parkDeatils=new String[]{command[1], command[3]};
			int slot=carParkService.parkCar(carPark, parkDeatils);
			
			if(slot<=0)
				System.out.println("The Parking lot is full, no more cars can be parked ");
			else
				System.out.println("Car with vehicle registration number "+ command[1] +" has been parked at slot number "+slot);	
		}
		else if(command.length==2 && command[0].equals("Slot_numbers_for_driver_of_age") 
				&& Integer.parseInt(command[1])>=18 && Integer.parseInt(command[1])<=100) {
			List<Integer> slots= carParkService.getSlotsForDriverOfAge(carPark, Integer.parseInt(command[1]));
			StringBuilder allSlots=new StringBuilder();
			
			if(slots.size()>0) {
			for(int i:slots)
				allSlots.append(i+",");
			System.out.println(allSlots.substring(0, allSlots.length()-1));
			}else {
				System.out.println("No slots found where age of driver is "+command[1]);
			}
		}
		else if(command.length==2 && command[0].equals("Slot_number_for_car_with_number")) {
			int slot=carParkService.getSlotForCarWithNum(carPark, command[1]);
			
			if(slot>0)
				System.out.println(slot);
			else
				System.out.println("No car with number "+command[1]+" in any slot");
		}
		else if(command.length==2 && command[0].equals("Vehicle_registration_number_for_driver_of_age")
				&& Integer.parseInt(command[1])>=18 && Integer.parseInt(command[1])<=100) {
			List<String> regNums= carParkService.getRegNumsForDriverOfAge(carPark, Integer.parseInt(command[1]));
			
			if(regNums.size()>0) {
			for(String reg:regNums)
				System.out.println(reg);
			}else {
				System.out.println("No registered cars found where age of driver is "+command[1]);
			}
		}
		else if(command.length==2 && command[0].equals("Leave")){
			String[]carDetails =carParkService.leaveCar(carPark, Integer.parseInt(command[1]));
			System.out.println("Slot number "+command[1]+
					" vacated, the car with vehicle registration number "+carDetails[0]+
					" left the space, the driver of the car was of age "+carDetails[1]);
		}
		else {
			System.out.println("Invalid Command");
		}
		
		}  
		fr.close();     
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		} 
	}
	
}
