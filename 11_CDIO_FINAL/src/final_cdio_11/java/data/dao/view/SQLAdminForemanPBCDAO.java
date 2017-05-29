package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;

public class SQLAdminForemanPBCDAO implements IVAdminForemanPBCDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLAdminForemanPBCDAO(Connector connector) {
		this.connector = connector;
	}

	/* Returns the list with the VAdminForemanPBC view for Operator with oprId */
	@Override
	public List<VAdminForemanPBCDTO> getVAdminForemanPBC(int oprId) throws DALException {
		String getVAdminForemanPBCSql = connector.getQuery("getVAdminForemanPBCSql");
		List<VAdminForemanPBCDTO> vAdminForemanPBCList = new ArrayList<>();
		PreparedStatement getVAdminForemanPBCStmt = null;
		ResultSet rs = null;
		try {
			getVAdminForemanPBCStmt = connector.getConnection().prepareStatement(getVAdminForemanPBCSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getVAdminForemanPBCStmt.setInt(1, oprId);
			rs = getVAdminForemanPBCStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries with oprId [" + oprId + "] exist!");

			do {
				vAdminForemanPBCList.add(new VAdminForemanPBCDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("status")));
			} while (rs.next());
			
			return vAdminForemanPBCList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVAdminForemanPBCStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/* Returns the list with the VAdminForemanPBC view for all Operators */
	@Override
	public List<VAdminForemanPBCDTO> getVAdminForemanPBCList() throws DALException {
		String getVAdminForemanPBCListSql = connector.getQuery("getVAdminForemanPBCListSql");
		List<VAdminForemanPBCDTO> vAdminForemanPBCList = new ArrayList<>();
		PreparedStatement getVAdminForemanPBCListStmt = null;
		ResultSet rs = null;
		try {
			getVAdminForemanPBCListStmt = connector.getConnection().prepareStatement(getVAdminForemanPBCListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getVAdminForemanPBCListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries in VAdminForemanPBCList exist!");

			do {
				vAdminForemanPBCList.add(new VAdminForemanPBCDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("status")));
			} while (rs.next());
			
			return vAdminForemanPBCList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVAdminForemanPBCListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}