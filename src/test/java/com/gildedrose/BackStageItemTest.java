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

public class BackStageItemTest {

    public static final ItemBuilder BACKSTAGE = anItem().withName("Backstage passes to a TAFKAL80ETC concert");

    @Test
    public void backstage_quality_increase_when_sellIn_is_more_than_10() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(15)
                                                  .withQuality(5).build());
        new BackStageItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(14)),
                         quality(equalTo(6))));
    }

    @Test
    public void backstage_quality_increase_twice_as_fast_when_sellIn_is_LTE_10() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(10)
                                                  .withQuality(5).build());
        new BackStageItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(9)),
                         quality(equalTo(7))));
    }

    @Test
    public void backstage_quality_increase_trice_as_fast_when_sellIn_is_less_than_5() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(4)
                                                  .withQuality(5).build());
        new BackStageItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(3)),
                         quality(equalTo(8))));
    }

    @Test
    public void backstage_quality_drops_to_zero_when_expired() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(0)
                                                  .withQuality(5).build());
        new BackStageItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(0))));
    }

    @Test
    public void backstage_quality_never_exceeds_50() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(5)
                                                  .withQuality(50).build());
        new BackStageItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(4)),
                         quality(equalTo(50))));
    }

}