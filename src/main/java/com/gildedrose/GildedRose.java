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
                    agedBrieUpdate(items.get(i));
                    return;
                case "Sulfuras, Hand of Ragnaros":
                    sulfurasUpdate(items.get(i));
                    return;
                case "Backstage passes to a TAFKAL80ETC concert":
                    backstageUpdate(items.get(i));
                    return;
            }
        }
    }

    private void backstageUpdate(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn <= 10) {
                item.quality += 1;
            }
            if (item.sellIn <= 5) {
                item.quality += 1;
            }
            if (item.sellIn == 0) {
                item.quality = 0;
            }
        }
        item.sellIn -= 1;
    }

    private void sulfurasUpdate(Item item) {

    }


    private void agedBrieUpdate(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn == 0) {
                item.quality += 1;
            }
        }

        item.sellIn -= 1;
    }

    class NormalItem {
        Item item;

        public NormalItem(Item item) {
            this.item = item;
        }

        private void update() {
            if (item.quality > 0) {
                item.quality -= 1;

                if (item.sellIn == 0) {
                    item.quality -= 1;
                }
            }

            item.sellIn -= 1;
        }
    }
}