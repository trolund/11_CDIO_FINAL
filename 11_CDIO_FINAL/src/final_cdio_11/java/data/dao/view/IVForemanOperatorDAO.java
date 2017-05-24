package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VForemanOperatorDTO;

public interface IVForemanOperatorDAO {
	VForemanOperatorDTO getVForemanOperator(int oprId) throws DALException;
	List<VForemanOperatorDTO> getVForemanOperatorList() throws DALException;
}