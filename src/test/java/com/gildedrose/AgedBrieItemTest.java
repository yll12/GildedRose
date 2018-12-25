package com.gildedrose;

import org.junit.Test;

import java.util.List;

import static com.gildedrose.ItemBuilder.anItem;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AgedBrieItemTest {

    public static final ItemBuilder AGED_BRIE = anItem().withName("Aged Brie");

    @Test
    public void quality_increases_when_sellIn_decreases() {
        List<Item> items = singletonList(AGED_BRIE.withSellIn(5)
                                                  .withQuality(5).build());
        new AgedBrieItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Aged Brie")),
                         sellIn(equalTo(4)),
                         quality(equalTo(6))));
    }

    @Test
    public void quality_never_exceeds_50() {
        List<Item> items = singletonList(AGED_BRIE.withSellIn(0)
                                                  .withQuality(50).build());
        new AgedBrieItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Aged Brie")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(50))));
    }

    @Test
    public void quality_increase_twice_as_fast_when_expired() {
        List<Item> items = singletonList(AGED_BRIE.withSellIn(0)
                                                  .withQuality(5).build());
        new AgedBrieItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Aged Brie")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(7))));
    }


}