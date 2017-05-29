package final_cdio_11.java.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.RaavareBatchDTO;

/*
 * RaavareBatch data access object implementation.
 * This class is used to manipulate RaavareBatch objects to and from the database.
 */
public class SQLRaavareBatchDAO implements IRaavareBatchDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLRaavareBatchDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive an existing RaavareBatch with rbId.
	 */
	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		String getRBSql = connector.getQuery("getRBSql");
		PreparedStatement getRBStmt = null;
		ResultSet rs = null;
		try {
			getRBStmt = connector.getConnection().prepareStatement(getRBSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getRBStmt.setInt(1, rbId);
			rs = getRBStmt.executeQuery();

			if (!rs.first()) throw new DALException("RaavareBatch with rbId [" + rbId + "] does not exist!");

			return new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"), rs.getInt("status"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRBStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing RaavareBatch's in the database.
	 */
	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		String getRBListSql = connector.getQuery("getRBListSql");
		List<RaavareBatchDTO> rbList = new ArrayList<>();
		PreparedStatement getRBListStmt = null;
		ResultSet rs = null;
		try {
			getRBListStmt = connector.getConnection().prepareStatement(getRBListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getRBListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No RaavareBatch's exist!");

			do {
				rbList.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"), rs.getInt("status")));
			} while (rs.next());
			
			return rbList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRBListStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing RaavareBatch's in the database with raavareId.
	 */
	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		String getRBListIdSql = connector.getQuery("getRBListIdSql");
		List<RaavareBatchDTO> rbList = new ArrayList<>();
		PreparedStatement getRBListIdStmt = null;
		ResultSet rs = null;
		try {
			getRBListIdStmt = connector.getConnection().prepareStatement(getRBListIdSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getRBListIdStmt.setInt(1, raavareId);
			rs = getRBListIdStmt.executeQuery();

			if (!rs.first()) throw new DALException("No RaavareBatch's exist with raavareId: " + raavareId + "!");

			do {
				rbList.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"), rs.getInt("status")));
			} while (rs.next());
			
			return rbList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getRBListIdStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new RaavareBatch stored in the database.
	 */
	@Override
	public void createRaavareBatch(RaavareBatchDTO rbDTO) throws DALException {
		String createRBSql = connector.getQuery("createRBSql");
		PreparedStatement createRBStmt = null;
		try {
			createRBStmt = connector.getConnection().prepareStatement(createRBSql);
			createRBStmt.setInt(1, rbDTO.getrBId());
			createRBStmt.setInt(2, rbDTO.getRaavareId());
			createRBStmt.setDouble(3, rbDTO.getAmount());
			createRBStmt.setInt(4, rbDTO.getStatus());
			createRBStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createRBStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing RaavareBatch stored in the database.
	 */
	@Override
	public void updateRaavareBatch(RaavareBatchDTO rbDTO) throws DALException {
		String updateRBCSql = connector.getQuery("updateRBSql");
		PreparedStatement updateRBStmt = null;
		try {
			updateRBStmt = connector.getConnection().prepareStatement(updateRBCSql);
			updateRBStmt.setDouble(1, rbDTO.getAmount());
			updateRBStmt.setInt(2, rbDTO.getStatus());
			updateRBStmt.setInt(3, rbDTO.getrBId());
			updateRBStmt.setInt(4, rbDTO.getRaavareId());
			updateRBStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updateRBStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing RaavareBatch stored in the database.
	 */
	@Override
	public void deleteRaavareBatch(int rbId) throws DALException {
		String deleteRBSql = connector.getQuery("deleteRBSql");
		PreparedStatement deleteRBStmt = null;
		try {
			deleteRBStmt = connector.getConnection().prepareStatement(deleteRBSql);
			deleteRBStmt.setInt(1, rbId);
			deleteRBStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deleteRBStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}