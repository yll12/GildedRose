package com.gildedrose.matchers;

import com.gildedrose.Item;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ItemQualityMatcher extends TypeSafeMatcher<Item> {
    private Matcher<Integer> expectedQuality;

    private ItemQualityMatcher(Matcher<Integer> expectedQuality) {
        this.expectedQuality = expectedQuality;
    }

    public static Matcher<Item> quality(Matcher<Integer> quality) {
        return new ItemQualityMatcher(quality);
    }

    @Override
    protected boolean matchesSafely(Item item) {
        return expectedQuality.matches(item.quality);
    }

    @Override
    public void describeTo(Description description) {
        expectedQuality.describeTo(description.appendText("Quality "));
    }

    @Override
    protected void describeMismatchSafely(Item item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item.quality);
    }
}
