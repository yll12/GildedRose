package com.gildedrose;

class ItemRoleFactory {

    static final String AGED_BRIE = "Aged Brie";
    static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    static final String CONJURED = "Conjured";
    static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    ItemRole getItemRole(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return new AgedBrie(item);
            case SULFURAS_HAND_OF_RAGNAROS:
                return new ItemRole(item);
            case BACKSTAGE_PASSES:
                return new BackStage(item);
            case CONJURED:
                return new Conjured(item);
            default:
                return new Normal(item);
        }
    }

}
