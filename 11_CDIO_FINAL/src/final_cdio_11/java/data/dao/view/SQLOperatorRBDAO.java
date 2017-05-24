package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VOperatorRBDTO;

public class SQLOperatorRBDAO implements IVOperatorRBDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLOperatorRBDAO(Connector connector) {
		this.connector = connector;
	}

	/* Returns the VOperatorRBDTO View associated with raavareId */
	@Override
	public List<VOperatorRBDTO> getVOperatorRB(int raavareId) throws DALException {
		String getVOperatorRBSql = connector.getQuery("getVOperatorRBSql");
		List<VOperatorRBDTO> vOperatorRBList = new ArrayList<>();
		PreparedStatement getOperatorRBStmt = null;
		ResultSet rs = null;
		try {
			getOperatorRBStmt = connector.getConnection().prepareStatement(getVOperatorRBSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getOperatorRBStmt.setInt(1, raavareId);
			rs = getOperatorRBStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries exist in VOperatorRBDTO!");

			do {
				vOperatorRBList.add(new VOperatorRBDTO(rs.getInt("raavare_id"), rs.getInt("rb_id"), rs.getInt("maengde"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			} while (rs.next());
			return vOperatorRBList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getOperatorRBStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/* Returns a list with all VOperatorRBDTO's */
	@Override
	public List<VOperatorRBDTO> getVOperatorRBList() throws DALException {
		String getVOperatorRBListSql = connector.getQuery("getVOperatorRBListSql");
		List<VOperatorRBDTO> vOperatorRBList = new ArrayList<>();
		PreparedStatement getVOperatorRBListStmt = null;
		ResultSet rs = null;
		try {
			getVOperatorRBListStmt = connector.getConnection().prepareStatement(getVOperatorRBListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getVOperatorRBListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries in VOperatorRB View exist!");

			do {
				vOperatorRBList.add(new VOperatorRBDTO(rs.getInt("raavare_id"), rs.getInt("rb_id"), rs.getInt("maengde"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			} while (rs.next());
			return vOperatorRBList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVOperatorRBListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}