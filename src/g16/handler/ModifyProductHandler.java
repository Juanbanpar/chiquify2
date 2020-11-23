package g16.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

public class ModifyProductHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler sirve para modificar un producto del sistema, se obtienen sus nuevos atributos
		 * y se aplican.
		 */
		
		HttpSession session = request.getSession(true);
		
		//int id = Integer.parseInt(request.getParameter("id"));
		int id = Integer.parseInt((String)session.getAttribute("idtoEdit"));
		String categoria = request.getParameter("categoria");
		String descripcion = request.getParameter("descripcion");
		String estado = "Disponible";
		int precio = Integer.parseInt(request.getParameter("precio"));
		String titulo = request.getParameter("titulo");
		
		ProductManager pm = new ProductManager();
		Producto product = pm.getProduct(id);
		
		InputStream imagen = null;
		try {
			imagen = request.getPart("imagen").getInputStream();
			if (imagen instanceof  FileInputStream) {
				product.setImagen(imagen);
			} else {
				product.setBase64(product.getImagen());
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		product.setCategoria(categoria);
		product.setDescripcion(descripcion);
		product.setEstado(estado);

		product.setPrecio(precio);
		product.setTitulo(titulo);
		
		pm.modifyProduct(id, product);
		
		return "user.jsp";
	}
}
