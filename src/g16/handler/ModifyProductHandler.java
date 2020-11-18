package g16.handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class ModifyProductHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		//int id = Integer.parseInt(request.getParameter("id"));
		int id = Integer.parseInt((String)session.getAttribute("idtoEdit"));
		String categoria = request.getParameter("categoria");
		String descripcion = request.getParameter("descripcion");
		String estado = "Disponible";
		String imagen = "estoesunaimagen";
		int precio = Integer.parseInt(request.getParameter("precio"));
		String titulo = request.getParameter("titulo");
		
		ProductManager pm = new ProductManager();
		Producto product = pm.getProduct(id);
		
		if (!session.getAttribute("email").equals(product.getVendedor().getEmail())) return "error.html";
		
		product.setCategoria(categoria);
		product.setDescripcion(descripcion);
		product.setEstado(estado);
		product.setImagen(imagen);
		product.setPrecio(precio);
		product.setTitulo(titulo);
		
		pm.modifyProduct(id, product);
		
		return "user.jsp";
	}
}
