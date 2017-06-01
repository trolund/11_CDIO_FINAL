package final_cdio_11.java.data.dao;

import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.RoleDTO;

/*
 * Interface for the RoleDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IRoleDAO {
	List<RoleDTO> getOprRoles(int oprId) throws DALException;
	List<RoleDTO> getRoleList() throws DALException;
	void createRole(RoleDTO roleDTO) throws DALException;
	void updateRole(RoleDTO roleDTO) throws DALException;
	void deleteRole(RoleDTO roleDTO) throws DALException;
}