package com.gildedrose;

import com.gildedrose.builders.ItemBuilder;

import org.junit.Test;

import static com.gildedrose.ItemRoleFactory.CONJURED;
import static com.gildedrose.builders.ItemBuilder.anItem;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConjuredTest {

    public static final ItemBuilder CONJURED_ITEM = anItem().withName("Conjured");

    @Test
    public void name_unchanged() {
        Conjured conjured = new Conjured(anItem().withName(CONJURED).build());

        conjured.update();

        assertThat(conjured.item, name(equalTo(CONJURED)));
    }

    @Test
    public void quality_decreases_twice_as_fast_when_sellIn_decreases() {
        Conjured conjured = new Conjured(CONJURED_ITEM.withSellIn(5)
                                                      .withQuality(5).build());
        
        conjured.update();

        assertThat(conjured.item,
                   allOf(sellIn(equalTo(4)),
                         quality(equalTo(3))));
    }

    @Test
    public void quality_decreases_twice_as_fast_when_expired() {
        Conjured conjured = new Conjured(CONJURED_ITEM.withSellIn(0)
                                                      .withQuality(5).build());

        conjured.update();

        assertThat(conjured.item,
                   allOf(sellIn(equalTo(-1)),
                         quality(equalTo(1))));
    }

}
