package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VOperatorRecipeDTO;

public interface IVOperatorRecipeDAO {
	List<VOperatorRecipeDTO> getVOperatorRecipe(int receptId) throws DALException;
	List<VOperatorRecipeDTO> getVOperatorRecipeList() throws DALException;
}