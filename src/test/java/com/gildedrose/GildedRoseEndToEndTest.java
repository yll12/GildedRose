package com.gildedrose;

import org.junit.Test;

import java.util.List;

import static com.gildedrose.ItemRoleFactory.*;
import static com.gildedrose.builders.ItemBuilder.anItem;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class GildedRoseEndToEndTest {

    private static final String NORMAL = "normal";

    @Test
    public void allItemsUpdatesCorrectly() {
        List<Item> items = asList(anItem().withName(NORMAL)
                                          .withSellIn(5)
                                          .withQuality(5).build(),

                                  anItem().withName(SULFURAS_HAND_OF_RAGNAROS)
                                          .withSellIn(5)
                                          .withQuality(5).build(),

                                  anItem().withName(BACKSTAGE_PASSES)
                                          .withSellIn(5)
                                          .withQuality(5).build(),

                                  anItem().withName(CONJURED)
                                          .withSellIn(5)
                                          .withQuality(5).build());

        GildedRose gildedRose = new GildedRose(items, new ItemRoleFactory());

        gildedRose.updateQuality();

        assertThat(gildedRose.items,
                   contains(
                       allOf(name(equalTo(NORMAL)),
                             sellIn(equalTo(4)),
                             quality(equalTo(4))),

                       allOf(name(equalTo(SULFURAS_HAND_OF_RAGNAROS)),
                             sellIn(equalTo(5)),
                             quality(equalTo(5))),

                       allOf(name(equalTo(BACKSTAGE_PASSES)),
                             sellIn(equalTo(4)),
                             quality(equalTo(8))),

                       allOf(name(equalTo(CONJURED)),
                             sellIn(equalTo(4)),
                             quality(equalTo(3)))));

    }

}
