package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VPharmacistRecipeDTO;

public interface IVPharmacistRecipeDAO {
	List<VPharmacistRecipeDTO> getVPharmacistRecipe(int receptId) throws DALException;
	List<VPharmacistRecipeDTO> getVPharmacistRecipeList() throws DALException;
}