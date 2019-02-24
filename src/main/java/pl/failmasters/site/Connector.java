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

	public static final String insertQuery =
			"INSERT INTO 'users' ('name', 'surename', 'email', 'password') VALUES ('Magda', 'Dzik', 'dzik@abc.pl', 'haslo123');";

	public static void main(String[] args) throws SQLException {
		Connector conn = new Connector();
		Connection connection = DriverManager.getConnection(CONNECTION_STR, DB_USER, DB_PASSWORD);

		conn.printUserTable(connection);
		conn.insertUser(connection, insertQuery);

		conn.updateUserSurename(connection, "?bik", "Dzik");

		conn.printUserTable(connection);

		conn.deleteUser(connection, "Magda");
	}

	public void printUserTable(final Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from users order by surename");

		while (result.next()) {
			for (Columns dbColumn : Columns.values()) {
				String dbColumnName = dbColumn.getDbColumnName();
				System.out.printf("Column %s : %s \n", dbColumnName, result.getString(dbColumnName));
			}

			System.out.println();
		}
	}

	public void insertUser(final Connection connection, final String query) throws SQLException {
		Statement statement = connection.createStatement();

		int affectedRows = statement.executeUpdate(query);
		System.out.printf("affected rows: %d \n", affectedRows);

	}

	public void updateUserSurename(final Connection connection, final String previousSurename, final String newSurename)
			throws SQLException {
		Statement statement = connection.createStatement();

		String query = "update users set surename='" + newSurename + "' where surename='" + previousSurename + "'";
		int affectedRows = statement.executeUpdate(query);

		System.out.printf("affected rows: %d \n", affectedRows);

	}

	public void deleteUser(final Connection connection, final String surename) throws SQLException {
		Statement statement = connection.createStatement();

		String query = "delete from users where surename='" + surename + "'";
		int affectedRows = statement.executeUpdate(query);

		System.out.printf("affected rows: %d \n", affectedRows);
	}

}
