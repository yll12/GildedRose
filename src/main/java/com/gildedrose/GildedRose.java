package com.gildedrose;

import java.util.List;

class GildedRose {
    List<Item> items;

    GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            getItemRole(item).update();
        }
    }

    public ItemRole getItemRole(Item item) {
        switch (item.name) {
            case "normal":
                return new Normal(item);
            case "Aged Brie":
                return new AgedBrie(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackStage(item);
            default:
                return new ItemRole(item);
        }
    }

}