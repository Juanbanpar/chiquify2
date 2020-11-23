package g16.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProductManager {
	
	/*
	 * Esta clase contiene los métodos para manejar los productos en la base de datos con JPA
	 */
	
	private EntityManagerFactory emf;

	public ProductManager() {
		super();
		this.emf = Persistence.createEntityManagerFactory("ChiquifyNew");
	}
	
	public List<Producto> getAllProducts(){
		List<Producto> productos = null;
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("Select p from Producto p");
		
		productos = q.getResultList();
		
		return productos;
	}
	
	public List<List<Producto>> getAllProductsbyCategory(String cadena){
		
		List <List<Producto>> productos = new ArrayList<List<Producto>>(8);
		
		EntityManager em = emf.createEntityManager();
		
		Query q;
		
		try {
			String categoria = "Abrigo";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat  AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Pantalon";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat  AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Zapato";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat  AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Vestido";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Camiseta";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat  AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Chandal";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat  AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Bolso";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat  AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		try {
			String categoria = "Accesorio";
			q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.categoria = :cat AND p.estado = :estado")
					.setParameter("cat", categoria)
					.setParameter("estado", "Disponible");
			productos.add(q.getResultList());
		} catch (Exception e) {	
		}
		
		return productos;
	}
	
	public List<Producto> getAllProductsbyUser(Usuario user){
		List<Producto> productos = null;
		
		EntityManager em = emf.createEntityManager();
				
		Query q = em.createQuery("Select p from Producto p WHERE p.vendedor = :user").setParameter("user", user);
		
		productos = q.getResultList();
		
		return productos;
	}
	
	public List<Producto> getAllProductsbyString(String cadena){
		List<Producto> productos = null;
		
		EntityManager em = emf.createEntityManager();
				
		Query q = em.createQuery("Select p from Producto p WHERE (p.titulo LIKE '%" + cadena + "%' OR p.descripcion LIKE '%" + cadena + "%') AND p.estado = :estado")
				.setParameter("estado", "Disponible");
		
		productos = q.getResultList();
		
		return productos;
	}
	
	public List<Producto> getAllProductsBuyed(Usuario user){
		List<Producto> productos = null;
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("Select p from Producto p WHERE p.comprador = :email").setParameter("email", user);
		
		productos = q.getResultList();
		
		return productos;
	}
	
	
	public void insertProduct(Producto p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
		
			em.persist(p);
		
			et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
		
		
	}
	
	public Producto getProduct(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			Producto p = em.find(Producto.class, id);
			
			//et.begin();
		
			//em.persist(p);
			return p;
		
			//et.commit();
		}catch(Exception e) {	
			if(et.isActive()) {
				et.rollback();
			}
		}
		return null;	
	}
	
	public void modifyProduct(int id, Producto product) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			Producto p = em.find(Producto.class, id);
			
			et.begin();
			
			p.setCategoria(product.getCategoria());
			p.setDescripcion(product.getDescripcion());
			p.setEstado(product.getEstado());
			p.setBase64(product.getImagen());
			p.setPrecio(product.getPrecio());
			p.setTitulo(product.getTitulo());
					
			et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
	}
	
	public void buyerProduct(int id, Usuario buyer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			Producto p = em.find(Producto.class, id);
			
			et.begin();
			
			p.setEstado("Vendido");
			p.setUsuario1(buyer);
					
			et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
	}
	
	public void deleteProduct(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			Producto p = em.find(Producto.class, id);
			
			et.begin();
		
			em.remove(p);
		
			et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
		
	}
}
