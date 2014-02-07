

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecServlet
 */
public class SecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection con = null;  
    private static Statement stmt = null;  
    private static ResultSet rs = null; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
