package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.exceptions.weapons.SwashbucklerInvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

public class Swashbuckler extends Hero {

    public Swashbuckler(String name) {
        super(name, HeroClass.SWASHBUCKLER);
        this.levelAttributes = new HeroAttribute(2,6,1);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 4, 1);
    }

    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(1, 4, 1));
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

    @Override
    public void equipWeapon(Weapon weapon) throws InvalidWeaponException {
        if (level < weapon.getRequiredLevel()) {
            throw new InvalidWeaponException("Hero level is too low to equip this weapon");
        }

        if (!WeaponType.SWORD.equals(weapon.getWeaponType())
                && !WeaponType.DAGGER.equals(weapon.getWeaponType())) {
            throw new SwashbucklerInvalidWeaponException("Swashbucklers can only use swords or daggers");
        }

        equipment.equipItem(weapon);
    }

}
