package pl.failmasters.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connector {

	private static Logger LOGGER = LoggerFactory.getLogger(Connector.class);

	public static void main(String[] args) {

		UserDao dao = new UserDaoImpl();
		System.out.println("all users: " + dao.getAllUsers().toString());

		dao.insertUser(new User("Adam", "Ptak", "aa@wp.pl", "pass123", "miszczu"));
		System.out.println("all users after insert: " + dao.getAllUsers().toString());
		System.out.println("user miszczu: " + dao.getUser("miszczu"));

		dao.updateUser(new User("Magda", "Kot", "aaa@onet.pl", "maslo", "miszczu"));
		System.out.println("all users after update: " + dao.getAllUsers().toString());

		dao.deleteUser("miszczu");
		System.out.println("all users after delete: " + dao.getAllUsers().toString());

	}

}
