package pl.failmasters.site;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.dao.ProductDao;
import pl.failmasters.site.dao.UserDao;
import pl.failmasters.site.product.ProductDto;
import pl.failmasters.site.user.UserDto;

public class Connector {

	private static final String TEST_PRODUCT_NAME = "odkurzacz SSACZ-200";
	private static final String TEST_PRODUCT_NAME_AFTER_UPDATE = "odkurzacz SSACZ-200 XL";
	private static final String TEST_USER_LOGIN = "miszczu";
	private static final String TEST_USER_PASSWORD = "pass123";

	private static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);

	public static void main(String[] args) {
		/**
		 * those are basically integration tests and form of a use case<br/>
		 * disclaimer: product:name and user:login are unique in db
		 */

		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.info("<< Started running.");
		LOGGER.info("");

		LOGGER.info("---- USERS ----");
		UserDao userDao = new UserDao();
		LOGGER.info("all users: {}", userDao.getAll());

		userDao.insert(new UserDto("Adam", "Ptak", "aa@wp.pl", TEST_USER_PASSWORD, TEST_USER_LOGIN));
		LOGGER.info("all users after insert: {}", userDao.getAll());
		LOGGER.info("user miszczu: {}", userDao.get(TEST_USER_LOGIN));

		LOGGER.info("correct authentication: {}", userDao.login(TEST_USER_LOGIN, TEST_USER_PASSWORD));
		LOGGER.info("incorrect authentication - false is good here: {}", userDao.login("hackier", TEST_USER_PASSWORD));

		userDao.update(new UserDto("Magda", "Kot", "aaa@onet.pl", "maslo", TEST_USER_LOGIN));
		int userId = userDao.getIdByName(TEST_USER_LOGIN);
		LOGGER.info("user miszczu after update: {}", userDao.get(userId));

		userDao.delete(TEST_USER_LOGIN);
		LOGGER.info("all users after delete: {}", userDao.getAll());

		LOGGER.info("");
		LOGGER.info("---- PRODUCTS ----");
		ProductDao productDao = new ProductDao();
		LOGGER.info("all products: {}", productDao.getAll());

		productDao.insert(new ProductDto(TEST_PRODUCT_NAME, "Ciągnie lepiej niż Twoja stara!", 5,
				"http://twojastara.pl/kupon.jpg"));
		LOGGER.info("all products after insert: {}", productDao.getAll());

		int productId = productDao.getIdByName(TEST_PRODUCT_NAME);
		LOGGER.info("Product \"odkurzacz SSACZ-200\": {}", productDao.get(productId));

		productDao.update(new ProductDto(productId, TEST_PRODUCT_NAME_AFTER_UPDATE,
				"Ciągnie lepiej niż Twoja stara na tripie!", 2, "http://twojastara.pl/kupon_xl.jpg"));
		LOGGER.info("Product \"odkurzacz SSACZ-200 XL\" after update: {}",
				productDao.get(TEST_PRODUCT_NAME_AFTER_UPDATE));

		productDao.delete(TEST_PRODUCT_NAME_AFTER_UPDATE);
		LOGGER.info("all products after delete: {}", productDao.getAll());

		watch.stop();
		LOGGER.info("");
		LOGGER.info("<< Finished in {} ms.", watch.getTime(TimeUnit.MILLISECONDS));
	}

}
