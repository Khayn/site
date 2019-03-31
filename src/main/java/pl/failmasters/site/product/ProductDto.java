package pl.failmasters.site.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ProductDto {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDto.class);

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	int id;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	String name;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	String desc;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	int quantity;

	@Getter(value = AccessLevel.PUBLIC)
	@Setter(value = AccessLevel.PUBLIC)
	String image;

	public ProductDto(int id, String name, String desc, int quantity, String image) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = image;
	}

	public ProductDto(String name, String desc, int quantity, String image) {
		super();
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = image;
	}

	public ProductDto(ResultSet rs) {

		try {
			this.id = rs.getInt(ProductColumns.ID.getDbColumnName());
			this.name = rs.getString(ProductColumns.NAME.getDbColumnName());
			this.desc = rs.getString(ProductColumns.DESCRIPTION.getDbColumnName());
			this.quantity = rs.getInt(ProductColumns.QUANTITY.getDbColumnName());
			this.image = rs.getString(ProductColumns.IMAGE.getDbColumnName());

		} catch (SQLException e) {
			LOGGER.error("<< SQLException thrown while getting constructing ProductDto: {}, ResultSet: {}",
					e.getMessage(), rs);
		}
	}

	public ProductDto() {

	}

}
