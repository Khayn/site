package pl.failmasters.site.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.connection.ConnectionData;
import pl.failmasters.site.connection.ConnectionFactory;
import pl.failmasters.site.user.PasswordHasher;
import pl.failmasters.site.user.UserDto;

public class UserDao implements Dao<UserDto> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
	private final ConnectionData data = getConnectionData();

	public boolean login(String login, String password) {
		String query = "SELECT * FROM users WHERE login=? AND password=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			stmt.setString(2, new PasswordHasher(password).hash());
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					LOGGER.info("<< Authentication sucessful.");
					return true;

				} else {
					LOGGER.warn("<< Incorrect authentication!");
					return false;
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting user by login and password: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public UserDto get(final int id) {
		String query = "SELECT * FROM users WHERE id=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					return new UserDto(rs);
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting user by id: {}", ex.getMessage());
		}

		return null;
	}

	@Override
	public UserDto get(final String login) {
		String query = "SELECT * FROM users WHERE login=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					return new UserDto(rs);
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting user by login: {}", ex.getMessage());
		}

		return null;
	}

	public UserDto getUserByLoginAndPassword(String login, String pass) {
		String query = "SELECT * FROM users WHERE login=? AND password=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			stmt.setString(2, pass);
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					return new UserDto(rs);
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting user by login and password: {}", ex.getMessage());
		}

		return null;
	}

	@Override
	public Set<UserDto> getAll() {
		String query = "SELECT * FROM users ORDER BY id";
		Connection connection = ConnectionFactory.getConnection(data);

		Set<UserDto> users = new HashSet<>();

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				users.add(new UserDto(rs));
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting all users: {}", ex.getMessage());
		}

		return users;
	}

	@Override
	public boolean insert(UserDto user) {
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
			LOGGER.error("<< SQLException thrown while inserting user: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public boolean update(UserDto user) {
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
			LOGGER.error("<< SQLException thrown while updating user: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(String login) {
		String query = "DELETE FROM users WHERE login=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while deleting user by login: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM users WHERE id=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setInt(1, id);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while deleting user by id: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public int getIdByName(String login) {
		String query = "SELECT * FROM users WHERE login=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, login);
			try (ResultSet rs = stmt.executeQuery();) {

				while (rs.next()) {
					UserDto user = new UserDto(rs);

					return user.getId();
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting userId by login: {}", ex.getMessage());
		}

		LOGGER.warn("<< No user with login {} found! Returning -1", login);
		return -1;
	}
}
