package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.InvalidWeaponException;
import no.noroff.accelerate.items.Equipment;
import no.noroff.accelerate.items.armor.Armor;
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

    }

    public void equipWeapon(Weapon weapon) throws InvalidWeaponException {
        if(!validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new InvalidWeaponException("Invalid weapon type for this hero");
        }
        equipment.equipItem(weapon);
    }

    public void equipArmor(Armor armor) throws InvalidArmorException {
        if(!validArmorTypes.contains(armor.getArmorType())) {
            throw new InvalidArmorException("Invalid armor type for this hero");
        }
        equipment.equipItem(armor);
    }

    public int calcDamage() {
        return 0;
    }

    public int totalAttributes() {
        return 0;
    }

    public void displayHero() {

    }

}
