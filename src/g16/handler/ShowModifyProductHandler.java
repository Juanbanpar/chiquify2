package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class ShowModifyProductHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler obtiene el id del producto a editar y muestra la página de edición
		 */
		
		HttpSession session = request.getSession(true);
		session.setAttribute("idtoEdit", request.getParameter("Id"));
		
		return "editProduct.jsp";
	}
}
