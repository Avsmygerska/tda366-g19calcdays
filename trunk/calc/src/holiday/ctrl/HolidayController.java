package holiday.ctrl;

import holiday.gui.MainFrame;
import holiday.subsys.DateCalculator;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;


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
		Calendar nextHoliday = DateCalculator.getInstance().nextHoliday(Calendar.getInstance());
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
		JOptionPane.showMessageDialog(null, "Nästa helgdag: " + formatter.format(nextHoliday.getTime()));
	}
}