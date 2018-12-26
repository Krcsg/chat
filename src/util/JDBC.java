package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public final static String Driver = "oracle.jdbc.driver.OracleDriver";
	public final static String USER = "c##kang";
	public final static String PSW = "123456";
	public final static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static Connection getConnection(){
		try {
			Class.forName(Driver);
			Connection conn = DriverManager.getConnection(URL,USER, PSW);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void close(Connection conn,Statement stm,ResultSet set){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(set!=null){
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
