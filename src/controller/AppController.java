package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.SQLException;
import dao.AdminDao;
import dao.Database;

import dao.UserDao;
import dao.EtudiantDao;
import entities.User;

@WebServlet(urlPatterns={"/login"})
public class AppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean isPost;
	PrintWriter out;
	HttpSession session;
	User utilisateurConnecte;
	
	UserDao userDao;
	AdminDao adminDao;
	EtudiantDao  etiduantDao;
	
    public AppController() {
        super();
        
    }

    @Override
	public void init() throws ServletException {
		try {
			userDao = new UserDao( Database.connectionFactory().getCnx());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		isPost = request.getMethod().toUpperCase().equals("POST");
		String url = request.getServletPath();
		session = request.getSession();
		
		utilisateurConnecte = (User)  session.getAttribute("utilisateurConnecte");
		try {
		switch (url) {
		case "/login":
				login(request, response);
			break;
			
			default :
				break;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		if(isPost){
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if(email.isEmpty() || password.isEmpty()){
				System.out.println("EMPTYYYYYYYY");
				response.sendRedirect("index.jsp");
				return;
			}
			
			User u = userDao.getByEmail(email);
			if(u==null){
				response.sendRedirect("index.jsp");
				return;
			}
			
			if(u.getPassword().equals(password)){
				System.out.println(u.getType());
				session.setAttribute("utilisateurConnecte", u);
				if(u.getType().equals("admin")){
					response.sendRedirect("admin/index");
				}else if(u.getType().equals("prof")){
					response.sendRedirect("prof/index");
				}else if(u.getType().equals("etudiant")){
					response.sendRedirect("etudiant/index");
				}
			}else{
				response.sendRedirect("index.jsp");
			}
		}else{
			response.sendRedirect("index.jsp");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
