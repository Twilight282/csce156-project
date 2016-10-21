import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateData {
	
	public static int elapsedDays(String a, String b){ //let it be a given that a and b are both of format yyyy-mm-dd
		String[] partsa = a.split("-");
		String[] partsb = b.split("-");
		
		Calendar first = new GregorianCalendar(Integer.parseInt(partsa[0]), Integer.parseInt(partsa[1]) - 1, Integer.parseInt(partsa[2]));
		Calendar second = new GregorianCalendar(Integer.parseInt(partsb[0]), Integer.parseInt(partsb[1]) - 1, Integer.parseInt(partsb[2]));

	    double elapse = Math.abs(first.getTimeInMillis() - second.getTimeInMillis());
	    elapse = elapse / (86400000); // converstion from milliseconds to days
	    int elapsedInt = (int)elapse; // rounds the day count down
	    return elapsedInt;
	}
	
	public static boolean tuesOrThurs(String a){ //makes the same format assumptions as above
		String[] partsa = a.split("-");	
		
		Calendar date = new GregorianCalendar(Integer.parseInt(partsa[0]), Integer.parseInt(partsa[1]) - 1, Integer.parseInt(partsa[2]));

		int day = date.get(Calendar.DAY_OF_WEEK); //values 1-7 sunday-saturday
		return (day == 3 || day == 5); //returns true if the day is 3 or 5 (tuesday or thursday)
	}
}
