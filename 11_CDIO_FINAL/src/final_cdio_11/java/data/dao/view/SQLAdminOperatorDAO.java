package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VAdminOperatorDTO;

public class SQLAdminOperatorDAO implements IVAdminOperatorDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLAdminOperatorDAO(Connector connector) {
		this.connector = connector;
	}

	/* Returns the VAdminOperator view associated with oprId */
	@Override
	public VAdminOperatorDTO getVAdminOperator(int oprId) throws DALException {
		String getVAdminOperatorSql = connector.getQuery("getVAdminOperatorSql");
		PreparedStatement getVAdminOperatorStmt = null;
		ResultSet rs = null;
		try {
			getVAdminOperatorStmt = connector.getConnection().prepareStatement(getVAdminOperatorSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getVAdminOperatorStmt.setInt(1, oprId);
			rs = getVAdminOperatorStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries with [" + oprId + "] exist!");

			return new VAdminOperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getString("opr_rolle"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVAdminOperatorStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/* Returns a list of all the objects contained in the VAdminOperator view */
	@Override
	public List<VAdminOperatorDTO> getVAdminOperatorList() throws DALException {
		String getVAdminOperatorListSql = connector.getQuery("getVAdminOperatorListSql");
		List<VAdminOperatorDTO> vAdminOperatorList = new ArrayList<>();
		PreparedStatement getVAdminOperatorListStmt = null;
		ResultSet rs = null;
		try {
			getVAdminOperatorListStmt = connector.getConnection().prepareStatement(getVAdminOperatorListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = getVAdminOperatorListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries in VAdminOperator view exist!");

			do {
				vAdminOperatorList.add(new VAdminOperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getString("opr_rolle")));
			} while (rs.next());
			return vAdminOperatorList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVAdminOperatorListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}