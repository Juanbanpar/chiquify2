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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SendConfirmation implements RequestHandler{
	
	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	//private javax.jms.MessageConsumer Mcon = null;
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession(true);
			String emailOrigen = (String) session.getAttribute("email");
			List<Item> listaItems = (List<Item>)session.getAttribute("cart");
		
			DBHelper dbh = new DBHelper();
			Usuario comprador = dbh.getUser(emailOrigen, (String)session.getAttribute("password"));
			
			ProductManager pm = new ProductManager();
			
			List<ConfirmacionCompra> ConfirmacionVendedor = new ArrayList<ConfirmacionCompra>();
			String tarjeta = (String) request.getParameter("tarjeta");
			
			for(int i=0; i<listaItems.size(); i++) {
				String vendedor=listaItems.get(i).getProduct().getVendedor().getEmail();
				boolean isVendedor = false;
				if(!ConfirmacionVendedor.isEmpty()) {
					for(int j=0; j<ConfirmacionVendedor.size(); j++) {
						if(ConfirmacionVendedor.get(j).getVendedor()==vendedor) {
							//adding to total cost
							double PrecioPorVendedor=ConfirmacionVendedor.get(j).getCantidad(); 
							PrecioPorVendedor+=listaItems.get(i).getQuantity();
							ConfirmacionVendedor.get(j).setCantidad(PrecioPorVendedor);
							
							//adding products
							isVendedor=true;
							ConfirmacionVendedor.get(j).getProductos().add(listaItems.get(i));
							
						}
					}
				}
				//if vendedor not in previous list, add him
				if(isVendedor == false) {
					double precio=listaItems.get(i).getProduct().getPrecio()*listaItems.get(i).getQuantity();
					int x=(int) Math.random()*20000;
					String id=String.valueOf(x);
					List<Item> aux = new ArrayList<Item>();
					aux.add(listaItems.get(i));
					ConfirmacionVendedor.add(new ConfirmacionCompra(tarjeta,precio, aux, id ,vendedor));
				
				}
			}
			
			contextoInicial = new javax.naming.InitialContext();
			
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Destination) contextoInicial.lookup(InformacionProperties.getQueue());
			
			Qcon = factory.createConnection();
			QSes = Qcon.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			
			
			Mpro = QSes.createProducer(cola);
			
			Qcon.start();
			
			for(int i=0; i<ConfirmacionVendedor.size(); i++) {
				String emailDestino = ConfirmacionVendedor.get(i).getVendedor();
				String mensaje= "Tarjeta: " + tarjeta + "\nID compra: "+ConfirmacionVendedor.get(i).getId()+"\nProductos: ";
				
				//let's add all items buyed to this seller
				List<Item> itemsOfSeller = ConfirmacionVendedor.get(i).getProductos();
				
				for(int j=0; j<itemsOfSeller.size(); j++) {
					//marcamos productos como vendidos
					pm.buyerProduct(itemsOfSeller.get(j).getProduct().getIdproduct(), comprador);
					
					//añadimos prodcuto al mensaje
					mensaje+= "\n " + itemsOfSeller.get(j).getProduct().getTitulo() + " x " + itemsOfSeller.get(j).getQuantity(); 
				}
				
			
				MiMensaje contenido = new MiMensaje(emailOrigen, emailDestino, mensaje);
				ObjectMessage mess =  QSes.createObjectMessage(contenido);//Queremos introducir nuestro objeto mensaje
				mess.setStringProperty("Destinatario", emailDestino);
				Mpro.send(mess);
			}
            
            
			//System.out.println("Mensaje: " + mess.getObject().getMessage() + "__________________" );
			
			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
					.println("JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println("JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return "index.jsp";
	}
}
