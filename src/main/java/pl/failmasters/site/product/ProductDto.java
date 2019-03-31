package pl.failmasters.site.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDto {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDto.class);

	int id;
	String name;
	String desc;
	int quantity;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
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
		ProductDto other = (ProductDto) obj;
		if (desc == null) {
			if (other.desc != null) {
				return false;
			}
		} else if (!desc.equals(other.desc)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (image == null) {
			if (other.image != null) {
				return false;
			}
		} else if (!image.equals(other.image)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		return true;
	}

}
