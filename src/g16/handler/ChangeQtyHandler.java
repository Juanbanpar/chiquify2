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

public class ChangeQtyHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler nos permite modificar la cantidad de elementos a comprar de un producto en el carrito
		 */
		
		HttpSession session = request.getSession();
		int indexCart = Integer.parseInt(request.getParameter("IndexCart"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		
		cart.get(indexCart).setQuantity(qty);
		session.setAttribute("cart", cart);
		
		return "cart.jsp";
	}
}
