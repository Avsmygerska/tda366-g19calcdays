package holiday.subsys;

import java.util.Calendar;

public interface IDateCalculator {
	Calendar nextHoliday(Calendar date);
}