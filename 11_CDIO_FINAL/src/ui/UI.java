package ui;

import logic.ILogic;

/*
 * User interface implementation.
 */
public class UI implements IUI {

	private final ILogic logic;

	public UI(ILogic logic) {
		this.logic = logic;
	}

}