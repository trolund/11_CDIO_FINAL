package data.dao;

import java.util.List;

import data.dto.ReceptComponentDTO;

/*
 * Interface for the ReceptComponentDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IReceptComponentDAO {
	ReceptComponentDTO getReceptComponent(int receptId, int raavareId) throws DALException;
	List<ReceptComponentDTO> getReceptComponentList(int receptId) throws DALException;
	List<ReceptComponentDTO> getReceptComponentList() throws DALException;
	void createReceptComponent(ReceptComponentDTO rc) throws DALException;
	void updateReceptComponent(ReceptComponentDTO rc) throws DALException;
	void deleteReceptComponent(int receptId, int raavareId) throws DALException;
}