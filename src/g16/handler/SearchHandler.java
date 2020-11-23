package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.wls.shaded.org.apache.xalan.transformer.ResultNameSpace;

import g16.model.*;

public class SearchHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler obtiene la cadena de búsqueda para obtener resultados en título y descripción de los productos
		 */
		
		String cadena = request.getParameter("cadena");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("cadena", cadena);
		session.setAttribute("category", null);
		
		return "category.jsp";
	}
	
}
