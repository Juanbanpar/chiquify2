package g16.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSendMessageHandler implements RequestHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Este handler obtiene el email del vendedor al que queremos envia un mensaje
		 */
		
		HttpSession session = request.getSession(true);
		session.setAttribute("emailSeller", request.getParameter("emailSeller"));
		
		return "chat.jsp";
	}
}
