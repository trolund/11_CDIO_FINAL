package main;

import data.Data;
import logic.Logic;
import ui.UI;

/*
 * Main class that initiates the application.
 */
public class Main {

	public static void main(String[] args) {
		new UI(new Logic(new Data()));
	}

}