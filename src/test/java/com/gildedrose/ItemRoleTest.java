package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.builders.ItemBuilder.anItem;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemRoleTest {


    @Test
    public void quality_and_sellIn_never_changes() {
        ItemRole itemRole = new ItemRole(anItem().withName("Sulfuras, Hand of Ragnaros")
                                                 .withSellIn(5)
                                                 .withQuality(5).build());

        itemRole.update();

        assertThat(itemRole.item,
                   allOf(name(equalTo("Sulfuras, Hand of Ragnaros")),
                         sellIn(equalTo(5)),
                         quality(equalTo(5))));
    }

}