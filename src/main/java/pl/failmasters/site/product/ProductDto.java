package pl.failmasters.site.product;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDto.class);

	private int id;
	private String name;
	private String desc;
	private int quantity;
	private InputStream image;

	public ProductDto(int id, String name, String desc, int quantity, InputStream image) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = image;
	}

	public ProductDto(String name, String desc, int quantity, InputStream image) {
		super();
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = image;
	}

	public ProductDto(String name, String desc, int quantity, String imagePath) {
		super();

		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = getFileInputStream(imagePath);
	}

	public ProductDto(int id, String name, String desc, int quantity, String imagePath) {
		super();

		this.id = id;
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = getFileInputStream(imagePath);
	}

	public static ProductDto newProductDto(int id, String name, String desc, int quantity, String imagePath) {
		ProductDto dto = new ProductDto(name, desc, quantity, imagePath);
		dto.setId(id);

		return dto;

	}

	public ProductDto(ResultSet rs) {

		try {
			this.id = rs.getInt(ProductColumns.ID.getDbColumnName());
			this.name = rs.getString(ProductColumns.NAME.getDbColumnName());
			this.desc = rs.getString(ProductColumns.DESCRIPTION.getDbColumnName());
			this.quantity = rs.getInt(ProductColumns.QUANTITY.getDbColumnName());
			this.image = rs.getBinaryStream(ProductColumns.IMAGE.getDbColumnName());

		} catch (SQLException e) {
			LOGGER.error("<< SQLException thrown while getting constructing ProductDto: {}, ResultSet: {}",
					e.getMessage(), rs);
		}
	}

	public ProductDto() {

	}

	private InputStream getFileInputStream(String path) {
		InputStream inputStream = ProductDto.class.getResourceAsStream("/images/" + path);

		LOGGER.info("<< Got product image input stream: {}", inputStream);
		return inputStream;
	}
}
