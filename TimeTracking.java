import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimeTracking {
	//An array to hold all of the timeslots for the week
	public static TimeSlot[] timeList = new TimeSlot[20];

	public static void main(String[] args) {
		takeInput();
		printByDay();
		System.out.println("\n" + timeTotal());
	}
	
	//Round a double to the nearest hundredth
	public static double roundHundredth(double num) {
		return (double)(Math.round(num * 100)) / 100;
	}

	//Convert time format to decimal format
	public static double timeToDecimal(String time){
		int hours = Integer.parseInt(time.substring(0, time.indexOf(":")));
		double minutes = Double.parseDouble(time.substring(time.indexOf(":")+1));

		return roundHundredth(hours + (minutes / 60));
	}

	//Convert decimal format to time format
	public static String decimalToTime(double dec){
		int hours = (int) dec;
		double hPercent = (dec - hours);
		String minutes = String.format("%02d", Math.round((hPercent * 60)));

		return (hours + ":" + minutes);
	}
	
	//Take time input from a file "timeFile.txt"
	public static void takeInput() {
		TimeSlot tempTimeSlot;
		String day, temp;
		double start, end;

		int x = 0;	

		try {
			File timeFile = new File("timeFile.txt");
			Scanner fileReader = new Scanner(timeFile);

			while(fileReader.hasNextLine()){
				day = fileReader.nextLine();

				if(fileReader.hasNextLine()) {
					temp = fileReader.nextLine();
					if(temp.equals("-")) {
						start = 0;
					} else {
						start = timeToDecimal(temp);
					}
					if(fileReader.hasNextLine()) {
						temp = fileReader.nextLine();
						if(temp.equals("-")) {
							end = 0;
						} else {
							end = timeToDecimal(temp);
						}
						tempTimeSlot = new TimeSlot(day, start, end);
					}
					else {
						tempTimeSlot = new TimeSlot(day, start);
					}
				} else {
					tempTimeSlot = new TimeSlot(day);
				}

				timeList[x] = tempTimeSlot;
				x++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}	
	}

	//Print off every time slot with all of the information per slot
	public static void printDetail() {
		for(int x = 0; x < timeList.length && timeList[x] != null; x++) {
			timeList[x].print();
		}
	}

	//Get the total time for the week
	public static double timeTotal() {
		double total = 0;

		for(int x = 0; x < timeList.length && timeList[x] != null; x++) {
			if(timeList[x].time() > 0) {
				total += timeList[x].time();
			}
		}

		return total;
	}

	//Get the total of each weekday and print them out
	public static void printByDay() {
		double mon = 0, tue = 0, wed = 0, thu = 0, fri = 0, sat = 0, sun = 0;

		for(int x = 0; x < timeList.length && timeList[x] != null; x++) {
			if(timeList[x].time() > 0) {
				switch (timeList[x].getDay()) {
					case "Monday":		mon += timeList[x].time();
										break;
					case "Tuesday":		tue += timeList[x].time();
										break;
					case "Wednesday":	wed += timeList[x].time();
										break;
					case "Thursday":	thu += timeList[x].time();
										break;
					case "Friday":		fri += timeList[x].time();
										break;
					case "Saturday":	sat += timeList[x].time();
										break;
					case "Sunday":		sun += timeList[x].time();
										break;
					default:			System.out.println("Invalid day found.");
										break;
				}
			}
		}

		System.out.println(
			"Sunday: " + roundHundredth(sun) 
			+ "\nMonday: " + roundHundredth(mon) 
			+ "\nTuesday: " + roundHundredth(tue) 
			+ "\nWednesday: " + roundHundredth(wed) 
			+ "\nThursday: " + roundHundredth(thu) 
			+ "\nFriday: " + roundHundredth(fri) 
			+ "\nSaturday: " + roundHundredth(sat));
	}
}

