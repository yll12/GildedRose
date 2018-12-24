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
                    new NormalItem(items.get(i)).update();
                    return;
                case "Aged Brie":
                    new AgedBrieItem(items.get(i)).update();
                    return;
                case "Sulfuras, Hand of Ragnaros":
                    new SulfurasItem().update();
                    return;
                case "Backstage passes to a TAFKAL80ETC concert":
                    new BackStageItem(items.get(i)).update();
                    return;
            }
        }
    }

}