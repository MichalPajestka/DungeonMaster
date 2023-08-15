package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.InvalidWeaponException;
import no.noroff.accelerate.items.Equipment;
import no.noroff.accelerate.items.Item;
import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorAttribute;
import no.noroff.accelerate.items.armor.ArmorType;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

import java.util.List;

public abstract class Hero {
    protected String name;

    protected int level;
    protected HeroAttribute levelAttributes;

    protected List<WeaponType> validWeaponTypes;
    protected List<ArmorType> validArmorTypes;
    protected Equipment equipment;

    public Hero(String name) {
        this.name = name;
        this.level = 1; //Heroes starts at level 1
        equipment = new Equipment();
    }

    public void levelUp() {
        level++;
    }

    public void equipWeapon(Weapon weapon) throws InvalidWeaponException {
        if(!validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new InvalidWeaponException("Invalid weapon type for this hero");
        }
        equipment.equipItem(weapon);
    }

    protected Weapon getEquippedWeapon() {
        return (Weapon) equipment.getEquippedItems().get(Slot.WEAPON);
    }


    public void equipArmor(Armor armor) throws InvalidArmorException {
        if(!validArmorTypes.contains(armor.getArmorType())) {
            throw new InvalidArmorException("Invalid armor type for this hero");
        }
        equipment.equipItem(armor);
    }

    public HeroAttribute calcTotalAttributes() {
        HeroAttribute totalAttributes = new HeroAttribute(
                levelAttributes.getStrength(),
                levelAttributes.getDexterity(),
                levelAttributes.getIntelligence()
        );

        for (Item item : equipment.getEquippedItems().values()) {
            if (item instanceof Armor) {
                ArmorAttribute armorAttribute = ((Armor) item).getArmorAttribute();
                totalAttributes.addAttributes(new HeroAttribute(
                        armorAttribute.getStrengthBonus(),
                        armorAttribute.getDexterityBonus(),
                        armorAttribute.getIntelligenceBonus()
                ));
            }
        }
        // Return the HeroAttribute object, not the sum of attributes
        return totalAttributes;
    }

    public abstract int calcDamage();

    public void displayHero() {

    }

}
