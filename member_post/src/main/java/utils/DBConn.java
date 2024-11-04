package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection() throws ClassNotFoundException, SQLException { //여기선 던져도됨
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mariadb://54.180.26.240:3306/post", "sample", "1234");
	}
}
