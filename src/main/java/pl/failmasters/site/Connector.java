package pl.failmasters.site;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connector {

	public static final String CONNECTION_STR = "jdbc:mysql://khayn.synology.me:5306/site";
	public static final String DB_USER = "root";
	public static final String DB_PWD = "crd123!";

	public static void main(String[] args) {
		Connector conn = new Connector();

		ConnectionData data = conn.getConnectionData();
		Connection connection = null;

		try {

			// nie pobiera zawartosci pliku, do poprawy - cos jest skopane z
			// classpath?
			// connection =
			// DriverManager.getConnection(data.getConnectionString(),
			// data.getDbUser(), data.getDbPassword());

			connection = DriverManager.getConnection(CONNECTION_STR, DB_USER, DB_PWD);

			conn.insertUser(connection);
			conn.printUserTable(connection);

			conn.updateUserSurename(connection, "Dzik", "Kot");
			conn.deleteUser(connection, "Kot");

			conn.printUserTable(connection);

		} catch (SQLException e) {

		}

	}

	public void printUserTable(final Connection connection) {

		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM users order by surename")) {

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
		String insertQuery = "INSERT INTO users (name, surename, email, password) VALUES (?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(insertQuery);) {

			statement.setString(1, "Magda");
			statement.setString(2, "Dzik");
			statement.setString(3, "abc@test.pl");
			statement.setString(4, "haslo123");

			int affectedRows = statement.executeUpdate();
			System.out.println("insert affected rows: " + affectedRows);

		} catch (SQLException e) {

		}
	}

	public void updateUserSurename(final Connection connection, final String previousSurename,
			final String newSurename) {
		String query = "UPDATE users SET surename=? WHERE surename=?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, newSurename);
			statement.setString(2, previousSurename);

			int affectedRows = statement.executeUpdate();

			System.out.printf("update affected rows: %d \n\n", affectedRows);

		} catch (SQLException e) {

		}
	}

	public void deleteUser(final Connection connection, final String surename) {
		String query = "delete from users where surename=?";

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, surename);

			int affectedRows = statement.executeUpdate();

			System.out.printf("delete affected rows: %d \n\n", affectedRows);

		} catch (SQLException e) {

		}
	}

	public ConnectionData getConnectionData() {
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
