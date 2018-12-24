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

public class GildedRoseTest {

    public static final ItemBuilder NORMAL_ITEM = anItem().withName("normal");
    public static final ItemBuilder AGED_BRIE = anItem().withName("Aged Brie");
    public static final ItemBuilder BACKSTAGE = anItem().withName("Backstage passes to a TAFKAL80ETC concert");

    @Test
    public void normal_item_degrades_quality_and_sellIn() {
        List<Item> items = singletonList(NORMAL_ITEM.withSellIn(5)
                                                    .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         sellIn(equalTo(4)),
                         quality(equalTo(4))));
    }

    @Test
    public void normal_item_degrades_twice_as_fast_if_sellIn_is_zero() {
        List<Item> items = singletonList(NORMAL_ITEM.withSellIn(0)
                                                    .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(3))));
    }

    @Test
    public void normal_item_quality_is_never_negative() {
        List<Item> items = singletonList(NORMAL_ITEM.withSellIn(5)
                                                    .withQuality(0).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("normal")),
                         sellIn(equalTo(4)),
                         quality(equalTo(0))));
    }

    @Test
    public void aged_brie_increases_quality_when_sellIn_decreases() {
        List<Item> items = singletonList(AGED_BRIE.withSellIn(5)
                                                  .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Aged Brie")),
                         sellIn(equalTo(4)),
                         quality(equalTo(6))));
    }

    @Test
    public void aged_brie_quality_never_exceeds_50() {
        List<Item> items = singletonList(AGED_BRIE.withSellIn(5)
                                                  .withQuality(50).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Aged Brie")),
                         sellIn(equalTo(4)),
                         quality(equalTo(50))));
    }

    @Test
    public void aged_brie_quality_increase_twice_as_fast_when_sellIn_is_zero() {
        List<Item> items = singletonList(AGED_BRIE.withSellIn(0)
                                                  .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Aged Brie")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(7))));
    }

    @Test
    public void sulfuras_quality_and_sellIn_unchanged() {
        List<Item> items = singletonList(anItem().withName("Sulfuras, Hand of Ragnaros")
                                                 .withSellIn(5)
                                                 .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Sulfuras, Hand of Ragnaros")),
                         sellIn(equalTo(5)),
                         quality(equalTo(5))));
    }

    @Test
    public void backstage_quality_increase_when_sellIn_is_more_than_10() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(15)
                                                  .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(14)),
                         quality(equalTo(6))));
    }

    @Test
    public void backstage_quality_increase_twice_as_fast_when_sellIn_is_LTE_10() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(10)
                                                  .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(9)),
                         quality(equalTo(7))));
    }

    @Test
    public void backstage_quality_increase_trice_as_fast_when_sellIn_is_less_than_5() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(4)
                                                  .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(3)),
                         quality(equalTo(8))));
    }

    @Test
    public void backstage_quality_drops_to_zero_when_expired() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(0)
                                                  .withQuality(5).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(-1)),
                         quality(equalTo(0))));
    }

    @Test
    public void backstage_quality_never_exceeds_50() {
        List<Item> items = singletonList(BACKSTAGE.withSellIn(5)
                                                  .withQuality(50).build());
        new GildedRose(items).updateQuality();
        assertThat(items.get(0),
                   allOf(name(equalTo("Backstage passes to a TAFKAL80ETC concert")),
                         sellIn(equalTo(4)),
                         quality(equalTo(50))));
    }

}
