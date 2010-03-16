package holiday.ctrl;

import holiday.gui.MainFrame;
import holiday.subsys.DateCalculator;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Calendar;
//import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;


@SuppressWarnings("serial")
public class HolidayController extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getValue(MainFrame.ComponentNames.JBNextHoliday
				.toString())) {
			nextHoliday();
		}
	}

	private void nextHoliday() {
		JCalendar jcal = (JCalendar)getValue(MainFrame.ComponentNames.JPCalendar.toString());
		Calendar nextHoliday = DateCalculator.getInstance().nextHoliday(jcal.getCalendar());
//		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
		JOptionPane.showMessageDialog(null, "Next Swedish holiday: " + formatter.format(nextHoliday.getTime()));
//		JOptionPane.showMessageDialog(null, "Nästa helgdag: " + formatter.format(nextHoliday.getTime()));
	}
}