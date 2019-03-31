package pl.failmasters.site.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.dao.ProductDao;

public class UserDto {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);

	private Integer id;
	private String name;
	private String surename;
	private String email;
	private String password;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surename=");
		builder.append(surename);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", login=");
		builder.append(login);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surename == null) ? 0 : surename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserDto other = (UserDto) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (surename == null) {
			if (other.surename != null) {
				return false;
			}
		} else if (!surename.equals(other.surename)) {
			return false;
		}
		return true;
	}

}
