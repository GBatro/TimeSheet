public class TimeSlot {

	private String day;
	private double start;
	private double end;

	public TimeSlot() {
		day = null;
		start = -1;
		end = -1;
	}

	public TimeSlot(String d) {
		day = d;
		start = 0;
		end = 0;
	}

	public TimeSlot(String d, double s) {
		day = d;
		start = s;
		end = 17.00;
	}

	public TimeSlot(String d, double s, double e) {
		day = d;
		start = s;
		end = e;
	}

	public void print() {
		System.out.println(day + ": " + start + " - " + end + " (" + time() + ")");
	}

	public String getDay() {
		return day;
	}

	public void setDay(String d) {
		day = d;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double s) {
		start = s;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double e) {
		end = e;
	}

	public double time() {
		return roundHundredth(end - start);
	}

	public static double roundHundredth(double num) {
		return (double)(Math.round(num * 100)) / 100;
	}
}