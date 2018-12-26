package com.gildedrose;

public class Conjured extends ItemRole {

    public Conjured(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.quality > 0) {
            item.quality -= 2;

            if (item.sellIn == 0) {
                item.quality -= 2;
            }
        }

        item.sellIn -= 1;
    }
}
