package holiday;

import holiday.gui.MainFrame;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) {
		// This is the way to construct the GUI
		// (in the event thread)
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				MainFrame frame = new MainFrame();
//				frame.setLocationRelativeTo(null);
//				frame.setVisible(true);
			}
		});
	}
}