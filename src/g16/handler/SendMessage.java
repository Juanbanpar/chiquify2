package g16.handler;
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

import java.util.Enumeration;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SendMessage implements RequestHandler{
	
	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler permite enviar mensajes entre usuarios
		 */
		
		try {
			
			HttpSession session = request.getSession(true);
			String emailOrigen = (String) session.getAttribute("email");
			
			String emailDestino = (String) request.getParameter("emailDestino");
			
			contextoInicial = new javax.naming.InitialContext();
			
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueue());
			
			Qcon = factory.createConnection();
			QSes = Qcon.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			
            MiMensaje contenido = new MiMensaje(emailOrigen, emailDestino, request.getParameter("mensaje"));
            
			Mpro = QSes.createProducer(cola);
			
			ObjectMessage mess =  QSes.createObjectMessage(contenido);//Queremos introducir nuestro objeto mensaje
					
			mess.setStringProperty("Destinatario", emailDestino);
			
			Qcon.start();
			
			Mpro.send(mess);
			
			//System.out.println("Mensaje: " + mess.getObject().getMessage() + "__________________" );
			
			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
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

		return "chat.jsp";
	}
}
