package final_cdio_11.java.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.utils.SecUtils;

/*
 * Operator data access object implementation.
 * This class is used to manipulate Operator objects to and from the database.
 */
public class SQLOperatorDAO implements IOperatorDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLOperatorDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive an existing Operator with oprId.
	 */
	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		String getOprSql = connector.getQuery("getOprSql");
		PreparedStatement getOprStmt = null;
		ResultSet rs = null;
		try {
			getOprStmt = connector.getConnection().prepareStatement(getOprSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getOprStmt.setInt(1, oprId);
			rs = getOprStmt.executeQuery();

			if (!rs.first()) throw new DALException("Operator id [" + oprId + "] does not exist!");

			return new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getOprStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing Operator's in the database.
	 */
	@Override
	public List<OperatorDTO> getOperatorList() throws DALException {
		String getOprListSql = connector.getQuery("getOprListSql");
		List<OperatorDTO> oprList = new ArrayList<>();
		PreparedStatement getOprListStmt = null;
		ResultSet rs = null;
		try {
			getOprListStmt = connector.getConnection().prepareStatement(getOprListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getOprListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No operators exist!");

			do {
				oprList.add(new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			} while (rs.next());
			return oprList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getOprListStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new Operator stored in the database.
	 */
	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		String createOprSql = connector.getQuery("createOprSql");
		PreparedStatement createOprStmt = null;
		try {
			createOprStmt = connector.getConnection().prepareStatement(createOprSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			createOprStmt.setInt(1, opr.getOprId());
			createOprStmt.setString(2, opr.getOprName());
			createOprStmt.setString(3, opr.getOprIni());
			createOprStmt.setString(4, opr.getOprCpr());
			createOprStmt.setString(5, SecUtils.getInstance().sha256(opr.getOprPassword()));
			createOprStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createOprStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing Operator stored inside the database.
	 */
	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {
		String updateOprSql = connector.getQuery("updateOprSql");
		PreparedStatement updateOprStmt = null;
		try {
			updateOprStmt = connector.getConnection().prepareStatement(updateOprSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			updateOprStmt.setString(1, opr.getOprName());
			updateOprStmt.setString(2, opr.getOprIni());
			updateOprStmt.setString(3, opr.getOprCpr());
			updateOprStmt.setString(4, SecUtils.getInstance().sha256(opr.getOprPassword()));
			updateOprStmt.setInt(5, opr.getOprId());
			updateOprStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updateOprStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing Operator stored inside the database.
	 */
	@Override
	public void deleteOperator(int oprId) throws DALException {
		String deleteOprRolesSql = connector.getQuery("deleteOprRolesSql");
		String deleteOprSql = connector.getQuery("deleteOprSql");
		PreparedStatement deleteOprRolesStmt = null;
		PreparedStatement deleteOprStmt = null;
		try {
			deleteOprRolesStmt = connector.getConnection().prepareStatement(deleteOprRolesSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			deleteOprStmt = connector.getConnection().prepareStatement(deleteOprSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			deleteOprRolesStmt.setInt(1, oprId);
			deleteOprStmt.setInt(1, oprId);

			deleteOprRolesStmt.executeUpdate();
			deleteOprStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deleteOprStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}