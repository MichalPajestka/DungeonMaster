package no.noroff.accelerate.items.armor;

import no.noroff.accelerate.items.Item;
import no.noroff.accelerate.items.Slot;

public class Armor extends Item {
    private ArmorType armorType;
    private ArmorAttribute armorAttribute;


    public Armor(String name, int requiredLevel, ArmorType armorType, ArmorAttribute armorAttribute) {
        super(name, requiredLevel, getArmorSlot(armorType));
        this.armorType = armorType;
        this.armorAttribute = armorAttribute;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public ArmorAttribute getArmorAttribute() {
        return armorAttribute;
    }

    private static Slot getArmorSlot(ArmorType armorType) {
        switch (armorType) {
            case CLOTH:
            case LEATHER:
            case MAIL:
            case PLATE:
                return Slot.BODY;
            default:
                throw new IllegalArgumentException("Invalid armor type");
        }
    }
}
