package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class DeleteProductHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProductManager pm = new ProductManager();
		Producto product = pm.getProduct(id);
		
		HttpSession session = request.getSession(true);
		if (!session.getAttribute("email").equals(product.getUsuario2().getEmail())) return "error.html";
		
		pm.deleteProduct(id);
		
		return "index.html";
	}
}
