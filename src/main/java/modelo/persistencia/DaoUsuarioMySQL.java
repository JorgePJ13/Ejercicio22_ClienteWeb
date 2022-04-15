package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidad.Usuario;
import modelo.persistencia.interfaces.DaoUsuario;

public class DaoUsuarioMySQL implements DaoUsuario {

	private Connection conn;
	//Class.forName("com.mysql.cj.jdbc.Driver");
	
	private static final Logger logger = Logger.getLogger(DaoUsuarioMySQL.class.getName());
	
	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Usuario get(long id) {
		if (conn == null) return null;
		
		Usuario user = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id ="+id);			 
			if (!rs.next()) return null; 
			user  = new Usuario();	 
			user.setId(rs.getInt("id"));
			user.setNombre(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			logger.info("fetching User by id: "+id+" -> "+user.getId()+" "+user.getNombre()+" "+user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public Usuario get(String username) {
		if (conn == null) return null;
		
		Usuario user = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username ='"+username+"'");			 
			if (!rs.next()) return null; 
			user  = new Usuario();	 
			user.setId(rs.getInt("id"));
			user.setNombre(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			logger.info("fetching User by name: "+ username + " -> "+ user.getId()+" "+user.getNombre()+" "+user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public List<Usuario> getAll() {

		if (conn == null) return null;
		
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");
			while ( rs.next() ) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("id"));
				user.setNombre(rs.getString("username"));
				//user.setPassword(rs.getString("password"));
				user.setPassword("********");//We return all users with a hidden password
				
				users.add(user);
				logger.info("fetching users: "+user.getId()+" "+user.getNombre()+" "+user.getPassword());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	

	@Override
	public long add(Usuario user) {
		long id=-1;
		long lastidu=-1;
		if (conn != null){

			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='users'");			 
				if (!rs.next()) return -1; 
				lastidu=rs.getInt("seq");
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO Users (username,password) VALUES('"
									+user.getNombre()+"','"
									+user.getPassword()+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='users'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastidu) return -1;
											
				logger.info("CREATING User("+id+"): "+user.getNombre()+" "+user.getPassword());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return id;
	}

	@Override
	public boolean save(Usuario user) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE users SET username='"+user.getNombre()
									+"', password='"+user.getPassword()
									+"' WHERE id = "+user.getId());
				logger.info("updating User: "+user.getId()+" "+user.getNombre()+" "+user.getPassword());
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return done;

	}

	@Override
	public boolean delete(long id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM users WHERE id ="+id);
				logger.info("deleting User: "+id);
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

}
