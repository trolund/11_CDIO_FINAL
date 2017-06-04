package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

public interface IVUserTableDAO {
	VUserTableDTO getVUserTable(int oprId) throws DALException;
	List<VUserTableDTO> getVUserTableList() throws DALException;
}