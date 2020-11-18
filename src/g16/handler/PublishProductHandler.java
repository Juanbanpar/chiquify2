package g16.handler;

import java.io.FileNotFoundException;
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

public class PublishProductHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String categoria = request.getParameter("categoria");
		String descripcion = request.getParameter("descripcion");
		String estado = "Disponible";
		
		InputStream imagen = null;
		try {
			imagen = request.getPart("imagen").getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int precio = Integer.parseInt(request.getParameter("precio"));
		String titulo = request.getParameter("titulo");
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("email").equals(null)) return "error.html";
		
		String name = (String) session.getAttribute("name");
		String lastname1 = (String) session.getAttribute("lastname1");
		String lastname2 = (String) session.getAttribute("lastname2");
		String city = (String) session.getAttribute("city");
		String email = (String) session.getAttribute("email");
		String passwd = (String) session.getAttribute("password");
		
		Usuario _usuario = new Usuario();
		_usuario.setNombre(name);
		_usuario.setApellido1(lastname1);
		_usuario.setApellido2(lastname2);
		_usuario.setCiudad(city);
		_usuario.setEmail(email);
        _usuario.setPasswd(passwd);
        
        Producto _product = new Producto();
        //_product.setIdproduct(1);
        _product.setCategoria(categoria);
        _product.setDescripcion(descripcion);
        _product.setEstado(estado);
        _product.setImagen(imagen);
        _product.setPrecio(precio);
        _product.setTitulo(titulo);
        _product.setUsuario2(_usuario);
        
        ProductManager pm = new ProductManager();
        pm.insertProduct(_product);
		
		return "index.html";
	}
}
