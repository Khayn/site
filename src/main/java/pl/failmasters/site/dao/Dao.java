package pl.failmasters.site.dao;

import java.util.Set;

public interface Dao<T> {
	T getUser(String login);

	Set<T> getAllUsers();

	T getUserByLoginAndPassword(String login, String pass);

	boolean insertUser(T user);

	boolean updateUser(T user);

	boolean deleteUser(String login);
}
