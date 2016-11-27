package bean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class DBProcess {
	private HttpServletRequest request;
	private Connection con;
	private Statement stat;
	
	public DBProcess(HttpServletRequest request){
		this.request=request;
		ServletContext ctx=request.getSession().getServletContext();
		con=(Connection)ctx.getAttribute("DBCon");
		
	}
	public void save()throws Exception{
		String insertSQL="insert into book (bid,name,authors,publisher,publishdate,price) value(?,?,?,?,?,?)";
		String updateSQL="update book set bid=?,name=?,authors=?,publisher=?,publishdate=?,price=? where id=?";
		ArrayList<String> params=new ArrayList<String>();
		params.add(request.getParameter("bid"));
		params.add(request.getParameter("name"));
		params.add(request.getParameter("authors"));
		params.add(request.getParameter("publisher"));
		params.add(request.getParameter("publishdate"));
		params.add(request.getParameter("price"));
		
		String id=request.getParameter("id");
		
		if("".equals(id)){
			exePrepare(insertSQL,params);
		}else{
			params.add(id);
			exePrepare(updateSQL,params);
			
		}
			
	}
	
	public ArrayList<Book> getBookList()throws SQLException{
		String sql="select * from book";
		ResultSet rs =getRS(sql);
		ArrayList<Book> bookList=new ArrayList<Book>();
		while(rs.next()){
			bookList.add(toBook(rs));
			
		}
		//request.setAttribute("bookList", bookList);//应该写在servlet中
		closeRS(rs);
		return bookList;
	}
	
	public Book getBookById()throws SQLException{
		String sql= "select * from book where id=";
		String id=request.getParameter("id");
		sql+=id;
		ResultSet rs=getRS(sql);
		if(rs.next()){
			Book book=toBook(rs);
			//request.setAttribute("book",book);
		}
		//closeRS(rs);
		return toBook(rs);
		
		
	}
	public void deleteBookById()throws SQLException{
		String sql="delete from book where id=?";
		String id=request.getParameter("id");
		ArrayList<String> params=new ArrayList<String>();
		params.add(id);
		exePrepare(sql,params);
		
	}
	
	private Book toBook(ResultSet rs)throws SQLException{
		Book book=new Book();
		book.setId(rs.getInt("id"));
		book.setBid(rs.getString("bid"));
		book.setAuthors(rs.getString("authors"));
		book.setName(rs.getString("name"));
		book.setPrice(rs.getString("price"));
		book.setPublishdate(rs.getString("publishdate"));
		book.setPublisher(rs.getString("publisher"));
		return book;
		
	}
	
	private void exePrepare(String sql,ArrayList<String> params)throws SQLException{
		PreparedStatement pstat =con.prepareStatement(sql);
		int i=1;
		for(String param:params){
			pstat.setString(i++, param);
			
		}
		pstat.execute();
		pstat.close();
	}
		
		private ResultSet getRS(String sql)throws SQLException{
			stat=con.createStatement();
			ResultSet res=stat.executeQuery(sql);
			return res;
			
		}
		private void closeRS(ResultSet rs){
			try{
				stat.close();
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	
}
