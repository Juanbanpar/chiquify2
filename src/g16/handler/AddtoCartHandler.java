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

public class AddtoCartHandler implements RequestHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
	
		//Producto productModel = new Producto();
		
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("Id"));
		
		ProductManager pm = new ProductManager();
		
		if (session.getAttribute("cart") == null) {
			
			List<Item> cart = new ArrayList<Item>();
			cart.add(new Item(pm.getProduct(id), 1));
			session.setAttribute("cart", cart);
			
		} else {
			
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = -1;
			
			for (int i = 0; i < cart.size(); i++) {
				if (cart.get(i).getProduct().getIdproduct() == id) {
					index = i;
				}
			}
			
			if (index == -1) {
				cart.add(new Item(pm.getProduct(id), 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			
			session.setAttribute("cart", cart);
		}
		
		//response.sendRedirect("cart");
		return "index.html";
	}
}
