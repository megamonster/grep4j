package org.grep4j.core.matchers;

import org.grep4j.core.result.GrepResults;
import static org.grep4j.core.Grep4j.constantExpression;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class GrepResultDoesNotContain extends TypeSafeMatcher<GrepResults> {

	private final String expression;

	private GrepResultDoesNotContain(String expression) {
		this.expression = expression;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("contains expression : " + expression);
	}

	@Override
	public boolean matchesSafely(GrepResults results) {
		return results.filterBy(constantExpression(expression)).totalLines() == 0;
	}

	@Factory
	public static <T> Matcher<GrepResults> doesNotContainExpression(String expression) {
		return new GrepResultDoesNotContain(expression);
	}
}