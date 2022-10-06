package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Filiere;
import entities.Module;

public class ModuleDao extends IActionDao<Module>{
	Statement statement;
	Connection connection;

	FiliereDao filiereDao;
	
	public ModuleDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		filiereDao = new FiliereDao(Database.connectionFactory().getCnx());
	}
	
	
	@Override
	public Module add(Module m) throws SQLException {
		String req = "insert into module(code, libele, filiere) values ('"+m.getCode()+"', '"+m.getLibele()+"',  "+m.getFiliere().getId()+");";
		System.out.println(req);
		statement.execute(req, Statement.RETURN_GENERATED_KEYS);
	try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				m.setId(generatedKeys.getInt(1));
			}
		}
		return m;
	}

	@Override
	public List<Module> list() throws SQLException {
		String req = "select * from module ";
		ResultSet rs = statement.executeQuery(req);
		List<Module> resultats = new  ArrayList<Module>();
		while (rs.next()) {
			Module m=new Module();
			m.setId(rs.getInt("id"));
			m.setCode(rs.getString("code"));
			m.setLibele(rs.getString("libele"));
			//m.setCoefficient(rs.getInt("coefficient"));
			
			Filiere f = filiereDao.getbyId(rs.getInt("filiere"));
			m.setFiliere(f);
			resultats.add(m);
		}
		return resultats;
	}

	@Override
	public Module getbyId(int id) throws SQLException {
		String req = "select * from module where id="+id;
		ResultSet rs = statement.executeQuery(req);
		Module m=null ;
		while (rs.next()) {
		m=new Module();
			m.setId(rs.getInt("id"));
			m.setCode(rs.getString("code"));
			m.setLibele(rs.getString("libele"));
		//	m.setCoefficient(rs.getInt("coefficient"));
			
			Filiere f = filiereDao.getbyId(rs.getInt("filiere"));
			m.setFiliere(f);
		}
		return m;
	}

	@Override
	public void delete(int id) throws SQLException {
		String req = "DELETE FROM module WHERE id=" + id;
		statement.execute(req);
	}

	@Override
	public void update(Module t) throws SQLException {
		// TODO Auto-generated method stub
			}

	
	public List<Module> listParFiliere(int idFiliere) throws SQLException {
		String req = "select * from module where filiere="+idFiliere;
		ResultSet rs = statement.executeQuery(req);
		List<Module> resultats = new  ArrayList<Module>();
		while (rs.next()) {
			Module m=new Module();
			m.setId(rs.getInt("id"));
			m.setCode(rs.getString("code"));
			m.setLibele(rs.getString("libele"));
		//	m.setCoefficient(rs.getInt("coefficient"));
			Filiere f = filiereDao.getbyId(rs.getInt("filiere"));
			m.setFiliere(f);
			resultats.add(m);
		}
		return resultats;
	}

	public int nombreModule() throws SQLException {

		
		String sql = "SELECT count(*) as count FROM module";

		
		System.out.println(sql);

		int count = 0;
		ResultSet rs = statement.executeQuery(sql);
	
		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}


	@Override
	public Module getFiliereByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
