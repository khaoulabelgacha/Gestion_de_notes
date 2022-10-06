package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Etudiant;
import entities.Exam;
import entities.Filiere;
import entities.Module;

public class ExamDao extends IActionDao<Exam>{
	Statement statement;
	Connection connection;
	ModuleDao moduleDao ;
	
	
	public ExamDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		moduleDao = new ModuleDao(connection);
	}
	
	@Override
	public Exam add(Exam e) throws SQLException {
		String req = "insert into exam(idModule, dateExam,coefficient) values ("+e.getModule().getId()+", '"+e.getMySQLDateExam()+"' , "+e.getCoefficient()+" );";
		System.out.println(req);
		statement.execute(req, Statement.RETURN_GENERATED_KEYS);
	try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				e.setId(generatedKeys.getInt(1));
			}
		}
		return e;
	}

	
	

	@Override
	public Exam getbyId(int id) throws SQLException {
		String req = "select * from exam where id="+id;
		ResultSet rs = statement.executeQuery(req);
		Exam e=null ;
		while (rs.next()) {
		e=new Exam();
		e.setId(rs.getInt("id"));
		e.setDateExam(rs.getDate("dateExam"));
		e.setCoefficient(rs.getInt("coefficient"));
		//Module module = moduleDao.getbyId(idModule);
		//e.setModule(module);
		}
		return e;
	
	}
	
	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Exam t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public List<Exam> listByModule(int idModule) throws SQLException {
		String req = "select * from exam where idModule="+idModule;
		ResultSet rs = statement.executeQuery(req);
		List<Exam> resultats = new  ArrayList<Exam>();
		while (rs.next()) {
			Exam e=new Exam();
			e.setId(rs.getInt("id"));
			e.setDateExam(rs.getDate("dateExam"));
			e.setCoefficient(rs.getInt("coefficient"));
			Module module = moduleDao.getbyId(idModule);
			e.setModule(module);
			
			resultats.add(e);
		}
		return resultats;
	}
	public List<Exam> list() throws SQLException {
		String req = "select * from exam";
		ResultSet rs = statement.executeQuery(req);
		List<Exam> resultats = new  ArrayList<Exam>();
		while (rs.next()) {
			Exam e=new Exam();
			e.setId(rs.getInt("id"));
			e.setDateExam(rs.getDate("dateExam"));
			e.setCoefficient(rs.getInt("coefficient"));
			
			Module module = moduleDao.getbyId(rs.getInt("idModule"));
			e.setModule(module);
			
			resultats.add(e);
		}
		return resultats;
	}
	
	public int nombreExam() throws SQLException {
	String sql = "SELECT count(*) as count FROM exam";
	
		System.out.println(sql);

		int count = 0;
		ResultSet rs = statement.executeQuery(sql);
	
		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}

	@Override
	public Exam getFiliereByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
