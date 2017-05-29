package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VForemanOperatorDTO;

public class SQLForemanOperatorDAO implements IVForemanOperatorDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLForemanOperatorDAO(Connector connector) {
		this.connector = connector;
	}

	/* Method to return a VForemanOperatorDTO with oprId */
	@Override
	public VForemanOperatorDTO getVForemanOperator(int oprId) throws DALException {
		String getVForemanOperatorSql = connector.getQuery("getVForemanOperatorSql");
		PreparedStatement getVForemanOperatorStmt = null;
		ResultSet rs = null;
		try {
			getVForemanOperatorStmt = connector.getConnection().prepareStatement(getVForemanOperatorSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getVForemanOperatorStmt.setInt(1, oprId);
			rs = getVForemanOperatorStmt.executeQuery();

			if (!rs.first()) throw new DALException("Entry with id [" + oprId + "] does not exist!");

			return new VForemanOperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("opr_roller"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVForemanOperatorStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}

	}

	/* Method to return a list of all VForemanOperatorDTO's */
	@Override
	public List<VForemanOperatorDTO> getVForemanOperatorList() throws DALException {
		String getVForemanOperatorListSql = connector.getQuery("getVForemanOperatorListSql");
		List<VForemanOperatorDTO> vForemanOperatorList = new ArrayList<>();
		PreparedStatement getVForemanOperatorListStmt = null;
		ResultSet rs = null;
		try {
			getVForemanOperatorListStmt = connector.getConnection().prepareStatement(getVForemanOperatorListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getVForemanOperatorListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries in VForemanOperator View exist!");

			do {
				vForemanOperatorList.add(new VForemanOperatorDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("opr_roller")));
			} while (rs.next());

			return vForemanOperatorList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVForemanOperatorListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}