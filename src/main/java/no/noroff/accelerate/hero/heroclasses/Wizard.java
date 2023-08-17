package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.exceptions.weapons.WizardInvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

public class Wizard extends Hero {
    public Wizard(String name) {
        super(name, HeroClass.WIZARD);
        this.levelAttributes = new HeroAttribute(1, 1, 8);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 1, 5);
    }

    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(1, 1, 5));
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

    @Override
    public void equipWeapon(Weapon weapon) throws InvalidWeaponException {
        if (level < weapon.getRequiredLevel()) {
            throw new InvalidWeaponException("Hero level is too low to equip this weapon");
        }

        if (!WeaponType.WAND.equals(weapon.getWeaponType())
                && !WeaponType.STAFF.equals(weapon.getWeaponType())) {
            throw new WizardInvalidWeaponException("Wizards can only use wands or staffs");
        }

        equipment.equipItem(weapon);
    }


}
