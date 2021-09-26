package Yashi;

import java.util.*;
public class ParkingLot {
	int MAX_SPACE = 0;
	private class Car {
		String regNo;
		String age;
		public Car(String regNo, String age) {
			this.regNo = regNo;
			this.age = age;
		}
	}
	// Available slots list
	ArrayList<Integer> listOfAvailableSlot;
	Map<String, Car> mp1;

	Map<String, String> mp2;

	Map<String, ArrayList<String>> mp3;


	public void createParkingLot(String lotCount) {
		try {
			this.MAX_SPACE = Integer.parseInt(lotCount);
		} catch (Exception e) {
			System.out.println("Invalid lot count");


		}
		this.listOfAvailableSlot = new ArrayList<Integer>() {};
		int i=1;
		while(i<=this.MAX_SPACE)
		{
			listOfAvailableSlot.add(i);
			i++;
		}
		this.mp1 = new HashMap<String, Car>();
		this.mp2 = new HashMap<String, String>();
		this.mp3 = new HashMap<String, ArrayList<String>>();
		System.out.println("Created parking of " + lotCount + " slots");

	}
	public void park(String regNo, String age) {
		if (this.MAX_SPACE == 0) {
			System.out.println("Sorry, parking lot is not created");

		} else if (this.mp1.size() == this.MAX_SPACE) {
			System.out.println("Sorry, parking lot is full");

		} else {
			Collections.sort(listOfAvailableSlot);
			String slot = listOfAvailableSlot.get(0).toString();
			Car car = new Car(regNo, age);
			this.mp1.put(slot, car);
			this.mp2.put(regNo, slot);
			if (this.mp3.containsKey(age)) {
				ArrayList<String> listofRegNo = this.mp3.get(age);
				this.mp3.remove(age);
				listofRegNo.add(regNo);
				this.mp3.put(age, listofRegNo);
			} else {
				ArrayList<String> listofRegNo = new ArrayList<String>();
				listofRegNo.add(regNo);
				this.mp3.put(age, listofRegNo);
			}
			System.out.println("Car with vehicle registration number \""+ regNo +"\" has been parked at slot number "+slot);

			listOfAvailableSlot.remove(0);
		}
	}
	public void status() {
		if (this.MAX_SPACE == 0) {
			System.out.println("Sorry, parking lot is not created");

		} else if (this.mp1.size() > 0) {
			// Print the current status.
			System.out.println("Slot No.\tRegistration No.\tage");
			Car car;
			int k=1;
			while(k<=this.MAX_SPACE)
			{
				String key = Integer.toString(k);
				if (this.mp1.containsKey(key)) {
					car = this.mp1.get(key);
					System.out.println(k + "\t" + car.regNo + "\t" + car.age);
					k++;
				}
			}

		} else {
			System.out.println("Parking lot is empty");

		}
	}
	public void leave(String SlotNum) {
		if (this.MAX_SPACE == 0) {
			System.out.println("Sorry, parking lot is not created");

		} else if (this.mp1.size() > 0) {
			Car carToexit = this.mp1.get(SlotNum);
			if (carToexit != null) {
				this.mp1.remove(SlotNum);
				this.mp2.remove(carToexit.regNo);
				ArrayList<String> listofRegNo = this.mp3.get(carToexit.age);
				if (listofRegNo.contains(carToexit.regNo)) {
					listofRegNo.remove(carToexit.regNo);
				}
				// Add the Lot No. back to available slot list.
				this.listOfAvailableSlot.add(Integer.parseInt(SlotNum));
				System.out.println("Slot number " + SlotNum + " vacated, the car with vehicle registration number "+carToexit.regNo+"left the space, the driver of the car was of age "+carToexit.age);

			} else {
				System.out.println(SlotNum + " is empty already");

			}
		} else {
			System.out.println("Parking lot is empty");

		}
	}

	public void getSlotNumberFromRegNo(String regNo) {
		if (this.MAX_SPACE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.mp2.containsKey(regNo)) {
			System.out.println(this.mp2.get(regNo));
		} else {
			System.out.println(" ");

		}
	}
	public void getRegistrationNumbersFromAge(String age) {
		if (this.MAX_SPACE == 0) {
			System.out.println("Sorry, parking lot is not created");

		} else if (this.mp3.containsKey(age)) {
			ArrayList<String> listofRegNo = this.mp3.get(age);

			int i=0;
			while(i<listofRegNo.size())
			{
				if (!(i==listofRegNo.size() - 1)){
					System.out.print(listofRegNo.get(i) + ",");
				} else {
					System.out.print(listofRegNo.get(i));
				}
				i++;
			}
		} else {
			System.out.println("Not Found");

		}
	}
	public void getSlotNumbersFromAge(String age) {
		if (this.MAX_SPACE == 0) {
			System.out.println("Sorry, parking lot is not created");
		} else if (this.mp3.containsKey(age)) {
			ArrayList<String> listofRegNo = this.mp3.get(age);
			ArrayList<Integer> listofSlotNo = new ArrayList<Integer>();
			int i=0;
			while(i<listofRegNo.size())
			{

				listofSlotNo.add(Integer.valueOf(this.mp2.get(listofRegNo.get(i))));
				i++;}
			Collections.sort(listofSlotNo);
			int j=0;
			while(j<listofSlotNo.size())
			{

				if (!(j == listofSlotNo.size() - 1)) {
					System.out.print(listofSlotNo.get(j) + ",");
				} else {
					System.out.println(listofSlotNo.get(j));
				}
				j++;
			}

		} else {
			System.out.println("Not found");

		}
	}

}