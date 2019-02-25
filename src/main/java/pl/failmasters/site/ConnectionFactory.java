package pl.failmasters.site;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

public class ConnectionFactory {
	public static final String CONNECTION_STR = "jdbc:mysql://khayn.synology.me:5306/site";
	public static final String DB_USER = "root";
	public static final String DB_PWD = "crd123!";

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(CONNECTION_STR, DB_USER, DB_PWD);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	private ConnectionData getConnectionData() {
		InputStream inputStream = Connector.class.getClassLoader().getResourceAsStream("db.properties");
		Properties props = new Properties();

		try {
			props.load(inputStream);
			System.out.println("<< got props: " + props);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ConnectionData(props);

	}
}
