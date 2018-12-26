package com.gildedrose;

import com.gildedrose.builders.ItemBuilder;

import org.junit.Test;

import static com.gildedrose.builders.ItemBuilder.anItem;
import static com.gildedrose.ItemRoleFactory.*;
import static com.gildedrose.matchers.ItemNameMatcher.name;
import static com.gildedrose.matchers.ItemQualityMatcher.quality;
import static com.gildedrose.matchers.ItemSellInMatcher.sellIn;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BackStageTest {

    private static final ItemBuilder BACKSTAGE_ITEM = anItem().withName(BACKSTAGE_PASSES);

    @Test
    public void name_unchanged() {
        BackStage backStage = new BackStage(anItem().withName(BACKSTAGE_PASSES).build());

        backStage.update();

        assertThat(backStage.item, name(equalTo(BACKSTAGE_PASSES)));
    }

    @Test
    public void backstage_quality_increase_when_sellIn_is_more_than_10() {
        BackStage backStage = new BackStage(BACKSTAGE_ITEM.withSellIn(15)
                                                          .withQuality(5).build());

        backStage.update();

        assertThat(backStage.item,
                   allOf(sellIn(equalTo(14)),
                         quality(equalTo(6))));
    }

    @Test
    public void backstage_quality_increase_twice_as_fast_when_sellIn_is_LTE_10() {
        BackStage backStage = new BackStage(BACKSTAGE_ITEM.withSellIn(10)
                                                          .withQuality(5).build());
        backStage.update();
        assertThat(backStage.item,
                   allOf(sellIn(equalTo(9)),
                         quality(equalTo(7))));
    }

    @Test
    public void backstage_quality_increase_trice_as_fast_when_sellIn_is_less_than_5() {
        BackStage backStage = new BackStage(BACKSTAGE_ITEM.withSellIn(4)
                                                          .withQuality(5).build());
        backStage.update();
        assertThat(backStage.item,
                   allOf(sellIn(equalTo(3)),
                         quality(equalTo(8))));
    }

    @Test
    public void backstage_quality_drops_to_zero_when_expired() {
        BackStage backStage = new BackStage(BACKSTAGE_ITEM.withSellIn(0)
                                                          .withQuality(5).build());
        backStage.update();
        assertThat(backStage.item,
                   allOf(sellIn(equalTo(-1)),
                         quality(equalTo(0))));
    }

    @Test
    public void backstage_quality_never_exceeds_50() {
        BackStage backStage = new BackStage(BACKSTAGE_ITEM.withSellIn(5)
                                                          .withQuality(50).build());
        backStage.update();
        assertThat(backStage.item,
                   allOf(sellIn(equalTo(4)),
                         quality(equalTo(50))));
    }

}