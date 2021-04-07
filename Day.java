public class Day implements Cloneable {
	private int year;
	private int month;
	private int day;

	// Constructor
	public Day(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}

	public Day(String sDay) {
		set(sDay);
	}

	@Override
	public String toString() {
		return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
	}

	@Override
	public Day clone() {
		Day copy = null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return copy;
	}

	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		else if (y % 100 == 0)
			return false;
		else if (y % 4 == 0)
			return true;
		else
			return false;
	}

	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		if (m < 1 || m > 12 || d < 1)
			return false;
		switch (m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return d <= 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return d <= 30;
		case 2:
			if (isLeapYear(y))
				return d <= 29;
			else
				return d <= 28;
		}
		return false;
	}

	public void set(String sDay) // Set year,month,day based on a string like 2020-01-31
	{
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[2]);
		this.month = Integer.parseInt(sDayParts[1]);
		this.year = Integer.parseInt(sDayParts[0]);
	}

}
