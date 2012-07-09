package org.grep4j.core;

import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.fixtures.ProfileFixtures.localProfile;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.grep4j.core.options.Option.countMatches;
import static org.grep4j.core.options.Option.extraLinesAfter;
import static org.grep4j.core.options.Option.extraLinesBefore;
import static org.grep4j.core.options.Option.invertMatch;
import static org.grep4j.core.options.Option.withFileName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.grep4j.core.result.GrepResult;
import org.grep4j.core.result.GrepResults;
import org.testng.annotations.Test;

@Test
public class WhenGreppingWithOptions {

	public void linesAfter() {
		GrepResults results = grep("ERROR 1", on(localProfile()), extraLinesAfter(20));
		assertThat(results.filterBy("customer Marco(id=12345) has been updated successfully").totalOccurrences(), is(1));
	}

	public void linesBefore() {
		GrepResults results = grep("ERROR 2", on(localProfile()), extraLinesBefore(20));
		assertThat(results.filterBy("ERROR 1").totalOccurrences(), is(1));
	}

	public void invert() {
		GrepResults results = grep("ERROR 2", on(localProfile()), invertMatch());
		assertThat(results.filterBy("ERROR 2").totalOccurrences(), is(0));
	}

	public void countMatchesOption() {
		GrepResults results = grep("ERROR 2", on(localProfile()), countMatches());
		for (GrepResult result : results) {
			assertThat(result.toString(), is("1\n"));
		}
	}

	public void withFileNameOption() {
		GrepResults results = grep("ERROR 2", on(localProfile()), withFileName());
		assertThat(results.filterBy("local.txt").totalOccurrences(), is(1));
	}

}
