package pl.failmasters.site.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.connection.ConnectionData;

public interface Dao<T> {

	static final Logger LOGGER = LoggerFactory.getLogger(Dao.class);

	T get(int id);

	T get(String key);

	Set<T> getAll();

	boolean insert(T user);

	boolean update(T user);

	boolean delete(int id);

	boolean delete(String key);

	int getIdByName(String name);

	public default ConnectionData getConnectionData() {
		Properties props = new Properties();
		InputStream inputStream = UserDao.class.getResourceAsStream("/db.properties");

		try {
			props.load(inputStream);

		} catch (IOException e) {
			LOGGER.error("<< IOException thrown while getting props: {}", e.getMessage());

		}

		return new ConnectionData(props);

	}
}
