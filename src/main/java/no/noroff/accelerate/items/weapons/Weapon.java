package no.noroff.accelerate.items.weapons;

import no.noroff.accelerate.items.Item;
import no.noroff.accelerate.items.Slot;

public class Weapon extends Item {
    private WeaponType weaponType;
    protected int weaponDamage;


    public Weapon(String name, int requiredLevel, WeaponType weaponType, int weaponDamage) {
        super(name, requiredLevel, Slot.WEAPON); //Automatically set the slot to weapon
        this.weaponType = weaponType;
        this.weaponDamage = weaponDamage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }
}
