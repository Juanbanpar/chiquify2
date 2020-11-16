package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class LoginHandler implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		
		DBHelper helper = new DBHelper();
		Usuario nuevo = helper.getUser(email, passwd);
		
		HttpSession session = request.getSession(true);
        session.setAttribute("email", nuevo.getEmail());
        session.setAttribute("nombre", nuevo.getNombre());
        session.setAttribute("apellido1", nuevo.getApellido1());
        session.setAttribute("apellido2", nuevo.getApellido2());
        session.setAttribute("ciudad", nuevo.getCiudad());
        session.setAttribute("passwd", passwd);
		
		//AccountManager am = new AccountManager();
		//am.insert(nuevo);
        
        //System.out.println(nuevo.getEmail());
		
		return "user.jsp";
	}

}