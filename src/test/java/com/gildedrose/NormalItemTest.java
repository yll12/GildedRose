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

public class NormalItemTest {

    public static final ItemBuilder NORMAL_ITEM = anItem().withName("normal");

    @Test
    public void quality_and_sellIn_degrades() {
        List<Item> items = singletonList(NORMAL_ITEM.withSellIn(5)
                                                    .withQuality(5).build());
        new NormalItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         sellIn(equalTo(4)),
                         quality(equalTo(4))));
    }

    @Test
    public void quality_degrades_twice_as_fast_when_expired() {
        List<Item> items = singletonList(NORMAL_ITEM.withSellIn(0)
                                                    .withQuality(5).build());
        new NormalItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(3))));
    }

    @Test
    public void quality_is_never_negative() {
        List<Item> items = singletonList(NORMAL_ITEM.withSellIn(0)
                                                    .withQuality(0).build());
        new NormalItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(0))));
    }


}