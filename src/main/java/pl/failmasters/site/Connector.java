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

	private static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);

	public static void main(String[] args) {

		/**
		 * those are basically integration tests and form of a use case
		 */
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.info("<< Started running.");
		LOGGER.info("");

		LOGGER.info("---- USERS ----");
		UserDao userDao = new UserDao();
		LOGGER.info("all users: {}", userDao.getAll());

		userDao.insert(new UserDto("Adam", "Ptak", "aa@wp.pl", "pass123", "miszczu"));
		LOGGER.info("all users after insert: {}", userDao.getAll());
		LOGGER.info("user miszczu: {}", userDao.get("miszczu"));

		LOGGER.info("correct authentication: {}", userDao.login("miszczu", "pass123"));
		LOGGER.info("incorrect authentication - false is good here: {}", userDao.login("hackier", "pass123"));

		userDao.update(new UserDto("Magda", "Kot", "aaa@onet.pl", "maslo", "miszczu"));
		int userId = userDao.getIdByName("miszczu");
		LOGGER.info("user miszczu after update: {}", userDao.get(userId));

		userDao.delete("miszczu");
		LOGGER.info("all users after delete: {}", userDao.getAll());

		LOGGER.info("");
		LOGGER.info("---- PRODUCTS ----");
		ProductDao productDao = new ProductDao();
		LOGGER.info("all products: {}", productDao.getAll());

		productDao.insert(new ProductDto("odkurzacz SSACZ-200", "Ciągnie lepiej niż Twoja stara!", 5,
				"http://twojastara.pl/kupon.jpg"));
		LOGGER.info("all products after insert: {}", productDao.getAll());

		int productId = productDao.getIdByName("odkurzacz SSACZ-200");
		LOGGER.info("Product \"odkurzacz SSACZ-200\": {}", productDao.get(productId));

		productDao.update(new ProductDto(productId, "odkurzacz SSACZ-200 XL",
				"Ciągnie lepiej niż Twoja stara na tripie!", 2, "http://twojastara.pl/kupon_xl.jpg"));
		LOGGER.info("Product \"odkurzacz SSACZ-200 XL\" after update: {}", productDao.get("odkurzacz SSACZ-200 XL"));

		productDao.delete("odkurzacz SSACZ-200 XL");
		LOGGER.info("all products after delete: {}", productDao.getAll());

		watch.stop();
		LOGGER.info("");
		LOGGER.info("<< Finished in {} ms.", watch.getTime(TimeUnit.MILLISECONDS));
	}

}
