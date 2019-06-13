package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	//����DB����
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private static Connection conn;
	//��ȡConnection����
	public static Connection getConnection() throws SQLException {
		if (conn==null||conn.isClosed()) {
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","fsrmslth");
		}
		return conn;
	}
	//�ر���Դ
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
