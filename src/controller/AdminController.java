package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.Database;
import dao.EtudiantDao;
import dao.FiliereDao;
import dao.ModuleDao;
import dao.ProfDao;

import entities.Etudiant;
import entities.Filiere;
import entities.Module;
import entities.Prof;
import entities.User;

@WebServlet(urlPatterns={"/admin/index","/admin/ajouterFiliere","/admin/listeFilieres","/admin/supprimerFiliere",
		"/admin/ajouterModule","/admin/listeModules","/admin/supprimerModule",
		"/admin/ajouterProf","/admin/listeProfs","/admin/supprimerProf",
		"/admin/ajouterEtudiant","/admin/listeEtudiants","/admin/supprimerEtudiant",
		"/admin/logout"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean isPost;
	PrintWriter out;
	HttpSession session;
	User utilisateurConnecte;
	
	
	AdminDao adminDao;
	FiliereDao filiereDao;
	ModuleDao moduleDao;
	ProfDao profDao;
	EtudiantDao etudiantDao;
	
    public AdminController() {
        super();
       
    }

    
	@Override
	public void init() throws ServletException {
		try {
			adminDao = new AdminDao( Database.connectionFactory().getCnx());
			filiereDao = new FiliereDao( Database.connectionFactory().getCnx());
			moduleDao = new ModuleDao(Database.connectionFactory().getCnx());
			profDao  = new ProfDao(Database.connectionFactory().getCnx());
			etudiantDao = new EtudiantDao(Database.connectionFactory().getCnx());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		isPost = request.getMethod().toUpperCase().equals("POST");
		String url = request.getServletPath();
		session = request.getSession();
		
		utilisateurConnecte = (User)  session.getAttribute("utilisateurConnecte");
		
		if(	utilisateurConnecte==null){
			response.sendRedirect("../index.jsp");
			return;
		}
		
		out.println("url="+url);
		try {
			switch (url) {
			
			case "/admin/index":
				index(request, response);
				break;
			
			case "/admin/ajouterFiliere":
				ajouterFiliere(request, response);
				break;
				
			case "/admin/listeFilieres":
				listeFilieres(request, response);
				break;
				
			case "/admin/supprimerFiliere" :
				supprimerFiliere(request, response);
				break;
				
				
			case "/admin/ajouterModule":
				ajouterModule(request, response);
				break;
				
			case "/admin/listeModules":
				listeModules(request, response);
				break;
				
			case "/admin/supprimerModule" :
				supprimerModule(request, response);
				break;
				
				
			case "/admin/ajouterProf":
				ajouterProf(request, response);
				break;
				
			case "/admin/listeProfs":
				listeProfs(request, response);
				break;
				
			case "/admin/supprimerProf" :
				supprimerProf(request, response);
				break;
				
			
			case "/admin/ajouterEtudiant":
				ajouterEtudiant(request, response);
				break;
				
			case "/admin/listeEtudiants":
				listeEtudiants(request, response);
				break;
				
			case "/admin/supprimerEtudiant" :
				supprimerEtudiant(request, response);
				break;
				
				
			case "/admin/logout" :
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
		//	request.setAttribute("listeExams", examDao.listByModule(utilisateurConnecte.getModule().getId()));
			request.setAttribute("nbrprof",profDao.nombreProf() );
			request.setAttribute("nbretu",etudiantDao.nombreEtudiant() );
			request.setAttribute("nbrfiliere",filiereDao.nombreFiliere());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		private void ajouterFiliere(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
			if(isPost){
				String code = request.getParameter("code");
				String libele = request.getParameter("libele");
				Filiere filiere = new Filiere();
				filiere.setCode(code);
				filiere.setLibele(libele);
				filiere = filiereDao.add(filiere);
				//response.sendRedirect("/admin/listeFilieres");
				request.getRequestDispatcher("/admin/listeFilieres").forward(request, response);
			}else{
				request.getRequestDispatcher("ajouterFiliere.jsp").forward(request, response);
				//response.sendRedirect("ajouterFiliere.jsp");
			}
		}
		
		
		private void listeFilieres(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			request.setAttribute("listeFilieres", filiereDao.list());
			request.getRequestDispatcher("listeFilieres.jsp").forward(request, response);
		}



		private void supprimerFiliere(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			String idFiliere = request.getParameter("idFiliere");
			//Filiere filiere = filiereDao.getbyId(Integer.parseInt(idFiliere));
			filiereDao.delete(Integer.parseInt(idFiliere));
			request.getRequestDispatcher("listeFilieres").forward(request, response);
		}

		
		
		private void ajouterModule(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
			if(isPost){
				String code = request.getParameter("code");
				String libele = request.getParameter("libele");
				String idFiliere = request.getParameter("filiere");
			//String coefficient = request.getParameter("coefficient");
				Filiere filiere = filiereDao.getbyId(Integer.parseInt(idFiliere));
				Module module = new Module();
				module.setCode(code);
				module.setLibele(libele);
			//	module.setCoefficient(Integer.parseInt(coefficient));
				module.setFiliere(filiere);
				module = moduleDao.add(module);
				request.setAttribute("listeFilieres", filiereDao.list());
				request.getRequestDispatcher("/admin/listeModules").forward(request, response);
			}else{
				request.setAttribute("listeFilieres", filiereDao.list());
				request.getRequestDispatcher("ajouterModule.jsp").forward(request, response);
			}
		}
		
		
		
		private void listeModules(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			request.setAttribute("listeModules", moduleDao.list());
			request.getRequestDispatcher("listeModules.jsp").forward(request, response);
		}



		private void supprimerModule(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			String idModule = request.getParameter("idModule");
			moduleDao.delete(Integer.parseInt(idModule));
			request.getRequestDispatcher("listeModules").forward(request, response);
		}
		
		
		
		private void ajouterProf(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
			if(isPost){
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String idModule  = request.getParameter("module");
				Module module = moduleDao.getbyId(Integer.parseInt(idModule));
				
				Prof prof = new Prof();
				
				prof.setNom(nom);
				prof.setPrenom(prenom);
				prof.setEmail(email);
				prof.setPassword(password);
				prof.setModule(module);
				profDao.add(prof);
				
				request.getRequestDispatcher("/admin/listeProfs").forward(request, response);
			}else{
				request.setAttribute("listeModules", moduleDao.list());
				request.getRequestDispatcher("ajouterProf.jsp").forward(request, response);
			}
		}
		
		
		
		private void listeProfs(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			request.setAttribute("listeProfs", profDao.list());
			request.getRequestDispatcher("listeProfs.jsp").forward(request, response);
		}



		private void supprimerProf(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			String idProf = request.getParameter("idProf");
			profDao.delete(Integer.parseInt(idProf));
			request.getRequestDispatcher("listeProfs").forward(request, response);
		}
		
		
		
		
		private void ajouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
			if(isPost){
				String cne = request.getParameter("cne");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String idFiliere  = request.getParameter("filiere");
				Filiere filiere = filiereDao.getbyId(Integer.parseInt(idFiliere));
				
				Etudiant etudiant = new Etudiant();
				
				etudiant.setCne(cne);
				etudiant.setNom(nom);
				etudiant.setPrenom(prenom);
				etudiant.setEmail(email);
				etudiant.setPassword(password);
				etudiant.setFiliere(filiere);
				
				etudiantDao.add(etudiant);
				
				request.getRequestDispatcher("/admin/listeEtudiants").forward(request, response);
			}else{
				request.setAttribute("listeFilieres", filiereDao.list());
				request.getRequestDispatcher("ajouterEtudiant.jsp").forward(request, response);
			}
		}
		
		
		
		private void listeEtudiants(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			request.setAttribute("listeEtudiants", etudiantDao.list());
			request.getRequestDispatcher("listeEtudiants.jsp").forward(request, response);
		}



		private void supprimerEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			String idEtudiant = request.getParameter("idEtudiant");
			etudiantDao.delete(Integer.parseInt(idEtudiant));
			request.getRequestDispatcher("listeEtudiants").forward(request, response);
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
