package final_cdio_11.java.data.dao.view;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.view.VOperatorRBDTO;

public interface IVOperatorRBDAO {
	List<VOperatorRBDTO> getVOperatorRB(int raavareId) throws DALException;
	List<VOperatorRBDTO> getVOperatorRBList() throws DALException;
}