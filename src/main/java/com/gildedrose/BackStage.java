package com.gildedrose;

class BackStage extends ItemRole {
    BackStage(Item item) {
        super(item);
    }

    @Override
    public void update() {
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
}
