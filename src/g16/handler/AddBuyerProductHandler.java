package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class AddBuyerProductHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProductManager pm = new ProductManager();
		Producto product = pm.getProduct(id);
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email").equals(product.getVendedor().getEmail())) {
        	session.setAttribute("seller", "me");
        	return "cart.jsp";
        }
		
		session.setAttribute("seller", null);
		
		Usuario _usuario = new Usuario();
		_usuario.setNombre((String) session.getAttribute("nombre"));
		_usuario.setApellido1((String) session.getAttribute("apellido1"));
		_usuario.setApellido2((String) session.getAttribute("apellido2"));
		_usuario.setCiudad((String) session.getAttribute("ciudad"));
		_usuario.setEmail((String) session.getAttribute("email"));
        _usuario.setPasswd((String) session.getAttribute("passwd"));
				
		pm.buyerProduct(id, _usuario);
		
		return "index.jsp";
	}
}
