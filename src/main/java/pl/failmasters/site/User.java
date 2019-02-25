package pl.failmasters.site;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

	public User(ResultSet rs) throws SQLException {
		this.id = rs.getInt(Columns.ID.getDbColumnName());
		this.name = rs.getString(Columns.NAME.getDbColumnName());
		this.surename = rs.getString(Columns.SURENAME.getDbColumnName());
		this.email = rs.getString(Columns.EMAIL.getDbColumnName());
		this.password = rs.getString(Columns.PASSWORD.getDbColumnName());
		this.login = rs.getString(Columns.LOGIN.getDbColumnName());
	}

	public User(String name, String surename, String email, String password, String login) {
		super();
		this.name = name;
		this.surename = surename;
		this.email = email;
		this.password = password;
		this.login = login;
	}

	public User(Integer id, String name, String surename, String email, String password, String login) {
		super();
		this.id = id;
		this.name = name;
		this.surename = surename;
		this.email = email;
		this.password = password;
		this.login = login;
	}

	public User() {

	}

	private Integer id;
	private String name;
	private String surename;
	private String email;
	private String password;
	private String login;

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

}
