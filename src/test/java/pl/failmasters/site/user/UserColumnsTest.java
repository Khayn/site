package pl.failmasters.site.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class UserColumnsTest {

	@ParameterizedTest
	@EnumSource(value = UserColumns.class)
	public void testEnum(UserColumns column) {
		// given + when + then
		assertThat(column.getDbColumnName(), allOf(is(notNullValue()), is(not(emptyString()))));
	}
}
