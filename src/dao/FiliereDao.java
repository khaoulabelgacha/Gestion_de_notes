package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Etudiant;
import entities.Filiere;

public class FiliereDao extends IActionDao<Filiere>{
	Statement statement;
	Connection connection;
	FiliereDao filiereDao ;
	
	
	public FiliereDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
	}
	
	@Override
	public Filiere add(Filiere f) throws SQLException {
		
		String req = "insert into filiere(code, libele) values ('"+f.getCode()+"', '"+f.getLibele()+"');";
		System.out.println(req);
		statement.execute(req, Statement.RETURN_GENERATED_KEYS);
	try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				f.setId(generatedKeys.getInt(1));
			}
		}
		return f;
	}

	@Override
	public List<Filiere> list() throws SQLException {
		String req = "select * from filiere ";
		ResultSet rs = statement.executeQuery(req);
		List<Filiere> resultats = new  ArrayList<Filiere>();
		while (rs.next()) {
			Filiere f=new Filiere();
			f.setId(rs.getInt("id"));
			f.setCode(rs.getString("code"));
			f.setLibele(rs.getString("libele"));
			resultats.add(f);
		}
		return resultats;
	}

	@Override
	public Filiere getbyId(int id) throws SQLException {
		String req = "select * from filiere where id="+id;
		ResultSet rs = statement.executeQuery(req);
		Filiere f=null ;
		while (rs.next()) {
		f=new Filiere();
			
			f.setId(rs.getInt("id"));
			f.setCode(rs.getString("code"));
			f.setLibele(rs.getString("libele"));
			
		}
		return f;
	}
	@Override
	
	public Filiere getFiliereByCode(String code) throws SQLException {
		 String req = "select * from filiere where code="+code;
		 ResultSet rs = statement.executeQuery(req);
		 Filiere filiere = null;
		 while (rs.next()) {
			 filiere= new Filiere();
			 filiere.setId(rs.getInt("id"));
			 filiere.setCode(rs.getString("code"));
			 filiere.setLibele(rs.getString("libele"));
					
		}
	  return filiere;
	}
	
	@Override
	public void delete(int id) throws SQLException {
		String req = "DELETE FROM filiere WHERE id=" + id;
		statement.execute(req);
	}

	@Override
	public void update(Filiere t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public int nombreFiliere() throws SQLException {

		
		String sql = "SELECT count(*) as count FROM filiere";

		
		System.out.println(sql);

		int count = 0;
		ResultSet rs = statement.executeQuery(sql);
	
		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}

	
}
