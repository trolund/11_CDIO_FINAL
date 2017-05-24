package data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Connector;
import data.dto.RaavareDTO;

/*
 * Raavare data access object implementation.
 * This class is used to manipulate Raavare objects to and from the database.
 */
public class SQLRaavareDAO implements IRaavareDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLRaavareDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive an existing Raavare with raavareId.
	 */
	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		String getRaavareSql = connector.getSQL("getRaavareSql");
		PreparedStatement getRaavareStmt = null;
		ResultSet rs = null;
		try {
			getRaavareStmt = connector.getConnection().prepareStatement(getRaavareSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getRaavareStmt.setInt(1, raavareId);
			rs = getRaavareStmt.executeQuery();

			if (!rs.first()) throw new DALException("Raavare with raavareId [" + raavareId + "] does not exist!");

			return new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRaavareStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing Raavare's in the database.
	 */
	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		String getRaavareListSql = connector.getSQL("getRaavareListSql");
		List<RaavareDTO> raavareList = new ArrayList<>();
		PreparedStatement getRaavareListStmt = null;
		ResultSet rs = null;
		try {
			getRaavareListStmt = connector.getConnection().prepareStatement(getRaavareListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getRaavareListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No Raavare's exist!");

			do {
				raavareList.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			} while (rs.next());
			return raavareList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRaavareListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new Raavare stored in the database.
	 */
	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		String createRaavareSql = connector.getSQL("createRaavareSql");
		PreparedStatement createRaavareStmt = null;
		try {
			createRaavareStmt = connector.getConnection().prepareStatement(createRaavareSql);
			createRaavareStmt.setInt(1, raavare.getRaavareId());
			createRaavareStmt.setString(2, raavare.getraavareName());
			createRaavareStmt.setString(3, raavare.getSupplier());
			createRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createRaavareStmt);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing Raavare stored in the database.
	 */
	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		String updateRaavareSql = connector.getSQL("updateRaavareSql");
		PreparedStatement updateRaavareStmt = null;
		try {
			updateRaavareStmt = connector.getConnection().prepareStatement(updateRaavareSql);
			updateRaavareStmt.setString(1, raavare.getraavareName());
			updateRaavareStmt.setString(2, raavare.getSupplier());
			updateRaavareStmt.setInt(3, raavare.getRaavareId());
			updateRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updateRaavareStmt);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing Raavare stored in the database.
	 */
	@Override
	public void deleteRaavare(int raavareId) throws DALException {
		String deleteRaavareSql = connector.getSQL("deleteRaavareSql");
		PreparedStatement deleteRaavareStmt = null;
		try {
			deleteRaavareStmt = connector.getConnection().prepareStatement(deleteRaavareSql);
			deleteRaavareStmt.setInt(1, raavareId);
			deleteRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deleteRaavareStmt);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}