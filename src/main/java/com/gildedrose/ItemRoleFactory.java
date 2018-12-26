package com.gildedrose;

public class ItemRoleFactory {

    public ItemRole getItemRole(Item item) {
        switch (item.name) {
            case "normal":
                return new Normal(item);
            case "Aged Brie":
                return new AgedBrie(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackStage(item);
            default:
                return new ItemRole(item);
        }
    }

}
