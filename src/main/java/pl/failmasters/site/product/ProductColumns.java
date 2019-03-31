package pl.failmasters.site.product;

public enum ProductColumns {
	ID("id"), NAME("name"), DESCRIPTION("description"), QUANTITY("quantity"), IMAGE("image");

	private String dbColumnName;

	private ProductColumns(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

}
