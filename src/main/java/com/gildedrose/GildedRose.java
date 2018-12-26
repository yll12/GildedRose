package com.gildedrose;

import java.util.List;

class GildedRose {
    private final ItemRoleFactory itemRoleFactory;
    private       List<Item>      items;

    GildedRose(List<Item> items, ItemRoleFactory itemRoleFactory) {
        this.items = items;
        this.itemRoleFactory = itemRoleFactory;
    }

    void updateQuality() {
        for (Item item : items) {
            itemRoleFactory.getItemRole(item).update();
        }
    }

}