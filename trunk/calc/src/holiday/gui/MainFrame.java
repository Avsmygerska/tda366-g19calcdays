package holiday.gui;

import holiday.ctrl.ControllerFactory;
import holiday.ctrl.ControllerFactory.Ctrl;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static enum ComponentNames {
		JBNextHoliday, JPCalendar
	};

	private JButton jbNextHoliday;
	private JCalendar jpCalendar;

	public MainFrame() {
		initGUI();
		initCtrl();
		setNames();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Holiday");
		pack();
	}

	private void initGUI() {
		// BorderLayout by default
		Container pane = getContentPane();
		jpCalendar = new JCalendar();
		jbNextHoliday = new JButton();
		pane.add(jpCalendar, BorderLayout.CENTER);
		pane.add(jbNextHoliday, BorderLayout.PAGE_END);
	}

	private void initCtrl() {
		Action ctrl = ControllerFactory.getControl(Ctrl.HOLIDAY_CTRL);
		jbNextHoliday.setAction(ctrl);
		ctrl.putValue(ComponentNames.JBNextHoliday.toString(), jbNextHoliday);
		ctrl.putValue(ComponentNames.JPCalendar.toString(), jpCalendar);
	}

	private void setNames() {
		jbNextHoliday.setText("Next holiday, please!");
	}
}