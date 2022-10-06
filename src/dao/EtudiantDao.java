package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Etudiant;
import entities.Filiere;

public class EtudiantDao extends IActionDao<Etudiant>{
	Statement statement;
	Connection connection;
	FiliereDao filiereDao ;
	
	
	public EtudiantDao(Connection connection) throws SQLException {
		this.connection=connection;
		statement = connection.createStatement();
		filiereDao = new FiliereDao(connection);
	}
	
	@Override
	public Etudiant add(Etudiant e) throws SQLException {
		
		
		//if( e.getFiliere()==null )
		String req = "insert into etudiant(cne,nom,prenom,email,password,filiere) values ("
				+ " '" + e.getCne() + "' , '" + e.getNom() + "','" + e.getPrenom() + "','" + e.getEmail()
				+ "','" + e.getPassword() + "'   ";
		if(e.getFiliere() != null)
			req +=" , '" + e.getFiliere().getId() + "' ";
				
		req+=")";
		
		System.out.println(req);

		statement.execute(req, Statement.RETURN_GENERATED_KEYS);

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				e.setId(generatedKeys.getInt(1));
			}
		}
		//System.out.println(e.getId());
		return e;
	}

	@Override
	public List<Etudiant> list() throws SQLException {
		String req = "select * from etudiant e";
		List<Etudiant> resultat = new ArrayList<Etudiant>();
		ResultSet rs = statement.executeQuery(req);
		while (rs.next()) {
			// cne,nom,prenom,email,password,filiere
			Etudiant e = new Etudiant();
			e.setId(rs.getInt("id"));
			e.setCne(rs.getString("cne"));
			e.setNom(rs.getString("nom"));
			e.setPrenom(rs.getString("prenom"));
			e.setEmail(rs.getString("email"));
			e.setPassword(rs.getString("password"));
			
			Filiere filiere = filiereDao.getbyId(rs.getInt("filiere"));
			e.setFiliere(filiere);
			
			resultat.add(e);
		}
		return resultat;
	}

	@Override
	public Etudiant getbyId(int id) throws SQLException {
		String req = "select * from etudiant where id="+id;
		ResultSet rs = statement.executeQuery(req);
		Etudiant e=null ;
		while (rs.next()) {
		e= new Etudiant();
			e.setId(rs.getInt("id"));
			e.setCne(rs.getString("cne"));
			e.setNom(rs.getString("nom"));
			e.setPrenom(rs.getString("prenom"));
			e.setEmail(rs.getString("email"));
			e.setPassword(rs.getString("password"));
			
			Filiere filiere = filiereDao.getbyId(rs.getInt("filiere"));
			e.setFiliere(filiere);
			
		
			//resultat.add(f);
		}
		return e;
	
	}

	@Override
	public void delete(int id) throws SQLException {
		String req = "delete from etudiant WHERE id=" + id;
		statement.execute(req);
	}

	@Override
	public void update(Etudiant t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public Etudiant getbyEmail(String email) throws SQLException {
		String req = "select * from filiere where email='"+email+"'";
		ResultSet rs = statement.executeQuery(req);
		Etudiant e=null ;
		while (rs.next()) {
		e= new Etudiant();
			e.setId(rs.getInt("id"));
			e.setCne(rs.getString("cne"));
			e.setNom(rs.getString("nom"));
			e.setPrenom(rs.getString("prenom"));
			e.setEmail(rs.getString("email"));
			e.setPassword(rs.getString("password"));
			
			Filiere filiere = filiereDao.getbyId(rs.getInt("filiere"));
			e.setFiliere(filiere);
		}
		return e;
	
	}
	
	
	
	public List<Etudiant> listParFiliere(int idFiliere) throws SQLException {
		String req = "select * from etudiant e where filiere="+idFiliere;
		List<Etudiant> resultat = new ArrayList<Etudiant>();
		ResultSet rs = statement.executeQuery(req);
		while (rs.next()) {
			// cne,nom,prenom,email,password,filiere
			Etudiant e = new Etudiant();
			e.setId(rs.getInt("id"));
			e.setCne(rs.getString("cne"));
			e.setNom(rs.getString("nom"));
			e.setPrenom(rs.getString("prenom"));
			e.setEmail(rs.getString("email"));
			e.setPassword(rs.getString("password"));
			
			Filiere filiere = filiereDao.getbyId(rs.getInt("filiere"));
			e.setFiliere(filiere);
			
			resultat.add(e);
		}
		return resultat;
	}
public int nombreEtudiant() throws SQLException {

		
		String sql = "SELECT count(*) as count FROM etudiant ";

		
		System.out.println(sql);

		int count = 0;
		ResultSet rs = statement.executeQuery(sql);
	
		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}

@Override
public Etudiant getFiliereByCode(String code) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
}
