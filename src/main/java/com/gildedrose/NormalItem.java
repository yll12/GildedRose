package com.gildedrose;

class NormalItem {
    Item item;

    public NormalItem(Item item) {
        this.item = item;
    }

    public void update() {
        if (item.quality > 0) {
            item.quality -= 1;

            if (item.sellIn == 0) {
                item.quality -= 1;
            }
        }

        item.sellIn -= 1;
    }
}
