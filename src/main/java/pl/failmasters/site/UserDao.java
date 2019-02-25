package pl.failmasters.site;

import java.util.Set;

public interface UserDao {
	User getUser(String login);

	Set<User> getAllUsers();

	User getUserByLoginAndPassword(String login, String pass);

	boolean insertUser(User user);

	boolean updateUser(User user);

	boolean deleteUser(String login);
}
