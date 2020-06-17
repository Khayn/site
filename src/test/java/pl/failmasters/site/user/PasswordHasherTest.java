package pl.failmasters.site.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Testy hashera haseł")
public class PasswordHasherTest {

	@Test
	@DisplayName(value = "Powinien zwrócić odpowiedni hash hasła")
	public void shouldReturnCorrectHash() {
		// given + when
		PasswordHasher hasher = new PasswordHasher("password123");

		// then
		org.hamcrest.MatcherAssert.assertThat(hasher.hash(), IsEqualIgnoringCase
				.equalToIgnoringCase("EF92B778BAFE771E89245B89ECBC08A44A4E166C06659911881F383D4473E94F"));

		org.hamcrest.MatcherAssert.assertThat("2", is(equalTo("2")));

	}
}
