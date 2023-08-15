package no.noroff.accelerate.items;

import java.util.HashMap;
import java.util.Map;

public class Equipment {
    private Map<Slot,Item> equippedItems;

    public Equipment(){
        equippedItems = new HashMap<>();
        for (Slot slot : Slot.values()) {
            equippedItems.put(slot, null);
        }
    }

    public void equipItem(Item item) {
        Slot slot = item.getSlot();
        equippedItems.put(slot, item);
    }

    public Map<Slot, Item> getEquippedItems() {
        return equippedItems;
    }
 }
