package g16.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import g16.handler.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/login", "/register", "/update", "/delete", "/publishproduct", "/showmodifyproduct",
		"/modifyproduct", "/deleteproduct", "/addbuyer", "/search", "/showproduct", "/addtocart"})
@MultipartConfig
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Map<String, RequestHandler> dictionary;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		dictionary = new HashMap<String, RequestHandler>();
		dictionary.put("/register", new RegisterHandler());
		dictionary.put("/login", new LoginHandler());
		dictionary.put("/update", new UpdateHandler());
		dictionary.put("/delete", new DeleteHandler());
		dictionary.put("/publishproduct", new PublishProductHandler());
		dictionary.put("/showmodifyproduct", new ShowModifyProductHandler());
		dictionary.put("/modifyproduct", new ModifyProductHandler());
		dictionary.put("/deleteproduct", new DeleteProductHandler());
		dictionary.put("/addbuyer", new AddBuyerProductHandler());
		dictionary.put("/search", new SearchHandler());
		dictionary.put("/showproduct", new ShowProductHandler());
		dictionary.put("/addtocart", new AddtoCartHandler());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		
		RequestHandler rh = dictionary.get(path);
		
		if(rh == null) {
			response.sendError(500);
		}else {
			String view = rh.process(request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}