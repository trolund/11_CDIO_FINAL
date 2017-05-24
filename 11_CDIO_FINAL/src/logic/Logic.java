package logic;

import data.IData;

/*
 * Logic layer implementation.
 */
public class Logic implements ILogic {

	private final IData data;

	public Logic(IData data) {
		this.data = data;
	}

}