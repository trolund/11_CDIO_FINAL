package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VAdminOperatorDTO;

public interface IVAdminOperatorDAO {
	VAdminOperatorDTO getVAdminOperator(int oprId) throws DALException;
	List<VAdminOperatorDTO> getVAdminOperatorList() throws DALException;
}