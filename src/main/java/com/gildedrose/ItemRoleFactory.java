package com.gildedrose;

class ItemRoleFactory {

    static final String NORMAL           = "normal";
    static final String AGED_BRIE        = "Aged Brie";
    static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    ItemRole getItemRole(Item item) {
        switch (item.name) {
            case NORMAL:
                return new Normal(item);
            case AGED_BRIE:
                return new AgedBrie(item);
            case BACKSTAGE_PASSES:
                return new BackStage(item);
            default:
                return new ItemRole(item);
        }
    }

}
