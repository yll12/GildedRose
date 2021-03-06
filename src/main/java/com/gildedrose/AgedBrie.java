package com.gildedrose;

class AgedBrie extends ItemRole {

    AgedBrie(Item item) {
        super(item);
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
