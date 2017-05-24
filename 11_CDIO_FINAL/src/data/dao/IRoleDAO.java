package data.dao;

import java.util.List;

import data.dto.RoleDTO;

/*
 * Interface for the RoleDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IRoleDAO {
	List<RoleDTO> getOprRoles(int oprId) throws DALException;
	List<RoleDTO> getRoleList() throws DALException;
	void createRole(RoleDTO roleDTO) throws DALException;
	void deleteRole(RoleDTO roleDTO) throws DALException;
}