package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class UpdateHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler permite actualizar los datos de usuario
		 */
		
		String name = request.getParameter("name");
		String lastname1 = request.getParameter("lastname1");
		String lastname2 = request.getParameter("lastname2");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		
		Usuario _usuario = new Usuario();
		_usuario.setNombre(name);
		_usuario.setApellido1(lastname1);
		_usuario.setApellido2(lastname2);
		_usuario.setCiudad(city);
		_usuario.setEmail(email);
        _usuario.setPasswd(passwd);
		
        HttpSession session = request.getSession(true);
		DBHelper helper = new DBHelper();
		helper.updateUser((String) session.getAttribute("email"), _usuario);
		
        session.setAttribute("email", _usuario.getEmail());
        session.setAttribute("nombre", _usuario.getNombre());
        session.setAttribute("apellido1", _usuario.getApellido1());
        session.setAttribute("apellido2", _usuario.getApellido2());
        session.setAttribute("ciudad", _usuario.getCiudad());
        session.setAttribute("passwd", passwd);
		
		return "user.jsp";
	}
}
