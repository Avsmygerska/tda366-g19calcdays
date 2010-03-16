package holiday.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

public final class ControllerFactory {

	public enum Ctrl {
		HOLIDAY_CTRL;
	}

	private final static Map<Ctrl, Action> ctrls = new HashMap<Ctrl, Action>();
	
	static {
		ctrls.put(Ctrl.HOLIDAY_CTRL, new HolidayController());
	}

	public static Action getControl(Ctrl ctrl) {
		return ctrls.get(ctrl);
	}

}