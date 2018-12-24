package com.gildedrose;

import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).name.equals("normal")) {
                normalUpdate(items.get(i));
                return;
            } else if (items.get(i).name.equals("Aged Brie")) {
                agedBrieUpdate(items.get(i));
                return;
            }

            if (!items.get(i).name.equals("Aged Brie")
                    && !items.get(i).name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items.get(i).quality > 0) {
                    if (!items.get(i).name.equals("Sulfuras, Hand of Ragnaros")) {
                        items.get(i).quality = items.get(i).quality - 1;
                    }
                }
            } else {
                if (items.get(i).quality < 50) {
                    items.get(i).quality = items.get(i).quality + 1;

                    if (items.get(i).name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items.get(i).sellIn < 11) {
                            if (items.get(i).quality < 50) {
                                items.get(i).quality = items.get(i).quality + 1;
                            }
                        }

                        if (items.get(i).sellIn < 6) {
                            if (items.get(i).quality < 50) {
                                items.get(i).quality = items.get(i).quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items.get(i).name.equals("Sulfuras, Hand of Ragnaros")) {
                items.get(i).sellIn = items.get(i).sellIn - 1;
            }

            if (items.get(i).sellIn < 0) {
                if (!items.get(i).name.equals("Aged Brie")) {
                    if (!items.get(i).name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items.get(i).quality > 0) {
                            if (!items.get(i).name.equals("Sulfuras, Hand of Ragnaros")) {
                                items.get(i).quality = items.get(i).quality - 1;
                            }
                        }
                    } else {
                        items.get(i).quality = items.get(i).quality - items.get(i).quality;
                    }
                } else {
                    if (items.get(i).quality < 50) {
                        items.get(i).quality = items.get(i).quality + 1;
                    }
                }
            }
        }
    }

    private void agedBrieUpdate(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        if (item.sellIn == 0) {
            item.quality += 1;
        }

        item.sellIn -= 1;
    }

    private void normalUpdate(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }

        if (item.sellIn == 0) {
            item.quality -= 1;
        }

        item.sellIn -= 1;
    }
}