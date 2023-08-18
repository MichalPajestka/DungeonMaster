package no.noroff.accelerate.items;

import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorAttribute;

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


    // Inside Equipment class
    public HeroAttribute calculateTotalArmorAttributes() {
        HeroAttribute totalArmorAttributes = new HeroAttribute(0, 0, 0);

        for (Item item : equippedItems.values()) {
            if (item instanceof Armor) {
                Armor armor = (Armor) item;
                ArmorAttribute armorAttribute = armor.getArmorAttribute();
                totalArmorAttributes.addArmorAttributes(armorAttribute);
            }
        }

        return totalArmorAttributes;
    }



    public Map<Slot, Item> getEquippedItems() {
        return equippedItems;
    }
 }
