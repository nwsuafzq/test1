package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.DBProcess;

public class Controller extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	/*public Controller() {
		super();
	}*/

	/**
	 * Destruction of the servlet. <br>
	 */
	/*public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}*/

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the respWonse send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String forward="";
			String redirect="";
			try{
				DBProcess db=new DBProcess(request);
				String action=request.getParameter("action");
				if("edit".equals(action)){
					Book book=db.getBookById();
					request.setAttribute("book",book);
					forward="BookEdit.jsp";
				}
				else if("save".equals(action)){
					db.save();
					redirect="ctrl";
				}
				else if("delete".equals(action)){
					db.deleteBookById();
					redirect="ctrl";
				}
				else{
					ArrayList<Book> bookList=db.getBookList();
					request.setAttribute("bookList", bookList);
					forward="BookList.jsp";
					
				}
				
			}
		catch(Exception e){
			forward="error.jsp";
			request.setAttribute("errMsg", e.getMessage());
			e.printStackTrace();
		}
			if("".equals(redirect)){
				request.getRequestDispatcher(forward).forward(request, response);
			}
			else{
				response.sendRedirect(redirect);
				
			}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	/*public void init() throws ServletException {
		// Put your code here
	}*/

}
