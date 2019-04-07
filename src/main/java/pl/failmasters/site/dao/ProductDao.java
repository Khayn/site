package pl.failmasters.site.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.failmasters.site.connection.ConnectionData;
import pl.failmasters.site.connection.ConnectionFactory;
import pl.failmasters.site.product.ProductDto;

public class ProductDao implements Dao<ProductDto> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);
	private final ConnectionData data = getConnectionData();

	@Override
	public ProductDto get(int id) {
		String query = "SELECT * FROM products WHERE id=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					return new ProductDto(rs);
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting product by id: {}", ex.getMessage());
		}

		return null;
	}

	@Override
	public Set<ProductDto> getAll() {
		String query = "SELECT * FROM products ORDER BY id";
		Connection connection = ConnectionFactory.getConnection(data);

		Set<ProductDto> products = new HashSet<>();

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				products.add(new ProductDto(rs));
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting all products: {}", ex.getMessage());
		}

		return products;
	}

	@Override
	public boolean insert(ProductDto product) {
		String query = "INSERT INTO products (name, description, quantity, image) VALUES (?, ?, ?, ?)";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDesc());
			stmt.setInt(3, product.getQuantity());
			stmt.setBinaryStream(4, product.getImage());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while inserting product: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public boolean update(ProductDto product) {
		String query = "UPDATE products SET name=?, description=?, quantity=?, image=? WHERE id=?";

		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDesc());
			stmt.setInt(3, product.getQuantity());
			stmt.setBinaryStream(4, product.getImage());
			stmt.setInt(5, product.getId());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while updating product: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM products WHERE id=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setInt(1, id);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while deleting product by id: {}", ex.getMessage());
		}

		return false;
	}

	@Override
	public int getIdByName(String name) {
		String query = "SELECT * FROM products WHERE name=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, name);
			try (ResultSet rs = stmt.executeQuery();) {

				while (rs.next()) {
					ProductDto product = new ProductDto(rs);

					return product.getId();
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting productId by name: {}", ex.getMessage());
		}

		LOGGER.warn("<< No product with name {} found! Returning -1", name);
		return -1;
	}

	@Override
	public ProductDto get(String name) {
		String query = "SELECT * FROM products WHERE name=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, name);
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					return new ProductDto(rs);
				}

			}
		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while getting product by name: {}", ex.getMessage());
		}

		return null;
	}

	@Override
	public boolean delete(String name) {
		String query = "DELETE FROM products WHERE name=?";
		Connection connection = ConnectionFactory.getConnection(data);

		try (PreparedStatement stmt = connection.prepareStatement(query);) {

			stmt.setString(1, name);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 1) {
				return true;
			}

		} catch (SQLException ex) {
			LOGGER.error("<< SQLException thrown while deleting product by name: {}", ex.getMessage());
		}

		return false;
	}
}
