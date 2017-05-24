package data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Connector;
import data.dto.ReceptComponentDTO;

/*
 * ReceptComponent data access object implementation.
 * This class is used to manipulate ReceptComponent objects to and from the database.
 */
public class SQLReceptComponentDAO implements IReceptComponentDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLReceptComponentDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive an existing ReceptComponent with receptId and raavareId
	 * stored in the database.
	 */
	@Override
	public ReceptComponentDTO getReceptComponent(int receptId, int raavareId) throws DALException {
		String getRCSql = connector.getSQL("getRCSql");
		PreparedStatement getRCStmt = null;
		ResultSet rs = null;
		try {
			getRCStmt = connector.getConnection().prepareStatement(getRCSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getRCStmt.setInt(1, receptId);
			getRCStmt.setInt(2, raavareId);
			rs = getRCStmt.executeQuery();

			if (!rs.first()) throw new DALException("ReceptComponent with receptId [" + receptId + "] and raavareId [" + raavareId + "] does not exist!");

			return new ReceptComponentDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRCStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing ReceptComponent's with receptId.
	 */
	@Override
	public List<ReceptComponentDTO> getReceptComponentList(int receptId) throws DALException {
		String getRCListIdSql = connector.getSQL("getRCListIdSql");
		List<ReceptComponentDTO> rcList = new ArrayList<>();
		PreparedStatement getRCListIdStmt = null;
		ResultSet rs = null;
		try {
			getRCListIdStmt = connector.getConnection().prepareStatement(getRCListIdSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getRCListIdStmt.setInt(1, receptId);
			rs = getRCListIdStmt.executeQuery();

			if (!rs.first()) throw new DALException("No ReceptComponent's exist with receptId: " + receptId + "!");

			do {
				rcList.add(new ReceptComponentDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getInt("tolerance")));
			} while (rs.next());
			return rcList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRCListIdStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing ReceptComponent's stored in the database.
	 */
	@Override
	public List<ReceptComponentDTO> getReceptComponentList() throws DALException {
		String getRCListSql = connector.getSQL("getRCListSql");
		List<ReceptComponentDTO> rcList = new ArrayList<>();
		PreparedStatement getRCListStmt = null;
		ResultSet rs = null;
		try {
			getRCListStmt = connector.getConnection().prepareStatement(getRCListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getRCListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No ReceptComponent's exist!");

			do {
				rcList.add(new ReceptComponentDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getInt("tolerance")));
			} while (rs.next());
			return rcList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRCListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new ReceptComponent in the database.
	 */
	@Override
	public void createReceptComponent(ReceptComponentDTO rc) throws DALException {
		String createRCSql = connector.getSQL("createRCSql");
		PreparedStatement createRCStmt = null;
		try {
			createRCStmt = connector.getConnection().prepareStatement(createRCSql);
			createRCStmt.setInt(1, rc.getReceptId());
			createRCStmt.setInt(2, rc.getRaavareId());
			createRCStmt.setDouble(3, rc.getNomNetto());
			createRCStmt.setDouble(4, rc.getTolerance());
			createRCStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createRCStmt);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing ReceptComponent in the database.
	 */
	@Override
	public void updateReceptComponent(ReceptComponentDTO rc) throws DALException {
		String updateRCSql = connector.getSQL("updateRCSql");
		PreparedStatement updateRCStmt = null;
		try {
			updateRCStmt = connector.getConnection().prepareStatement(updateRCSql);
			updateRCStmt.setDouble(1, rc.getNomNetto());
			updateRCStmt.setDouble(2, rc.getTolerance());
			updateRCStmt.setInt(3, rc.getReceptId());
			updateRCStmt.setInt(4, rc.getRaavareId());
			updateRCStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updateRCStmt);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing ReceptComponent in the database.
	 */
	@Override
	public void deleteReceptComponent(int receptId, int raavareId) throws DALException {
		String deleteRCSql = connector.getSQL("deleteRCSql");
		PreparedStatement deleteRCStmt = null;
		try {
			deleteRCStmt = connector.getConnection().prepareStatement(deleteRCSql);
			deleteRCStmt.setInt(1, receptId);
			deleteRCStmt.setInt(2, raavareId);
			deleteRCStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deleteRCStmt);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}