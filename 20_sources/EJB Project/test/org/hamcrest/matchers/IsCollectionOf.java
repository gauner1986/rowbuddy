package org.hamcrest.matchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsCollectionOf<T> extends TypeSafeMatcher<Collection<T>> {
	private final Collection<T> expected;

	public IsCollectionOf(Collection<T> expected) {
		this.expected = expected;
	}

	public boolean matchesSafely(Collection<T> given) {
		List<T> tmp = new ArrayList<T>(expected);
		for (T t : given) {
			if (!tmp.remove(t)) {
				return false;
			}
		}
		return tmp.isEmpty();
	}

	// describeTo here
	public static <T> Matcher<Collection<T>> equalsArray(T... items) {
		return new IsCollectionOf<T>(Arrays.asList(items));
	}

	public static <T> Matcher<Collection<T>> equalsList(List<T> items) {
		return new IsCollectionOf<T>(items);
	}

	@Override
	public void describeTo(Description description) {
	}
}
