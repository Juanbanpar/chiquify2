package g16.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AccountManager {

	/*
	 * Esbozo de una implementación con JPA de los usuarios, se decidió abandonarla con el 
	 * fin de cumplir con los requisitos de la práctica
	 */
	
	private EntityManagerFactory emf;

	public AccountManager() {
		super();
		this.emf = Persistence.createEntityManagerFactory("ChiquifyNew");
	}
	
	public List<Usuario> getAllUsers(){
		List<Usuario> usuarios = null;
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("Select * from Usuario");
		
		usuarios = q.getResultList();
		
		return usuarios;
	}
	
	
	public void insertUser(Usuario u) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
		
			em.persist(u);
		
			et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
		
		
	}
	
	public Usuario getUser(String email) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			Usuario user = em.find(Usuario.class, email);
			
			//et.begin();
		
			//em.persist(u);
		
			//et.commit();
			return user;
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
		return null;
		
		
	}
	
	/*
	public void transfer(int a1, int a2, int m) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Usuario Usuario1 = em.find(Usuario.class, a1);
		Usuario Usuario2 = em.find(Usuario.class, a2);
		
		try {
			et.begin();
			Usuario1.setBalance(Usuario1.getBalance()-m);
			Usuario2.setBalance(Usuario2.getBalance()+m);
			em.merge(Usuario1);
			em.merge(Usuario2);
			et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}
		
	}
	*/
	
}