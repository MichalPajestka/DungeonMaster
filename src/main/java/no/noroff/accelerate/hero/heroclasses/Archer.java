package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.weapons.ArcherInvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

import java.util.Arrays;

public class Archer extends Hero {
    public Archer(String name) {
        super(name, HeroClass.ARCHER);
        this.levelAttributes = new HeroAttribute(1, 7, 1);
        this.validWeaponTypes = Arrays.asList(WeaponType.BOW);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 5, 1);
    }

    @Override
    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(1, 5, 1));
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
    public void equipWeapon(Weapon weapon) throws ArcherInvalidWeaponException {
        if (level < weapon.getRequiredLevel()) {
            throw new ArcherInvalidWeaponException("Hero level is too low to equip this weapon");
        }

        if (!WeaponType.BOW.equals(weapon.getWeaponType())) {
            throw new ArcherInvalidWeaponException("Archers can only use bows");
        }

        equipment.equipItem(weapon);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public HeroAttribute getLevelAttributes() {
        return levelAttributes;
    }

}
