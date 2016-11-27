package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBManagerListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce){
		ServletContext ctx=sce.getServletContext();
		Object con=ctx.getAttribute("DBCon");
		if(con!=null){
			Connection conn =(Connection)con;
			try {
				if(!conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public void contextInitialized(ServletContextEvent sce){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager?characterEncoding=UTF-8","root","");
			ServletContext ctx= sce.getServletContext();
			ctx.setAttribute("DBCon", con);
			
		} catch ( ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		}


	
}
