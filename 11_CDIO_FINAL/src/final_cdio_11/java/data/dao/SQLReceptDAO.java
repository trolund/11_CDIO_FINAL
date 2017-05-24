package final_cdio_11.java.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.ReceptDTO;

/*
 * Recept data access object implementation.
 * This class is used to manipulate Recept objects to and from the database.
 */
public class SQLReceptDAO implements IReceptDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLReceptDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive a Recept with receptId.
	 */
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		String getReceptSql = connector.getQuery("getReceptSql");
		PreparedStatement getReceptStmt = null;
		ResultSet rs = null;
		try {
			getReceptStmt = connector.getConnection().prepareStatement(getReceptSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getReceptStmt.setInt(1, receptId);
			rs = getReceptStmt.executeQuery();

			if (!rs.first()) throw new DALException("Recept with receptId [" + receptId + "] does not exist!");

			return new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getReceptStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing Recept's stored in the database.
	 */
	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		String getReceptListSql = connector.getQuery("getReceptListSql");
		List<ReceptDTO> receptList = new ArrayList<>();
		PreparedStatement getReceptListStmt = null;
		ResultSet rs = null;
		try {
			getReceptListStmt = connector.getConnection().prepareStatement(getReceptListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getReceptListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No Recept's exist!");

			do {
				receptList.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			} while (rs.next());
			return receptList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getReceptListStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new Recept stored in the database.
	 */
	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		String createReceptSql = connector.getQuery("createReceptSql");
		PreparedStatement createReceptStmt = null;
		try {
			createReceptStmt = connector.getConnection().prepareStatement(createReceptSql);
			createReceptStmt.setInt(1, recept.getReceptId());
			createReceptStmt.setString(2, recept.getReceptName());
			createReceptStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createReceptStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing Recept stored in the database.
	 */
	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		String updateReceptSql = connector.getQuery("updateReceptSql");
		PreparedStatement updateReceptStmt = null;
		try {
			updateReceptStmt = connector.getConnection().prepareStatement(updateReceptSql);
			updateReceptStmt.setString(1, recept.getReceptName());
			updateReceptStmt.setInt(2, recept.getReceptId());
			updateReceptStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updateReceptStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing Recept stored in the database.
	 */
	@Override
	public void deleteRecept(int receptId) throws DALException {
		String deleteReceptSql = connector.getQuery("deleteReceptSql");
		PreparedStatement deleteReceptStmt = null;
		try {
			deleteReceptStmt = connector.getConnection().prepareStatement(deleteReceptSql);
			deleteReceptStmt.setInt(1, receptId);
			deleteReceptStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deleteReceptStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}