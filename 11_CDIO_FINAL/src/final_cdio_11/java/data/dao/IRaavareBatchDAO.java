package final_cdio_11.java.data.dao;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.RaavareBatchDTO;

/*
 * Interface for the RaavareBatchDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IRaavareBatchDAO {
	RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
	List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
	List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
	void createRaavareBatch(RaavareBatchDTO rb) throws DALException;
	void updateRaavareBatch(RaavareBatchDTO rb) throws DALException;
	void deleteRaavareBatch(int rbId) throws DALException;
}