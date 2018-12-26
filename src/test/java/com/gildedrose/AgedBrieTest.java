package com.gildedrose;

import com.gildedrose.builders.ItemBuilder;

import org.junit.Test;

import static com.gildedrose.builders.ItemBuilder.anItem;
import static com.gildedrose.ItemRoleFactory.AGED_BRIE;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AgedBrieTest {

    private static final ItemBuilder AGED_BRIE_ITEM = anItem().withName(AGED_BRIE);

    @Test
    public void name_unchanged() {
        AgedBrie agedBrie = new AgedBrie(anItem().withName(AGED_BRIE).build());

        agedBrie.update();

        assertThat(agedBrie.item, name(equalTo(AGED_BRIE)));
    }

    @Test
    public void quality_increases_when_sellIn_decreases() {
        AgedBrie agedBrie = new AgedBrie(AGED_BRIE_ITEM.withSellIn(5)
                                                       .withQuality(5).build());

        agedBrie.update();

        assertThat(agedBrie.item,
                   allOf(sellIn(equalTo(4)),
                         quality(equalTo(6))));
    }

    @Test
    public void quality_never_exceeds_50() {
        AgedBrie agedBrie = new AgedBrie(AGED_BRIE_ITEM.withSellIn(0)
                                                       .withQuality(50).build());

        agedBrie.update();

        assertThat(agedBrie.item,
                   allOf(sellIn(equalTo(-1)),
                         quality(equalTo(50))));
    }

    @Test
    public void quality_increase_twice_as_fast_when_expired() {
        AgedBrie agedBrie = new AgedBrie(AGED_BRIE_ITEM.withSellIn(0)
                                                       .withQuality(5).build());

        agedBrie.update();

        assertThat(agedBrie.item,
                   allOf(sellIn(equalTo(-1)),
                         quality(equalTo(7))));
    }

}