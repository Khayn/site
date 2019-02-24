package pl.failmasters.site;

public enum Columns {

	ID("id"), NAME("name"), SURENAME("surename"), EMAIL("email"), PASSWORD("password");

	private String dbColumnName;

	public String getDbColumnName() {
		return dbColumnName;
	}

	Columns(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}
}
