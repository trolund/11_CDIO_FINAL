package final_cdio_11.java.data.dao.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VOperatorRecipeDTO;

public class SQLOperatorRecipeDAO implements IVOperatorRecipeDAO {

	/* Database Connector object */
	private final Connector connector;

	/* Constructor to retrieve the Database Connector object */
	public SQLOperatorRecipeDAO(Connector connector) {
		this.connector = connector;
	}

	/* Method to return all VOperatorRecipeDTO's associated with receptId */
	@Override
	public List<VOperatorRecipeDTO> getVOperatorRecipe(int receptId) throws DALException {
		String getVOperatorRecipeSql = connector.getQuery("getVOperatorRecipeSql");
		List<VOperatorRecipeDTO> vOperatorRecipeList = new ArrayList<>();
		PreparedStatement getVOperatorRecipeStmt = null;
		ResultSet rs = null;
		try {
			getVOperatorRecipeStmt = connector.getConnection().prepareStatement(getVOperatorRecipeSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getVOperatorRecipeStmt.setInt(1, receptId);
			rs = getVOperatorRecipeStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries exist with receptID [" + receptId + "] in VOperatorRecipe View.");

			do {
				vOperatorRecipeList.add(new VOperatorRecipeDTO(rs.getInt("raavare_id"), rs.getInt("recept_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"), rs.getString("recept_navn"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			} while (rs.next());

			return vOperatorRecipeList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVOperatorRecipeStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

	/* Method to return all VOperatorRecipeDTO's in the View */
	@Override
	public List<VOperatorRecipeDTO> getVOperatorRecipeList() throws DALException {
		String getVOperatorRecipeSql = connector.getQuery("getVOperatorRecipeListSql");
		List<VOperatorRecipeDTO> vOperatorRecipeList = new ArrayList<>();
		PreparedStatement getVOperatorRecipeStmt = null;
		ResultSet rs = null;
		try {
			getVOperatorRecipeStmt = connector.getConnection().prepareStatement(getVOperatorRecipeSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = getVOperatorRecipeStmt.executeQuery();

			if (!rs.first()) throw new DALException("No entries exist in VOperatorRecipe View.");

			do {
				vOperatorRecipeList.add(new VOperatorRecipeDTO(rs.getInt("raavare_id"), rs.getInt("recept_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"), rs.getString("recept_navn"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			} while (rs.next());

			return vOperatorRecipeList;
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				connector.cleanup(getVOperatorRecipeStmt, rs);
			} catch (SQLException e) {
				throw new DALException(e.getMessage(), e);
			}
		}
	}

}