package modelo.persistencia.interfaces;

import java.sql.Connection;
import java.util.List;

import modelo.entidad.Usuario;

public interface DaoUsuario {

	/**
	 * Sets the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
	 * Gets an user from the DB using idu.
	 * 
	 * @param id
	 *            User Identifier.
	 * 
	 * @return User object with that id.
	 */
	public Usuario get(long id);

	/**
	 * Gets an user from the DB using username.
	 * 
	 * @param username
	 *            Username of the user.
	 * 
	 * @return User object with that username.
	 */
	public Usuario get(String username);

	/**
	 * Gets all the users from the database.
	 * 
	 * @return List of all the users from the database.
	 */
	public List<Usuario> getAll();

	/**
	 * Adds an user to the database.
	 * 
	 * @param user
	 *            User object with the user details.
	 * 
	 * @return User identifier or -1 in case the operation failed.
	 */
	public long add(Usuario user);

	/**
	 * Updates an existing user in the database.
	 * 
	 * @param user
	 *            User object with the new details of the existing user.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean save(Usuario user);

	/**
	 * Deletes an user from the database.
	 * 
	 * @param idu
	 *            User identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(long idu);
}
