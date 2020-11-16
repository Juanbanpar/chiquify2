package g16.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {

	private Connection _connection;
	
	private void connect() {
		try {
			Context _context = new InitialContext();
			DataSource _datasource = (DataSource) _context.lookup("jdbc/chiquify");
			 _connection = _datasource.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void disconect() {
		try {
			_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void insert(Usuario nueva) {
		connect();
	
		try {
			PreparedStatement ps = _connection.prepareStatement("INSERT INTO usuario values (?,?,?,?,?,MD5(?))");
			ps.setString(1, nueva.getNombre());
			ps.setString(2, nueva.getApellido1());
			ps.setString(3, nueva.getApellido2());
			ps.setString(4, nueva.getCiudad());
			ps.setString(5, nueva.getEmail());
			ps.setString(6, nueva.getPasswd());

			ps.executeUpdate();

			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disconect();
	}
	
	public Usuario getUser(String email, String password) {
		connect();
	
		try {
			PreparedStatement ps = _connection.prepareStatement("select * from usuario where email = ? and passwd = MD5(?)");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			Usuario user = null;
			
			if(rs.next()) user = new Usuario(rs.getString("email"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getString("nombre"),rs.getString("passwd"));
			
			ps.close();
			
			disconect();
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			disconect();
			return null;
		}
	}
	
	public void updateUser(String email, Usuario user) {
		connect();
		
		try {
			PreparedStatement ps = _connection.prepareStatement("UPDATE usuario SET nombre = ?, apellido1 = ?, "
					+ "apellido2 = ?, ciudad = ?, email = ?, passwd = MD5(?) WHERE email = ?");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellido1());
			ps.setString(3, user.getApellido2());
			ps.setString(4, user.getCiudad());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPasswd());
			ps.setString(7, email);

			ps.executeUpdate();

			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disconect();		
	}
	
	public void deleteUser(String email) {
		connect();
		
		try {
			PreparedStatement ps = _connection.prepareStatement("delete from usuario where email = ?");
			ps.setString(1, email);

			ps.execute();

			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		disconect();
	}
}