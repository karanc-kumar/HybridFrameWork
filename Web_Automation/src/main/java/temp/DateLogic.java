package temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLogic {

	public static void main(String[] args) throws Exception {
		
		Date d= new Date();
		System.out.println("Date :: "+d.toString());
		
		String df="31/05/2020";
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		Date d2=sdf.parse(df);
		System.out.println("Parsed date : "+d2.toString());
		
		sdf=new SimpleDateFormat("dd");
		String day=sdf.format(d2);
		System.out.println("Day::"+day);
		
		sdf=new SimpleDateFormat("MMM");
		String month=sdf.format(d2);
		System.out.println("Month::"+month);
		
		sdf=new SimpleDateFormat("yyyy");
		String year=sdf.format(d2);
		System.out.println("Year"+year);
		
		
		
		
		System.out.println("1 if current date is less than given date, -1 if current date is greater than given date, 0 if both are equal :: "+d2.compareTo(d));
		

	}

}
