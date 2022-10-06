package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Database;
import dao.EtudiantDao;
import dao.ExamDao;

import dao.ModuleDao;
import dao.NoteDao;

import entities.Etudiant;
import entities.Exam;
import entities.Module;

import entities.User;


@WebServlet(urlPatterns={"/etudiant/index", "/etudiant/detailsModule","/etudiant/afficherNote", "/etudiant/logout"})
public class EtudiantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean isPost;
	PrintWriter out;
	HttpSession session;
	Etudiant utilisateurConnecte;
	
	EtudiantDao etudiantDao;
	ExamDao examDao;
	NoteDao noteDao;
	ModuleDao moduleDao;   
   
    public EtudiantController() {
        super();
        
    }
    @Override
	public void init() throws ServletException {
		try {
			etudiantDao = new EtudiantDao(Database.connectionFactory().getCnx());
			examDao = new ExamDao(Database.connectionFactory().getCnx());
			noteDao = new NoteDao(Database.connectionFactory().getCnx());
			moduleDao =  new ModuleDao(Database.connectionFactory().getCnx());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			out = response.getWriter();
			isPost = request.getMethod().toUpperCase().equals("POST");
			String url = request.getServletPath();
			session = request.getSession();
			
			
			
			User tmp = (User) session.getAttribute("utilisateurConnecte");
			
			
			if(	tmp==null){
				response.sendRedirect("../index.jsp");
				return;
			}
			
			
			if(tmp.getType().equals("etudiant")){
					utilisateurConnecte = etudiantDao.getbyId(tmp.getId());
					session.setAttribute("utilisateurConnecte",utilisateurConnecte);
			}else{
				response.sendRedirect("../index.jsp");
				return;
			}
			
			
				switch (url) {
				
				case "/etudiant/index" : 
					index(request, response);
					break;
			
					
				case "/etudiant/detailsModule" :
					detailsModule(request, response);
					break;
				case "/etudiant/afficherNote":
					affichernotes(request, response);
					break;
					
				case "/etudiant/logout" :
					logout(request, response);
					break;
					
					
				default :
					break;
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		//request.setAttribute("listeExams", examDao.listByModule(utilisateurConnecte.getModule().getId()));
		request.setAttribute("etucon",utilisateurConnecte);
		request.setAttribute("mesNotes", noteDao.moyenneModules(utilisateurConnecte.getId()));		
		request.setAttribute("mesModules", moduleDao.listParFiliere(utilisateurConnecte.getFiliere().getId()));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	




	private void detailsModule(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		String idModule = request.getParameter("idModule");
		Module module= new Module();
        module =moduleDao.getbyId(Integer.parseInt(idModule));
		HashMap<Exam, Double> examesNotes =noteDao.examesNotes(utilisateurConnecte.getId(), Integer.parseInt(idModule));
		request.setAttribute("examesNotes", examesNotes);	
		request.setAttribute("modulee", module);		
		request.getRequestDispatcher("detailsModule.jsp").forward(request, response);

	}
	private void affichernotes(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		request.setAttribute("etucon",utilisateurConnecte);
		request.setAttribute("mesNotes", noteDao.moyenneModules(utilisateurConnecte.getId()));		
		request.setAttribute("mesModules", moduleDao.listParFiliere(utilisateurConnecte.getFiliere().getId()));
		request.getRequestDispatcher("AfficherNote.jsp").forward(request, response);

	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.invalidate();
	//	request.getRequestDispatcher("../index.jsp").forward(request, response);
		response.sendRedirect("../index.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
