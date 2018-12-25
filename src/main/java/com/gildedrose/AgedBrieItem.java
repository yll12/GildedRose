package com.gildedrose;

class AgedBrieItem extends ItemRole {

    public AgedBrieItem(Item item) {
        this.item = item;
    }

    public void update() {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn == 0) {
                item.quality += 1;
            }
        }

        item.sellIn -= 1;
    }
}
