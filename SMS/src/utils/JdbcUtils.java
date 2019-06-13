package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	//加载DB驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private static Connection conn;
	//获取Connection对象
	public static Connection getConnection() throws SQLException {
		if (conn==null||conn.isClosed()) {
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","fsrmslth");
		}
		return conn;
	}
	//关闭资源
	public static void close(Connection conn,Statement stmt,ResultSet rs) throws SQLException {
		
		if (stmt!=null) {
			stmt.close();
			
		}
		if (conn!=null&&!conn.isClosed()) {
			conn.close();
		}
		
		if (rs!=null) {
			rs.close();
		}
		
	}
}
