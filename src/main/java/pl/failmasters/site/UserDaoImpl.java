package pl.failmasters.site;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class UserDaoImpl implements UserDao {

	private final ConnectionData data = getConnectionData();

	@Override
	public User getUser(final String login) {
		String query = "SELECT * FROM users WHERE login=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public User getUserByLoginAndPassword(String login, String pass) {
		String query = "SELECT * FROM users WHERE login=? AND password=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Set<User> getAllUsers() {
		String query = "SELECT * FROM users ORDER BY id";
		Connection connection = ConnectionFactory.getConnection(data);

		Set<User> users = new HashSet<>();

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				users.add(new User(rs));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return users;
	}

	@Override
	public boolean insertUser(User user) {
		String query = "INSERT INTO users (name, surename, login, email, password) VALUES (?, ?, ?, ?, ?)";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurename());
			stmt.setString(3, user.getLogin());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPassword());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateUser(User user) {
		String query = "UPDATE users SET name=?, surename=?, email=?, password=? WHERE login=?";

		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurename());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getLogin());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteUser(String login) {
		String query = "DELETE FROM users WHERE login=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	private ConnectionData getConnectionData() {
		InputStream inputStream = getClass().getResourceAsStream("db.properties");
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
