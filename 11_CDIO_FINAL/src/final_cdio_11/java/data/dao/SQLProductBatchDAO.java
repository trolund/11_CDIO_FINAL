package final_cdio_11.java.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.utils.Utils;

/*
 * ProductBatch data access object implementation.
 * This class is used to manipulate ProductBatch objects to and from the database.
 */
public class SQLProductBatchDAO implements IProductBatchDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLProductBatchDAO(Connector connector) {
		this.connector = connector;
	}

	/*
	 * Method to receive an existing ProductBatch with pbId.
	 */
	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		String getPBSql = connector.getQuery("getPBSql");
		PreparedStatement getPBStmt = null;
		ResultSet rs = null;
		try {
			getPBStmt = connector.getConnection().prepareStatement(getPBSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getPBStmt.setInt(1, pbId);
			rs = getPBStmt.executeQuery();

			if (!rs.first()) throw new DALException("ProductBatch with pbId [" + pbId + "] does not exist!");

			return new ProductBatchDTO(rs.getInt("pb_id"), rs.getInt("item_status"), rs.getInt("recept_id"), rs.getInt("status"));
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getPBStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to receive all existing ProductBatch's stored in the database.
	 */
	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		String getPBListSql = connector.getQuery("getPBListSql");
		List<ProductBatchDTO> pbList = new ArrayList<>();
		PreparedStatement getPBListStmt = null;
		ResultSet rs = null;
		try {
			getPBListStmt = connector.getConnection().prepareStatement(getPBListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getPBListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No ProductBatch's exist!");

			Utils.getInstance().sleep(100);

			do {
				pbList.add(new ProductBatchDTO(rs.getInt("pb_id"), rs.getInt("item_status"), rs.getInt("recept_id"), rs.getInt("status")));
			} while (rs.next());

			return pbList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getPBListStmt, rs);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to create a new ProductBatch in the database.
	 */
	@Override
	public void createProductBatch(ProductBatchDTO pbDTO) throws DALException {
		String createPBSql = connector.getQuery("createPBSql");
		PreparedStatement createPBStmt = null;
		try {
			createPBStmt = connector.getConnection().prepareStatement(createPBSql);
			createPBStmt.setInt(1, pbDTO.getPbId());
			createPBStmt.setInt(2, pbDTO.getItemStatus());
			createPBStmt.setInt(3, pbDTO.getReceptId());
			createPBStmt.setInt(4, pbDTO.getStatus());
			createPBStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(createPBStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to update an existing ProductBatch stored in the database.
	 */
	@Override
	public void updateProductBatch(ProductBatchDTO pbDTO) throws DALException {
		String updatePBSql = connector.getQuery("updatePBSql");
		PreparedStatement updatePBStmt = null;
		try {
			updatePBStmt = connector.getConnection().prepareStatement(updatePBSql);
			updatePBStmt.setInt(1, pbDTO.getItemStatus());
			updatePBStmt.setInt(2, pbDTO.getStatus());
			updatePBStmt.setInt(3, pbDTO.getReceptId());
			updatePBStmt.setInt(4, pbDTO.getPbId());
			updatePBStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(updatePBStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/*
	 * Method to delete an existing ProductBatch stored in the database.
	 */
	@Override
	public void deleteProductBatch(int pbId) throws DALException {
		String deletePBSql = connector.getQuery("deletePBSql");
		PreparedStatement deletePBStmt = null;
		try {
			deletePBStmt = connector.getConnection().prepareStatement(deletePBSql);
			deletePBStmt.setInt(1, pbId);
			deletePBStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(deletePBStmt);
				connector.closeConnection();
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}