package pl.failmasters.site;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

	public static final String CONNECTION_STR = "jdbc:mysql://khayn.synology.me:5306/site";
	public static final String DB_USER = "root";
	public static final String DB_PWD = "crd123!";

	public static void main(String[] args) {
		Connector conn = new Connector();
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(CONNECTION_STR, DB_USER, DB_PWD);

			conn.printUserTable(connection);

			conn.insertUser(connection);
			conn.updateUserSurename(connection, "Dzik", "Kot");
			conn.deleteUser(connection, "Kot");

			conn.printUserTable(connection);

		} catch (SQLException e) {

		}

	}

	public void printUserTable(final Connection connection) {
		Statement statement = null;
		ResultSet result = null;

		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM users order by surename");

			while (result.next()) {
				for (Columns dbColumn : Columns.values()) {
					String dbColumnName = dbColumn.getDbColumnName();
					System.out.printf("Column %s : %s \n", dbColumnName, result.getString(dbColumnName));
				}

				System.out.println();
			}

		} catch (SQLException e) {

		}
	}

	public void insertUser(final Connection connection) {
		PreparedStatement statement = null;

		try {
			String insertQuery = "INSERT INTO users (name, surename, email, password) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, "Magda");
			statement.setString(2, "Dzik");
			statement.setString(3, "abc@test.pl");
			statement.setString(4, "haslo123");

			int affectedRows = statement.executeUpdate();
			System.out.printf("insert affected rows: %d \n\n", affectedRows);

		} catch (SQLException e) {

		}
	}

	public void updateUserSurename(final Connection connection, final String previousSurename,
			final String newSurename) {
		PreparedStatement statement = null;

		try {
			String query = "UPDATE users SET surename=? WHERE surename=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, newSurename);
			statement.setString(2, previousSurename);

			int affectedRows = statement.executeUpdate();

			System.out.printf("update affected rows: %d \n\n", affectedRows);

		} catch (SQLException e) {

		}
	}

	public void deleteUser(final Connection connection, final String surename) {
		PreparedStatement statement = null;

		try {
			String query = "delete from users where surename=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, surename);

			int affectedRows = statement.executeUpdate();

			System.out.printf("delete affected rows: %d \n\n", affectedRows);

		} catch (SQLException e) {

		}
	}

}
