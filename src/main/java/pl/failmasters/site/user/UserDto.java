package pl.failmasters.site.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.failmasters.site.dao.ProductDao;

@ToString
@EqualsAndHashCode
@Builder
public class UserDto {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	private Integer id;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	private String name;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	private String surename;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	private String email;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	private String password;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	private String login;

	public UserDto(ResultSet rs) {

		try {
			this.id = rs.getInt(UserColumns.ID.getDbColumnName());
			this.name = rs.getString(UserColumns.NAME.getDbColumnName());
			this.surename = rs.getString(UserColumns.SURENAME.getDbColumnName());
			this.email = rs.getString(UserColumns.EMAIL.getDbColumnName());
			this.password = rs.getString(UserColumns.PASSWORD.getDbColumnName());
			this.login = rs.getString(UserColumns.LOGIN.getDbColumnName());

		} catch (SQLException e) {
			LOGGER.error("<< SQLException thrown while getting constructing UserDto: {}, ResultSet: {}", e.getMessage(),
					rs);
		}
	}

	public UserDto(String name, String surename, String email, String password, String login) {
		super();
		this.name = name;
		this.surename = surename;
		this.email = email;
		this.password = new PasswordHasher(password).hash();
		this.login = login;
	}

	public UserDto(Integer id, String name, String surename, String email, String password, String login) {
		super();
		this.id = id;
		this.name = name;
		this.surename = surename;
		this.email = email;
		this.password = new PasswordHasher(password).hash();
		this.login = login;
	}

	public UserDto() {

	}

}
