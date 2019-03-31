package pl.failmasters.site.user;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class UserColumnsTest {

	@Test
	public void testEnum() {
		Stream.of(UserColumns.values()).forEach(c -> c.getDbColumnName());
	}
}
