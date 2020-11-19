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
		
		String cadena = request.getParameter("cadena");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("cadena", cadena);
		
		return "category.jsp";
	}
	
}
