package data.dao;

import java.util.List;

import data.dto.RaavareDTO;

/*
 * Interface for the RaavareDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IRaavareDAO {
	RaavareDTO getRaavare(int raavareId) throws DALException;
	List<RaavareDTO> getRaavareList() throws DALException;
	void createRaavare(RaavareDTO raavare) throws DALException;
	void updateRaavare(RaavareDTO raavare) throws DALException;
	void deleteRaavare(int raavareId) throws DALException;
}