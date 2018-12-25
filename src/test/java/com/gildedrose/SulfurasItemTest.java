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

public class SulfurasItemTest {

    @Test
    public void quality_and_sellIn_unchanged() {
        List<Item> items = singletonList(anItem().withName("Sulfuras, Hand of Ragnaros")
                                                 .withSellIn(5)
                                                 .withQuality(5).build());
        new SulfurasItem(items.get(0)).update();
        assertThat(items.get(0),
                   allOf(name(equalTo("Sulfuras, Hand of Ragnaros")),
                         sellIn(equalTo(5)),
                         quality(equalTo(5))));
    }

}