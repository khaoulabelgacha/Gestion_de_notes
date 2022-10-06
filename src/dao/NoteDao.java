package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Etudiant;
import entities.Exam;
import entities.Module;
import entities.Note;

public class NoteDao {
	Statement statement;
	Connection connection;
	ModuleDao moduleDao ;
	ExamDao examDao;
	EtudiantDao etudiantDao;
	
	public NoteDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		moduleDao = new ModuleDao(connection);
		examDao = new ExamDao(connection);
		etudiantDao = new EtudiantDao(connection);
	}
	
	
	
	
	public Note getbyId(int idEtudiant, int idExam) throws SQLException {
		String req = "select * from note where idEtudiant="+idEtudiant+" and idExam="+idExam;
		System.out.println(req);
		ResultSet rs = statement.executeQuery(req);
		Note n=null;
		while (rs.next()) {
			n=new Note();
			n.setNote(rs.getDouble("note"));
			
			Exam exam = examDao.getbyId(rs.getInt("idExam"));
			n.setExam(exam);
			
			Etudiant etudiant = etudiantDao.getbyId(rs.getInt("idEtudiant"));
			n.setEtudiant(etudiant);
		}
		return n;
	}
	
	
	public List<Note> getbyExam(int idExam) throws SQLException {
		String req = "select * from note where idExam="+idExam;
		System.out.println(req);
		ResultSet rs = statement.executeQuery(req);
		List<Note> resultats = new  ArrayList<Note>();
		while (rs.next()) {
			Note n=new Note();
			n.setNote(rs.getDouble("note"));
			
			//Exam exam = examDao.getbyId(rs.getInt("idExam"));
			Exam exam = examDao.getbyId(idExam);
			n.setExam(exam);
			
			Etudiant etudiant = etudiantDao.getbyId(rs.getInt("idEtudiant"));
			n.setEtudiant(etudiant);
			
			resultats.add(n);
		}
		return resultats;
	}
	
	
	
	public void add(Note n) throws SQLException {
		String req = "insert into note(idEtudiant, idExam,note) values ("+n.getEtudiant().getId()+", "+n.getExam().getId()+", "+n.getNote()+" );";
		System.out.println(req);
		statement.execute(req);
		//statement.execute(req, Statement.RETURN_GENERATED_KEYS);
	/*try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				n.setId(generatedKeys.getInt(1));
			}
	}*/
		//return n;
	}

	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void update(Note n) throws SQLException {
		String req = "UPDATE note set note="+n.getNote()+" where idEtudiant="+n.getEtudiant().getId()+" and idExam="+n.getExam().getId();
		System.out.println(req);
		statement.execute(req);
	}

	public List<Note> list(int id) throws SQLException {
		
		String req = "select * from note where idEtudiant="+id;
		ResultSet rs = statement.executeQuery(req);
		List<Note> resultats = new  ArrayList<Note>();
		Note n ;
		Exam ee;
		while (rs.next()) {
		    n =new Note();
			ee= new Exam();
			ee= examDao.getbyId(rs.getInt("idExam"));
			n.setNote(rs.getDouble("note"));
		    n.setExam(ee);
			resultats.add(n);
		}
		return resultats;
	}
	/*
	  liste contient les moyennes de chaque module pour un etudiant donnée:
	  	JAVA -> 20
	  	C++  -> 18
	 */
	public HashMap<Module, Double> moyenneModules(int idEtudiant) throws SQLException{
		String req = "SELECT module.id,module.libele,(SUM(note*exam.coefficient)/SUM(exam.coefficient)) AS moyenne from exam, note, module  where module.id=exam.idModule and exam.id=note.idExam and idEtudiant="+idEtudiant+" group by idModule" ;
		//String req = "SELECT (SUM(note*coefficient)/COUNT(note)) AS moyenne from exam, note, module where  note.idModule=module.id and exam.id=note.idExam and idEtudiant="+idEtudiant+" groub by idModule";
		System.out.println(req);
		ResultSet rs = statement.executeQuery(req);
		HashMap<Module, Double> moyenneModules = new HashMap<>();
		while(rs.next()){
			
			Module m = new Module();
			m.setId(rs.getInt("id"));
			m.setLibele(rs.getString("libele"));
			
			moyenneModules.put(m, rs.getDouble("moyenne"));
		}
		return moyenneModules ;	
	}
	
	
	/*
	 Liste contient les details de chaque examen (exam -> note )
	 */
	public HashMap<Exam, Double> examesNotes(int idEtudiant, int idModule) throws SQLException{
	String req = "select * from exam, note where note.idExam = exam.id and idEtudiant ="+idEtudiant+" and idModule="+idModule;		
	System.out.println(req);
		ResultSet rs = statement.executeQuery(req);
		HashMap<Exam, Double> examesNotes = new HashMap<>();
		while(rs.next()){
			Exam e=new Exam();
			e.setId(rs.getInt("id"));
			e.setDateExam(rs.getDate("dateExam"));
			e.setCoefficient(rs.getInt("coefficient"));
			
			examesNotes.put(e, rs.getDouble("note"));
		}
		return examesNotes ;	
	}
}
