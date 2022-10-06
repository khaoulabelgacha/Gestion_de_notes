package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Admin;
import entities.Module;
import entities.Prof;

public class AdminDao {
	Statement statement;
	Connection connection;
	ModuleDao moduleDao;
	
	
	public AdminDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		moduleDao = new ModuleDao(connection);
	}
}
