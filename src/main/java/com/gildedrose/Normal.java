package com.gildedrose;

class Normal extends ItemRole {

    Normal(Item item) {
        super(item);
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
