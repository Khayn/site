package pl.failmasters.site.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.Connector;
import pl.failmasters.site.connection.ConnectionData;
import pl.failmasters.site.connection.ConnectionFactory;
import pl.failmasters.site.user.UserDto;

public class UserDao implements Dao<UserDto> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);
	private final ConnectionData data = getConnectionData();

	@Override
	public UserDto getUser(final String login) {
		String query = "SELECT * FROM users WHERE login=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new UserDto(rs);
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting user by login!");
		}

		return null;
	}

	@Override
	public UserDto getUserByLoginAndPassword(String login, String pass) {
		String query = "SELECT * FROM users WHERE login=? AND password=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new UserDto(rs);
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting user by login and password!");
		}

		return null;
	}

	@Override
	public Set<UserDto> getAllUsers() {
		String query = "SELECT * FROM users ORDER BY id";
		Connection connection = ConnectionFactory.getConnection(data);

		Set<UserDto> users = new HashSet<>();

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				users.add(new UserDto(rs));
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting all users!");
		}

		return users;
	}

	@Override
	public boolean insertUser(UserDto user) {
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
			LOGGER.error("<< SQLException thrown while inserting user!");
		}

		return false;
	}

	@Override
	public boolean updateUser(UserDto user) {
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
			LOGGER.error("<< SQLException thrown while updating user!");
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
			LOGGER.error("<< SQLException thrown while deleting user!");
		}

		return false;
	}

	private ConnectionData getConnectionData() {
		Properties props = new Properties();
		InputStream inputStream = UserDao.class.getResourceAsStream("/db.properties");

		try {
			props.load(inputStream);

		} catch (IOException e) {
			LOGGER.error("<< IOException thrown while getting props!");

		}

		return new ConnectionData(props);

	}
}
