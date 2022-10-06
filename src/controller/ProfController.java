package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.Database;
import dao.EtudiantDao;
import dao.ExamDao;
import dao.FiliereDao;
import dao.ModuleDao;
import dao.NoteDao;
import dao.ProfDao;
import entities.User;
import entities.Etudiant;
import entities.Exam;
import entities.Note;
import entities.Prof;

@WebServlet(urlPatterns={"/prof/index","/prof/programmerExamen", "/prof/donnerNotes","/prof/listerExamen", "/prof/logout"})
public class ProfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean isPost;
	PrintWriter out;
	HttpSession session;
	Prof utilisateurConnecte;
	
	FiliereDao filiereDao;
	ModuleDao moduleDao;
	ProfDao profDao;
	EtudiantDao etudiantDao;
	ExamDao examDao;
	NoteDao noteDao;   
   
    public ProfController() {
        super();
        
    }
    @Override
	public void init() throws ServletException {
		try {
			filiereDao = new FiliereDao( Database.connectionFactory().getCnx());
			moduleDao = new ModuleDao(Database.connectionFactory().getCnx());
			profDao  = new ProfDao(Database.connectionFactory().getCnx());
			etudiantDao = new EtudiantDao(Database.connectionFactory().getCnx());
			examDao = new ExamDao(Database.connectionFactory().getCnx());
			noteDao = new NoteDao(Database.connectionFactory().getCnx());
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
			
			
			if(tmp.getType().equals("prof")){
					utilisateurConnecte = profDao.getbyId(tmp.getId());
					session.setAttribute("utilisateurConnecte",utilisateurConnecte);
			}else{
				response.sendRedirect("../index.jsp");
				return;
			}
			
			
		
				switch (url) {
				
				case "/prof/index" : 
					index(request, response);
					break;
				
				case "/prof/programmerExamen":
					programmerExamen(request, response);
					break;
				case "/prof/listerExamen":
					listExamen(request, response);
					break;
					
				case "/prof/donnerNotes":
					donnerNotes(request, response);
					break;
					
				case "/prof/logout" :
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
		request.setAttribute("nbrexam",examDao.nombreExam() );
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	
	private void listExamen(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		request.setAttribute("listeExams", examDao.list());
		request.getRequestDispatcher("listeExamen.jsp").forward(request, response);
	}


	@SuppressWarnings("deprecation")
	private void programmerExamen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if(isPost){
			String dateExamen = request.getParameter("dateExamen");
			String coefficient = request.getParameter("coefficient");
			
			Exam exam = new Exam();
			exam.setCoefficient(Integer.parseInt(coefficient));
			exam.setModule(utilisateurConnecte.getModule());
			exam.setDateExam(new Date(dateExamen));
			
			examDao.add(exam);
			// initialiser la table notes:
			for(Etudiant etudiant : etudiantDao.listParFiliere(utilisateurConnecte.getModule().getFiliere().getId())){
				Note note = new Note();
				note.setEtudiant(etudiant);
				note.setExam(exam);
				note.setNote(0);
				noteDao.add(note);
			}
			
			
			
			request.getRequestDispatcher("listerExamen").forward(request, response);
		}else{
			request.getRequestDispatcher("programmerExamen.jsp").forward(request, response);
		}
		
		
	}

	
	private void donnerNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String idExam = request.getParameter("idExam");
		request.setAttribute("idExam", idExam);
		
		if(isPost){
			String idEtudiant = request.getParameter("idEtudiant");
			String note = request.getParameter("note");
			
			Exam exam = examDao.getbyId(Integer.parseInt(idExam));
			Etudiant etudiant = etudiantDao.getbyId(Integer.parseInt(idEtudiant));
			Note n = noteDao.getbyId(etudiant.getId(),exam.getId());
			n.setNote(Double.valueOf(note));
			noteDao.update(n);
			
			request.setAttribute("listeNotes", noteDao.getbyExam(Integer.parseInt(idExam)));
			request.getRequestDispatcher("donnerNotes.jsp").forward(request, response);
		}else{
			//request.setAttribute("listeEtudiants", etudiantDao.listParFiliere(utilisateurConnecte.getModule().getFiliere().getId()));
			request.setAttribute("listeNotes", noteDao.getbyExam(Integer.parseInt(idExam)));
			request.getRequestDispatcher("donnerNotes.jsp").forward(request, response);
		}
		
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.invalidate();
		//request.getRequestDispatcher("../index.jsp").forward(request, response);
		response.sendRedirect("../index.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
