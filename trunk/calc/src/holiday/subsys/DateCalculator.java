package holiday.subsys;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DateCalculator implements IDateCalculator {
	private static final IDateCalculator instance = new DateCalculator();
	
	public static IDateCalculator getInstance() {
		return instance;
	}

	private DateCalculator() {
		// Setting default time zone to European Central Time
		TimeZone.setDefault(TimeZone.getTimeZone("ECT"));
	}

	@Override
	public Calendar nextHoliday(Calendar date) {
		List<Calendar> list = swedishHolidays(date.get(Calendar.YEAR));
		// If element not found, binary search returns 
		// the negative index of next following element in list
		return list.get(Math.abs(Collections.binarySearch(list,date)));
	}
	


	private List<Calendar> swedishHolidays(int year) {
		List<Calendar> hs = new ArrayList<Calendar>();
	
		Calendar cal = null;
		
		// Obs: Calendar class has month zero-based
		
		// Holidays and comments below taken from: 
		// http://sv.wikipedia.org/wiki/Helgdagar
		
		// nyårsdagen
		cal = Calendar.getInstance();
		cal.set(year, 0, 1);
		hs.add(cal);

		// trettondedag jul
		cal = Calendar.getInstance();
		cal.set(year, 0, 6);
		hs.add(cal);
		
		Calendar easter = easterDay(year);
		
		// långfredagen - rörlig, fredagen närmast före påskdagen
		cal = Calendar.getInstance();
		easter.add(Calendar.DATE, -2);
		cal.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DATE));
		hs.add(cal);

		// påskdagen - rörlig, söndagen närmast efter den fullmåne som infaller på eller närmast efter den 21 mars
		cal = Calendar.getInstance();
		easter.add(Calendar.DATE, 2);
		cal.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DATE));
		hs.add(cal);
		
		// annandag påsk- rörlig, dagen efter påskdagen
		cal = Calendar.getInstance();
		easter.add(Calendar.DATE, 1);
		cal.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DATE));
		hs.add(cal);

		// första maj
		cal = Calendar.getInstance();
		cal.set(year, 4, 1);
		hs.add(cal);

		// Kristi himmelsfärdsdag - rörlig, sjätte torsdagen efter påskdagen (39 dagar efter påskdagen)
		cal = Calendar.getInstance();
		easter.add(Calendar.DATE, 38);
		cal.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DATE));
		hs.add(cal);

		// pingstdagen - rörlig, sjunde söndagen efter påskdagen (49 dagar efter påskdagen)
		cal = Calendar.getInstance();
		easter.add(Calendar.DATE, 10);
		cal.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DATE));
		hs.add(cal);

		// Sveriges nationaldag- 6 juni
		cal = Calendar.getInstance();
		cal.set(year, 5, 6);
		hs.add(cal);

		// midsommardagen - rörlig, den lördag som infaller vecka 25
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, 25);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		hs.add(cal);

		// alla helgons dag - rörlig, den lördag som infaller under vecka 44
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, 44);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		hs.add(cal);

		// juldagen - 25 december
		cal = Calendar.getInstance();
		cal.set(year, 11, 25);
		hs.add(cal);

		// annandag jul - 26 december
		cal = Calendar.getInstance();
		cal.set(year, 11, 26);
		hs.add(cal);

		// First holiday next year
		cal = Calendar.getInstance();
		cal.set(year+1, 0, 1);
		hs.add(cal);

	    // Order is crucial! sorting to be sure
		Collections.sort(hs);

		return hs;
	}
	
	private Calendar easterDay(int year) {
		// Easter calculation according to Gregorian calendar
		// Attribution to: Antonio Luque Estepa
		// http://woody.us.es/~aluque/proy/easter.html
		int g = year % 19;
		int c = year/100;
		int h = (c - c/4 - (8*c+13)/25 + 19*g + 15) % 30;
		int i = h - (h/28)*(1 - (h/28)*(29/(h + 1))*((21 - g)/11));
		int j = (year + year/4 + i + 2 - c + c/4) % 7;
		int l = i - j;
		int easterMonth = 3 + (l + 40)/44;
		int easterDay = l + 28 - 31*(easterMonth/4);

		Calendar cal = Calendar.getInstance();
		cal.set(year, easterMonth-1, easterDay); // Month is zero-based
		
		return cal;
	}

}