package com.gildedrose.matchers;

import com.gildedrose.Item;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ItemSellInMatcher extends TypeSafeMatcher<Item> {
    private Matcher<Integer> expectedSellIn;

    private ItemSellInMatcher(Matcher<Integer> expectedSellIn) {
        this.expectedSellIn = expectedSellIn;
    }

    public static Matcher<Item> sellIn(Matcher<Integer> sellIn) {
        return new ItemSellInMatcher(sellIn);
    }

    @Override
    protected boolean matchesSafely(Item item) {
        return expectedSellIn.matches(item.sellIn);
    }

    @Override
    public void describeTo(Description description) {
        expectedSellIn.describeTo(description.appendText("SellIn "));
    }

    @Override
    protected void describeMismatchSafely(Item item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item.sellIn);
    }
}
