package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

public class SQLVUserTableDAO implements IVUserTableDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLVUserTableDAO(Connector connector) {
		this.connector = connector;
	}

	/* Returns the VUserTable view associated with oprId */
	@Override
	public VUserTableDTO getVUserTable(int oprId) throws DALException {
		String getVUserTableSql = connector.getQuery("getVUserTableSql");
		PreparedStatement getVUserTableStmt = null;
		ResultSet rs = null;

		try {
			getVUserTableStmt = connector.getConnection().prepareStatement(getVUserTableSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getVUserTableStmt.setInt(1, oprId);
			rs = getVUserTableStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries with [" + oprId + "] exist!");

			return new VUserTableDTO(rs.getInt("status"), rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("opr_efternavn"), rs.getString("ini"), rs.getString("email"), rs.getString("cpr"), rs.getString("opr_rolle"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVUserTableStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/* Returns a list of all the objects contained in the VUserTable view */
	@Override
	public List<VUserTableDTO> getVUserTableList() throws DALException {
		String getVUserTableListSql = connector.getQuery("getVUserTableListSql");
		List<VUserTableDTO> userTableList = new ArrayList<>();
		PreparedStatement getVUserTableListStmt = null;
		ResultSet rs = null;

		try {
			getVUserTableListStmt = connector.getConnection().prepareStatement(getVUserTableListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getVUserTableListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries in VAdminOperator view exist!");

			do {
				userTableList.add(new VUserTableDTO(rs.getInt("status"), rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("opr_efternavn"), rs.getString("ini"), rs.getString("email"), rs.getString("cpr"), rs.getString("opr_rolle")));
			} while (rs.next());

			return userTableList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVUserTableListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}