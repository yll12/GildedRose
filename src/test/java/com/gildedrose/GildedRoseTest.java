package com.gildedrose;

import org.junit.Test;

import java.util.List;

import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GildedRoseTest {

    @Test
    public void normal_item_degrades_quality_and_sellIn() {
        List<Item> items = singletonList(getNormal(5, 5));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         quality(equalTo(4)),
                         sellIn(equalTo(4))));
    }

    private Item getNormal(int i, int i2) {
        return new Item("normal", i, i2);
    }

    @Test
    public void normal_item_degrades_twice_as_fast_if_sellIn_is_zero() {
        List<Item> items = singletonList(getNormal(0, 5));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         quality(equalTo(3)),
                         sellIn(equalTo(-1))));
    }

    @Test
    public void normal_item_quality_is_never_negative() {
        List<Item> items = singletonList(getNormal(5, 0));
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         quality(equalTo(0)),
                         sellIn(equalTo(4))));
    }

}
