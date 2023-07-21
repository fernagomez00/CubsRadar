package v1;

import java.time.LocalDate;

public class Date{
	
	final LocalDate currentDate = LocalDate.now();
	
	public Date() {}
	
	public String toString() {
		
		return currentDate.getMonthValue() + "/" + currentDate.getDayOfMonth() + "/" + currentDate.getYear();
		
	}

}
