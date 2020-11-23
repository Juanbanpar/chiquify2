package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class RegisterHandler implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String lastname1 = request.getParameter("lastname1");
		String lastname2 = request.getParameter("lastname2");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//int balance = Integer.parseInt(request.getParameter("balance"));
		
		Usuario _usuario = new Usuario();
		_usuario.setNombre(name);
		_usuario.setApellido1(lastname1);
		_usuario.setApellido2(lastname2);
		_usuario.setCiudad(city);
		_usuario.setEmail(email);
        _usuario.setPasswd(password);
        
        AccountManager am = new AccountManager();
        HttpSession session = request.getSession(true);
        
        if(am.getUser(email) != null) {
        	session.setAttribute("name", "taken");
        	return "registration.jsp";
        }
        
		DBHelper helper = new DBHelper();
		helper.insert(_usuario);
		
		
        session.setAttribute("email", email);
        session.setAttribute("nombre", name);
        session.setAttribute("apellido1", lastname1);
        session.setAttribute("apellido2", lastname2);
        session.setAttribute("ciudad", city);
        session.setAttribute("passwd", password);
		
		
		return "index.jsp";
	}

}