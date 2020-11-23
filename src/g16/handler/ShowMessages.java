package g16.handler;
import javax.jms.ObjectMessage;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.jms.ObjectMessage;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import g16.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ShowMessages implements RequestHandler{
	
	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	
	private javax.jms.Destination cola = null; //Destination
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;
	

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler permite leer mensajes de la cola del sistema que recibe el usuario de la sesión
		 */
		
		HttpSession session;
		List<MiMensaje> contenido;
		MessageConsumer messageConsumer;
		Message m;
		
		try {
			
			//creamos sesión e incializamos cola y conexión
			session = request.getSession(true);
						contextoInicial = new javax.naming.InitialContext();
						
			String miEmail = (String) session.getAttribute("email");
			
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueue());
			
			Qcon = factory.createConnection();
			QSes = Qcon.createSession();
			
			messageConsumer = QSes.createConsumer(cola, "Destinatario = '" +  miEmail + "'"); 

			//esto se usa para no consumir los mensajes al leerlos
			//QueueBrowser queueBrowser = ((Session) session).createBrowser((Queue)destination);
			
			//empezamos conexion
			Qcon.start();
			contenido= new ArrayList<MiMensaje>();
			
			
			//leemos mensajes
			do {
				m = messageConsumer.receive(100);
				if(m!= null && m instanceof ObjectMessage) {
					ObjectMessage aux = (ObjectMessage) m;
					contenido.add(aux.getBody(MiMensaje.class));
				}
			
			}while(m!=null);
            
			
			
			request.setAttribute("contenido", contenido);
			
			
			//dejamos de leer mensajes y cerramos conexion
			messageConsumer.close();
			QSes.close();
			Qcon.close();

		} catch (JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return "mensajesLeidos.jsp";
	}
}
