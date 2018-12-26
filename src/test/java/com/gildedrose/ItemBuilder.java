package com.gildedrose;

public final class ItemBuilder {
    private String name;
    private int    sellIn;
    private int    quality;

    private ItemBuilder() {
    }

    static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    ItemBuilder withSellIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    ItemBuilder withQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public ItemBuilder but() {
        return anItem().withName(name)
                       .withSellIn(sellIn)
                       .withQuality(quality);
    }

    Item build() {
        return new Item(name, sellIn, quality);
    }
}
