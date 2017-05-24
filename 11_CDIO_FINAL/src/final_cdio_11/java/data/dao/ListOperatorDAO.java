package final_cdio_11.java.data.dao;

import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.OperatorDTO;

public class ListOperatorDAO implements IOperatorDAO {

	/* Global List object to store OperatorDTO's */
	private static List<OperatorDTO> oprList;

	/* Initializing the oprList as well as two sample objects */
	public ListOperatorDAO() {
		oprList = new ArrayList<>();
		oprList.add(new OperatorDTO(101, "Stig", "Høgh", "HGH", "dtu@dtu.dk", "123456-6543", "givos12", 0));
		oprList.add(new OperatorDTO(102, "Finn", "Gustafsson", "FGS", "dtu@dtu.dk", "987654-4567", "givos12", 0));
	}

	/* Method to retrieve an OperatorDTO */
	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		for (OperatorDTO dto : oprList) {
			if (oprId == dto.getOprId() && dto.getStatus() != 1) {
				return dto;
			}
		}
		throw new DALException("Operator with oprId [" + oprId + "] does not exist!");
	}

	/* Method to retrieve a list of all OperatorDTO's */
	@Override
	public List<OperatorDTO> getOperatorList() throws DALException {
		return oprList;
	}

	/* Method to create an OperatorDTO */
	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		for (OperatorDTO dto : oprList) {
			if (dto.getOprId() == opr.getOprId()) {
				throw new DALException("Operator with oprId [" + opr.getOprCpr() + "] already exists!");
			}
		}
		oprList.add(opr);
	}

	/* Method to update an OperatorDTO */
	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {
		for (int i = 0; i < oprList.size(); i++) {
			if (opr.getOprId() == oprList.get(i).getOprId() && opr.getStatus() != 1) {
				oprList.remove(i);
				oprList.add(i, opr);
			}
		}
	}

	/* Method to delete an OperatorDTO */
	@Override
	public void deleteOperator(int oprId) throws DALException {
		for (int i = 0; i < oprList.size(); i++) {
			if (oprList.get(i).getOprId() == oprId && oprList.get(i).getStatus() != 1) {
				oprList.remove(i);
			}
		}
	}

}