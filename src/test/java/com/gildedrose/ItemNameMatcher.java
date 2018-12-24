package com.gildedrose;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

class ItemNameMatcher extends TypeSafeMatcher<Item> {

    private Matcher<String> expectedName;

    public ItemNameMatcher(Matcher<String> expectedName) {
        this.expectedName = expectedName;
    }

    static Matcher<Item> name(Matcher<String> name) {
        return new ItemNameMatcher(name);
    }

    @Override
    protected boolean matchesSafely(Item item) {
        return expectedName.matches(item.name);
    }

    @Override
    public void describeTo(Description description) {
        expectedName.describeTo(description.appendText("Name "));
    }

    @Override
    protected void describeMismatchSafely(Item item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item.name);
    }
}
