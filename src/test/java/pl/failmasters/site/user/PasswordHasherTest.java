package pl.failmasters.site.user;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.Test;

public class PasswordHasherTest {

	@Test
	public void shouldReturnCorrectHash() {
		PasswordHasher hasher = new PasswordHasher("password123");

		org.hamcrest.MatcherAssert.assertThat(hasher.hash(), IsEqualIgnoringCase
				.equalToIgnoringCase("EF92B778BAFE771E89245B89ECBC08A44A4E166C06659911881F383D4473E94F"));

	}
}
