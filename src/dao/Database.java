package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private String url;
	private String user;
	private String pass;
	private Connection cnx;

	private static Database database;

	public static Database connectionFactory() {
		if (database == null)
			database = new Database();
		return database;
	}

	private Database(String url, String user, String pass) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, pass);
			System.out.println("You are connected now ! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Database() {
		super();
		this.url = "jdbc:mysql://localhost:3306/projet_jee";
		this.user = "root";
		this.pass = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, pass);
			System.out.println("You are connected now ! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCnx() {
		return cnx;
	}

	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}
}
