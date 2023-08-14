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
 }
