import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimeTracking {
	public static TimeSlot[] timeList = new TimeSlot[20];

	public static void main(String[] args) {
		takeInput();

		for(int x = 0; x < timeList.length; x++) {
			try {
				timeList[x].print();
			}
			catch(NullPointerException e) {
				return;
			}
		}
	}
	
	//round to nearest hundredth
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
	
	//take time input from a file "timeFile.txt"
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

}

