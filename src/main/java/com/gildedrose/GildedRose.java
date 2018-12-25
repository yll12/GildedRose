package com.gildedrose;

import java.util.List;

class GildedRose {
    List<Item> items;

    GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {

            switch (items.get(i).name) {
                case "normal":
                    final ItemRole normalItem = new NormalItem(items.get(i));
                    normalItem.update();
                    return;
                case "Aged Brie":
                    final ItemRole agedBrieItem = new AgedBrieItem(items.get(i));
                    agedBrieItem.update();
                    return;
                case "Sulfuras, Hand of Ragnaros":
                    final ItemRole sulfurasItem = new SulfurasItem(items.get(i));
                    sulfurasItem.update();
                    return;
                case "Backstage passes to a TAFKAL80ETC concert":
                    final ItemRole backStageItem = new BackStageItem(items.get(i));
                    backStageItem.update();
                    return;
            }
        }
    }

}