package final_cdio_11.java.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.ProductBatchComponentDTO;

/*
 * ProductBatchComponent data access object implementation.
 * This class is used to manipulate ProductBatchComponent objects to and from the database.
 */
public class SQLProductBatchComponentDAO implements IProductBatchComponentDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLProductBatchComponentDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive an existing ProductBatchComponent with pbId and rbId.
	 */
	@Override
	public ProductBatchComponentDTO getProductBatchComponent(int pbId, int rbId) throws DALException {
		String getPBCSql = connector.getQuery("getPBCSql");
		PreparedStatement getPBCStmt = null;
		ResultSet rs = null;
		try {
			getPBCStmt = connector.getConnection().prepareStatement(getPBCSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getPBCStmt.setInt(1, pbId);
			getPBCStmt.setInt(2, rbId);
			rs = getPBCStmt.executeQuery();

			if (!rs.first()) throw new DALException("ProductBatchComponent with pbId [" + pbId + "] and rbId [" + rbId + "] does not exist!");

			return new ProductBatchComponentDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"), rs.getInt("status"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getPBCStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing ProductBatchComponent's with pbId.
	 */
	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException {
		String getPBCListSql = connector.getQuery("getPBCListIdSql");
		List<ProductBatchComponentDTO> pbcList = new ArrayList<>();
		PreparedStatement getPBCListStmt = null;
		ResultSet rs = null;
		try {
			getPBCListStmt = connector.getConnection().prepareStatement(getPBCListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getPBCListStmt.setInt(1, pbId);
			rs = getPBCListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No ProductBatchComponent's exist with pbId: " + pbId + "!");

			do {
				pbcList.add(new ProductBatchComponentDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"), rs.getInt("status")));
			} while (rs.next());

			return pbcList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getPBCListStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing ProductBatchComponent's in the database.
	 */
	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException {
		String getPBCListSql = connector.getQuery("getPBCListSql");
		List<ProductBatchComponentDTO> pbcList = new ArrayList<>();
		PreparedStatement getPBCListStmt = null;
		ResultSet rs = null;
		try {
			getPBCListStmt = connector.getConnection().prepareStatement(getPBCListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getPBCListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No ProductBatchComponent's exist!");

			do {
				pbcList.add(new ProductBatchComponentDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"), rs.getInt("status")));
			} while (rs.next());

			return pbcList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getPBCListStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new ProductBatchComponent stored in the database.
	 */
	@Override
	public void createProductBatchComponent(ProductBatchComponentDTO pbc) throws DALException {
		String createPBCSql = connector.getQuery("createPBCSql");
		PreparedStatement createPBCStmt = null;
		try {
			createPBCStmt = connector.getConnection().prepareStatement(createPBCSql);
			createPBCStmt.setInt(1, pbc.getpbId());
			createPBCStmt.setInt(2, pbc.getrbId());
			createPBCStmt.setDouble(3, pbc.getTara());
			createPBCStmt.setDouble(4, pbc.getNetto());
			createPBCStmt.setInt(5, pbc.getOprId());
			createPBCStmt.setInt(6, pbc.getStatus());
			createPBCStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createPBCStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing ProductBatchComponent stored inside the database.
	 */
	@Override
	public void updateProductBatchComponent(ProductBatchComponentDTO pbc) throws DALException {
		String updatePBCSql = connector.getQuery("updatePBCSql");
		PreparedStatement updatePBCStmt = null;
		try {
			updatePBCStmt = connector.getConnection().prepareStatement(updatePBCSql);
			updatePBCStmt.setDouble(1, pbc.getTara());
			updatePBCStmt.setDouble(2, pbc.getNetto());
			updatePBCStmt.setInt(3, pbc.getStatus());
			updatePBCStmt.setInt(4, pbc.getpbId());
			updatePBCStmt.setInt(5, pbc.getrbId());
			updatePBCStmt.setInt(6, pbc.getOprId());
			updatePBCStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updatePBCStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing ProductBatchComponent stored inside the database.
	 */
	@Override
	public void deleteProductBatchComponent(int pbId, int rbId) throws DALException {
		String deletePBCSql = connector.getQuery("deletePBCSql");
		PreparedStatement deletePBCStmt = null;
		try {
			deletePBCStmt = connector.getConnection().prepareStatement(deletePBCSql);
			deletePBCStmt.setInt(1, pbId);
			deletePBCStmt.setInt(2, rbId);
			deletePBCStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deletePBCStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}