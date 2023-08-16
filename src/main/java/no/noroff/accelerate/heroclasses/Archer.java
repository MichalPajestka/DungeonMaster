package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.weapons.Weapon;

public class Archer extends Hero{
    public Archer(String name) {
        super(name, HeroClass.ARCHER);
        this.levelAttributes = new HeroAttribute(1, 7, 1);
    }

    @Override
    protected HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 7, 1);
    }

    @Override
    public int calcDamage() {
        int damageAttribute = calcTotalAttributes().getDexterity();
        Weapon equippedWeapon = getEquippedWeapon();

        int weaponDamage;
        if (equippedWeapon != null) {
            weaponDamage = equippedWeapon.getWeaponDamage();
        } else {
            weaponDamage = 1;
        }

        return (weaponDamage * (1 + damageAttribute / 100));
    }

}
