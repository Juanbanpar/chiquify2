package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.DBHelper;
import g16.model.Usuario;

public class DeleteHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("email");
		
		DBHelper helper = new DBHelper();
		helper.deleteUser(email);
		
		session.invalidate();
		
		return "index.html";
	}
}