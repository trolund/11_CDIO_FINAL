package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VPharmacistRecipeDTO;

public class SQLPharmacistRecipeDAO implements IVPharmacistRecipeDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLPharmacistRecipeDAO(Connector connector) {
		this.connector = connector;
	}

	/* Method to return all VPharmacistRecipeDTO's associated with receptId */
	@Override
	public List<VPharmacistRecipeDTO> getVPharmacistRecipe(int receptId) throws DALException {
		String getVPharmacistRecipeSql = connector.getQuery("getVPharmacistRecipeSql");
		List<VPharmacistRecipeDTO> vPharmacistRecipeList = new ArrayList<>();
		PreparedStatement getVPharmacistRecipeStmt = null;
		ResultSet rs = null;
		try {
			getVPharmacistRecipeStmt = connector.getConnection().prepareStatement(getVPharmacistRecipeSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getVPharmacistRecipeStmt.setInt(1, receptId);
			rs = getVPharmacistRecipeStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries exist with receptID [" + receptId + "] in VPharmacistRecipe View.");

			do {
				vPharmacistRecipeList.add(new VPharmacistRecipeDTO(rs.getInt("raavare_id"), rs.getInt("recept_id"), rs.getString("raavare_navn"), rs.getString("recept_navn")));
			} while (rs.next());

			return vPharmacistRecipeList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVPharmacistRecipeStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/* Method to return all VPharmacistRecipeDTO's in the View */
	@Override
	public List<VPharmacistRecipeDTO> getVPharmacistRecipeList() throws DALException {
		String getVPharmacistRecipeListSql = connector.getQuery("getVPharmacistRecipeListSql");
		List<VPharmacistRecipeDTO> vPharmacistRecipeList = new ArrayList<>();
		PreparedStatement getVPharmacistRecipeListStmt = null;
		ResultSet rs = null;
		try {
			getVPharmacistRecipeListStmt = connector.getConnection().prepareStatement(getVPharmacistRecipeListSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getVPharmacistRecipeListStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries exist in VPharmacistRecipe View!");

			do {
				vPharmacistRecipeList.add(new VPharmacistRecipeDTO(rs.getInt("raavare_id"), rs.getInt("recept_id"), rs.getString("raavare_navn"), rs.getString("recept_navn")));
			} while (rs.next());

			return vPharmacistRecipeList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVPharmacistRecipeListStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}