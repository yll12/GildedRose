package com.gildedrose;

import java.util.List;

class GildedRose {
    List<Item> items;

    GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            new ItemRoleFactory().getItemRole(item).update();
        }
    }

}