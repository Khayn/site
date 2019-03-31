package pl.failmasters.site.user;

public enum UserColumns {

	ID("id"), NAME("name"), SURENAME("surename"), LOGIN("login"), EMAIL("email"), PASSWORD("password");

	private String dbColumnName;

	public String getDbColumnName() {
		return dbColumnName;
	}

	UserColumns(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}
}
