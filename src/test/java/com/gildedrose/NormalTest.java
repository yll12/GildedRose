package com.gildedrose;

import com.gildedrose.builders.ItemBuilder;

import org.junit.Test;

import static com.gildedrose.builders.ItemBuilder.anItem;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NormalTest {

    private static final String NORMAL = "normal";
    private static final ItemBuilder NORMAL_ITEM = anItem().withName(NORMAL);

    @Test
    public void name_unchanged() {
        Normal normal = new Normal(anItem().withName(NORMAL).build());

        normal.update();

        assertThat(normal.item, name(equalTo(NORMAL)));
    }

    @Test
    public void quality_and_sellIn_decreases() {
        Normal normal = new Normal(NORMAL_ITEM.withSellIn(5)
                                              .withQuality(5).build());
        normal.update();

        assertThat(normal.item,
                   allOf(sellIn(equalTo(4)),
                         quality(equalTo(4))));
    }

    @Test
    public void quality_decreases_twice_as_fast_when_expired() {
        Normal normal = new Normal(NORMAL_ITEM.withSellIn(0)
                                              .withQuality(5).build());

        normal.update();

        assertThat(normal.item,
                   allOf(sellIn(equalTo(-1)),
                         quality(equalTo(3))));
    }

    @Test
    public void quality_is_never_negative() {
        Normal normal = new Normal(NORMAL_ITEM.withSellIn(0)
                                              .withQuality(0).build());

        normal.update();

        assertThat(normal.item,
                   allOf(sellIn(equalTo(-1)),
                         quality(equalTo(0))));
    }

}