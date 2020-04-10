package domain.model;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		
		try {
			// 1. Get a connection to a database
			
				String db_url = "jdbc:postgresql://localhost:8080/ingredients";
				String user = "root";
				String pwd = "root";
				
				Connection conn = DriverManager.getConnection(db_url, user, pwd);
			
			// 2. Create a statement
			
				Statement statement = conn.createStatement();
				
			// 3. Execute a SQL query
				
				ResultSet resSet = statement.executeQuery("select name from ingredients");
			
			// 4. Process the result set
				
				while(resSet.next()) {
					System.out.println(resSet.getString("name"));
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
