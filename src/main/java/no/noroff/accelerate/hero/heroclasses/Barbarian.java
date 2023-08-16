package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.weapons.Weapon;

public class Barbarian extends Hero {
    public Barbarian(String name) {
        super(name, HeroClass.BARBARIAN);
        this.levelAttributes = new HeroAttribute(5,2,1);
    }

    @Override
    protected HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(3, 2, 1);
    }

    @Override
    public int calcDamage() {
        int damageAttribute = calcTotalAttributes().getStrength();
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
