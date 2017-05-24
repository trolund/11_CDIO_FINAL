package data.dao;

import java.util.List;

import data.dto.OperatorDTO;

/*
 * Interface for the OperatorDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IOperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;
	List<OperatorDTO> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
	void deleteOperator(int oprId) throws DALException;
}