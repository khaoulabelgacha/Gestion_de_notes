package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Admin;
import entities.User;

public class UserDao {
	Statement statement;
	Connection connection;
	ModuleDao moduleDao;
	
	public UserDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		moduleDao = new ModuleDao(connection);
	}

	public User getByEmail(String email) throws SQLException{
		String req = "SELECT id, nom,prenom, email, password, 'etudiant' as type FROM etudiant  where email='"+email+"'"
				+ " UNION select id, nom,prenom, email, password, 'admin' as type from admin  where email='"+email+"'" 
				+ " UNION select id, nom,prenom, email, password, 'prof' as type from prof  where email='"+email+"'";
		System.out.println(req);
		ResultSet rs = statement.executeQuery(req);
		User u=null ;
		while (rs.next()) {
		u=new User();
		u.setId(rs.getInt("id"));
		u.setNom(rs.getString("nom"));
		u.setPrenom(rs.getString("prenom"));
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("password"));
		u.setType(rs.getString("type"));
		}
		return u;	
	}
}
