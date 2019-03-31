package pl.failmasters.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.dao.UserDao;
import pl.failmasters.site.user.UserDto;

public class Connector {

	private static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);

	public static void main(String[] args) {

		UserDao dao = new UserDao();
		LOGGER.info("all users: {}", dao.getAllUsers());

		dao.insertUser(new UserDto("Adam", "Ptak", "aa@wp.pl", "pass123", "miszczu"));
		LOGGER.info("all users after insert: {}", dao.getAllUsers());
		LOGGER.info("user miszczu: {}", dao.getUser("miszczu"));

		dao.updateUser(new UserDto("Magda", "Kot", "aaa@onet.pl", "maslo", "miszczu"));
		LOGGER.info("all users after update: {}", dao.getAllUsers());

		dao.deleteUser("miszczu");
		LOGGER.info("all users after delete: {}", dao.getAllUsers());

	}

}
