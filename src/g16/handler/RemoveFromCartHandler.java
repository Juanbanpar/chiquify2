package g16.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class RemoveFromCartHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler permite eliminar un elemento de la lista de la compra
		 */
		
		HttpSession session = request.getSession();
		int indexCart = Integer.parseInt(request.getParameter("IndexCart"));
		
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		
		cart.remove(indexCart);
		session.setAttribute("cart", cart);
		
		return "cart.jsp";
	}

}
