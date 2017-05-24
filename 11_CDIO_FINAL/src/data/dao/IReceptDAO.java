package data.dao;

import java.util.List;

import data.dto.ReceptDTO;

/*
 * Interface for the ReceptDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
	void deleteRecept(int receptId) throws DALException;
}