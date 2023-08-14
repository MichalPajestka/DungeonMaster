package no.noroff.accelerate.items;

import no.noroff.accelerate.exceptions.InvalidEquipmentException;

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

    public void equipItem(Item item) throws InvalidEquipmentException {
        Slot slot = item.getSlot();
        if (equippedItems.containsKey(slot)) {
            equippedItems.put(slot, item);
        } else {
            throw new InvalidEquipmentException("Invalid slot for equipping item");
        }
    }

    public Map<Slot, Item> getEquippedItems() {
        return equippedItems;
    }
 }
