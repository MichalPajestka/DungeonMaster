package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.weapons.BarbarianInvalidWeaponException;
import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

public class Barbarian extends Hero {
    public Barbarian(String name) {
        super(name, HeroClass.BARBARIAN);
        this.levelAttributes = new HeroAttribute(5,2,1);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(3, 2, 1);
    }

    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(3, 2, 1));
    }

    @Override
    public double calcDamage() {
        Weapon equippedWeapon = getEquippedWeapon();

        if (equippedWeapon != null) {
            int weaponDamage = equippedWeapon.getWeaponDamage();
            double damageAttribute = calcTotalAttributes().getStrength() / 100.0;
            return weaponDamage * (1 + damageAttribute);
        } else {
            return 1.0;
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) throws InvalidWeaponException {
        if (level < weapon.getRequiredLevel()) {
            throw new InvalidWeaponException("Hero level is too low to equip this weapon");
        }

        if (!WeaponType.HATCHET.equals(weapon.getWeaponType())
                && !WeaponType.MACE.equals(weapon.getWeaponType())
                && !WeaponType.SWORD.equals(weapon.getWeaponType())) {
            throw new BarbarianInvalidWeaponException("Barbarians can only use hatchets, maces or swords");
        }

        equipment.equipItem(weapon);
    }

}
