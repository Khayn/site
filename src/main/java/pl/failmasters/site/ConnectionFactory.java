package pl.failmasters.site;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConnectionFactory {

	public static Connection getConnection(ConnectionData data) {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(data.getConnectionString(), data.getDbUser(), data.getDbPassword());

		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

}
