package com.gildedrose.builders;

import com.gildedrose.Item;

public final class ItemBuilder {
    private String name;
    private int    sellIn;
    private int    quality;

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withSellIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    public ItemBuilder withQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public ItemBuilder but() {
        return anItem().withName(name)
                       .withSellIn(sellIn)
                       .withQuality(quality);
    }

    public Item build() {
        return new Item(name, sellIn, quality);
    }
}
