package final_cdio_11.java.data.dbutils;

import java.sql.SQLException;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.utils.Utils;

public class DbUtils {

	private static final Connector connector = Connector.getInstance();
	private static final Utils utils = Utils.getInstance();

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