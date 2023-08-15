package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.items.weapons.Weapon;

public class Swashbuckler extends Hero {

    public Swashbuckler(String name) {
        super(name);
        this.levelAttributes = new HeroAttribute(2,6,1);
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
