package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.weapons.Weapon;

public class Wizard extends Hero {
    public Wizard(String name) {
        super(name, HeroClass.WIZARD);
        this.levelAttributes = new HeroAttribute(1, 1, 8);
    }

    @Override
    protected HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 1, 5);
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
