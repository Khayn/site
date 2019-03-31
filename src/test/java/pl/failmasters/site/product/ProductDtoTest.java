package pl.failmasters.site.product;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ProductDtoTest {

	@Test
	public void testDto() {
		// given + when + then
		assertNotNull(new ProductDto());
		assertThat(ProductDto.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidGettersAndSetters(),
				hasValidBeanHashCode(), hasValidBeanToString()));
	}
}
