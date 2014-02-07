import java.io.*;  
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;  
import java.sql.DriverManager;   
import java.sql.SQLException;    
import java.util.ArrayList; 

import javax.servlet.*;  
import javax.servlet.http.*;  


public class FirstServlet extends HttpServlet  
{  
	
	private static Connection con = null;  
    private static Statement stmt = null;  
    private static ResultSet rs = null; 
	
    public void service(HttpServletRequest request,HttpServletResponse response)  
    throws IOException  
    {  
        PrintWriter out=response.getWriter();  
        out.print("My First Servlet!");  
        
        try {  
            Class.forName("com.mysql.jdbc.Driver"); 
            
           // String ConnString = "jdbc:"+System.getenv("OPENSHIFT_MYSQL_DB_URL")+"awsTest?user="+System.getenv("OPENSHIFT_MYSQL_DB_USERNAME")+"&passwrod="+System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
           // String ConnString = "jdbc:MySQL://"+System.getenv("OPENSHIFT_MYSQL_DB_HOST")+":"+System.getenv("OPENSHIFT_MYSQL_DB_PORT")+"/awsTest";
            //
            try {  
                con = DriverManager.getConnection(  
                        "jdbc:MySQL://127.0.0.1:3306/awsTest", "root", "1234");  
            	
            	//con = DriverManager.getConnection(ConnString, System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"), System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } catch (ClassNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        
        try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        try {
			rs = stmt.executeQuery("select sid,sname,sage from student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        try {
			while (rs.next()) {  
			    //Stu st = new Stu();  
			    out.println(rs.getString("sid"));  
			    out.println(rs.getString("sname"));  
			    out.println(rs.getString("sage"));  
			      
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        if (rs != null) {  
            try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            rs = null;  
        }  
        if (stmt != null) {  
            try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            stmt = null;  
        }  
        if (con != null) {  
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            con = null;  
        }   
        
    }  
}