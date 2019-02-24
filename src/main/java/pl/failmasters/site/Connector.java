package pl.failmasters.site;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

	public static final String CONNECTION_STR = "jdbc:mysql://khayn.synology.me:5306/site";

	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "crd123!";

	public static void main(String[] args) throws SQLException {
		Connection myConnection = DriverManager.getConnection(CONNECTION_STR, DB_USER, DB_PASSWORD);
		Statement statement = myConnection.createStatement();

		ResultSet result = statement.executeQuery("select * from users");

		while (result.next()) {
			for (Columns dbColumn : Columns.values()) {
				String dbColumnName = dbColumn.getDbColumnName();
				System.out.printf("Column %s : %s \n", dbColumnName, result.getString(dbColumnName));
			}
		}
	}

}
