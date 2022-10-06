package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Module;
import entities.Prof;


public class ProfDao extends IActionDao<Prof>{
	Statement statement;
	Connection connection;
	ModuleDao moduleDao;
	
	
	public ProfDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		moduleDao = new ModuleDao(connection);
	}
	
	@Override
	public Prof add(Prof p) throws SQLException {
		
		String req = "insert into prof(nom,prenom,email,password,module) values ('"
				+  p.getNom() + "','" + p.getPrenom() + "','" + p.getEmail()
				+ "','" + p.getPassword() + "'   ";
		
		if(p.getModule() != null)
			req +=" , " + p.getModule().getId() + " ";
				
		req+=")";
		
		System.out.println(req);

		statement.execute(req, Statement.RETURN_GENERATED_KEYS);

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				p.setId(generatedKeys.getInt(1));
			}
		}
		//System.out.println(e.getId());
		return p;
	}

	@Override
	public List<Prof> list() throws SQLException {
		String req = "select * from prof";
		List<Prof> resultat = new ArrayList<Prof>();
		ResultSet rs = statement.executeQuery(req);
		while (rs.next()) {
			// nom,prenom,email,password,module
			Prof p = new Prof();
			p.setId(rs.getInt("id"));
			p.setNom(rs.getString("nom"));
			p.setPrenom(rs.getString("prenom"));
			p.setEmail(rs.getString("email"));
			p.setPassword(rs.getString("password"));
			
			Module module = moduleDao.getbyId(rs.getInt("module"));
			p.setModule(module);
			
			resultat.add(p);
		}
		return resultat;
	}

	@Override
	public Prof getbyId(int id) throws SQLException {
		String req = "select * from prof where id="+id;
		ResultSet rs = statement.executeQuery(req);
		Prof p=null ;
		while (rs.next()) {
		p=new Prof();
		p.setId(rs.getInt("id"));
		p.setNom(rs.getString("nom"));
		p.setPrenom(rs.getString("prenom"));
		p.setEmail(rs.getString("email"));
		p.setPassword(rs.getString("password"));
		
		Module module = moduleDao.getbyId(rs.getInt("module"));
		p.setModule(module);
		}
		return p;
	}

	@Override
	public void delete(int id) throws SQLException {
		String req = "DELETE FROM prof WHERE id=" + id;
		statement.execute(req);	
	}
   
	@Override
	public void update(Prof t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public int nombreProf() throws SQLException {

		
		String sql = "SELECT count(*) as count FROM Prof";

		
		System.out.println(sql);

		int count = 0;
		ResultSet rs = statement.executeQuery(sql);
	
		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}

	@Override
	public Prof getFiliereByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
