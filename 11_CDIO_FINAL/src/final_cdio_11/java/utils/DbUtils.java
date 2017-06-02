package final_cdio_11.java.utils;

import java.sql.SQLException;

import final_cdio_11.java.data.Connector;

public class DbUtils {

	private static final Connector connector = Connector.getInstance();

	public static void main(String[] args) {
		try {
			deleteAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteAllUsers() throws SQLException {
		System.err.println("Deleting all users....");
		connector.getConnection().prepareStatement("DELETE FROM rolle WHERE opr_id != 10").executeUpdate();
		connector.getConnection().prepareStatement("DELETE FROM operatoer WHERE opr_id != 10").executeUpdate();
		System.err.println("Done.");
	}

}