package pl.failmasters.site.connection;

import java.util.Properties;

public class ConnectionData {

	private String connectionString;
	private String dbUser;
	private String dbPassword;

	public ConnectionData(Properties props) {
		this.connectionString = props.getProperty("db.string");
		this.dbUser = props.getProperty("db.user");
		this.dbPassword = props.getProperty("db.password");
	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConnectionData [connectionString=");
		builder.append(connectionString);
		builder.append(", dbUser=");
		builder.append(dbUser);
		builder.append(", dbPassword=");
		builder.append(dbPassword);
		builder.append("]");
		return builder.toString();
	}
}
