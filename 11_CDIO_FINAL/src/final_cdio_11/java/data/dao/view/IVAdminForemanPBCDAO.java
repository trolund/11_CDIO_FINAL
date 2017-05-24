package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;

public interface IVAdminForemanPBCDAO {
	List<VAdminForemanPBCDTO> getVAdminForemanPBC(int oprId) throws DALException;
	List<VAdminForemanPBCDTO> getVAdminForemanPBCList() throws DALException;
}