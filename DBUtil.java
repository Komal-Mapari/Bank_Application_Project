
package com.bank.configure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		//step 1.Load Driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//step 2.Create or establish database connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fd14", "root", "root");
		
	     return con;
	}

}
