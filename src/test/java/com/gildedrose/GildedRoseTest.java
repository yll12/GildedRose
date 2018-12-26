package com.gildedrose;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.gildedrose.builders.ItemBuilder.anItem;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class GildedRoseTest {

    private ItemRoleFactory mockItemRoleFactory = Mockito.mock(ItemRoleFactory.class);
    private ItemRole        mockItemRole1       = Mockito.mock(ItemRole.class);
    private ItemRole        mockItemRole2       = Mockito.mock(ItemRole.class);

    @Test
    public void callUpdateOnEachItem() {

        List<Item> items = asList(anItem().build(),
                                  anItem().build());

        when(mockItemRoleFactory.getItemRole(items.get(0))).thenReturn(mockItemRole1);
        when(mockItemRoleFactory.getItemRole(items.get(1))).thenReturn(mockItemRole2);

        new GildedRose(items, mockItemRoleFactory).updateQuality();

        verify(mockItemRole1, times(1)).update();
        verify(mockItemRole2, times(1)).update();
    }


}
