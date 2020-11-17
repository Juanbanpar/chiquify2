package g16.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProductManager {
	
	private EntityManagerFactory emf;

	public ProductManager() {
		super();
		this.emf = Persistence.createEntityManagerFactory("ChiquifyNew");
	}
	
	public List<Producto> getAllProducts(){
		List<Producto> productos = null;
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("Select * from Usuario");
		
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
