package pl.failmasters.site.product;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class ProductColumnsTest {

	@Test
	public void testEnum() {
		Stream.of(ProductColumns.values()).forEach(c -> c.getDbColumnName());
	}
}
