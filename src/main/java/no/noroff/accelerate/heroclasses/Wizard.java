package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.items.weapons.Weapon;

public class Wizard extends Hero{
    public Wizard(String name) {
        super(name);
        this.levelAttributes = new HeroAttribute(1, 1, 8);
    }

    @Override
    public int calcDamage() {
        int damageAttribute = calcTotalAttributes().getIntelligence();
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
