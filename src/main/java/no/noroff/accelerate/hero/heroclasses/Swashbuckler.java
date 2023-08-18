package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.armor.SwashbucklerInvalidArmorException;
import no.noroff.accelerate.exceptions.weapons.SwashbucklerInvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorType;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

import java.util.Arrays;

public class Swashbuckler extends Hero {
    public Swashbuckler(String name) {
        super(name, HeroClass.SWASHBUCKLER);
        this.levelAttributes = new HeroAttribute(2, 6, 1);
        this.validWeaponTypes = Arrays.asList(WeaponType.DAGGER, WeaponType.SWORD);
        this.validArmorTypes = Arrays.asList(ArmorType.LEATHER, ArmorType.MAIL);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 4, 1);
    }

    @Override
    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(1, 4, 1));
    }

    @Override
    public double calcDamage() {
        //Calculate swashbuckler damage
        Weapon equippedWeapon = getEquippedWeapon();

        if (equippedWeapon != null) {
            int weaponDamage = equippedWeapon.getWeaponDamage();
            double damageAttribute = calcTotalAttributes().getDexterity() / 100.0;
            double damage = weaponDamage * (1 + damageAttribute);
            return Math.round(damage * 100.0) / 100.0;
        } else {
            return 1.0;
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) throws SwashbucklerInvalidWeaponException {
        //Check if swashbuckler equipped weapons are valid
        if (level < weapon.getRequiredLevel()) {
            throw new SwashbucklerInvalidWeaponException("Hero level is too low to equip this weapon");
        }

        WeaponType weaponType = weapon.getWeaponType();
        if (!(WeaponType.DAGGER.equals(weaponType) || WeaponType.SWORD.equals(weaponType))) {
            throw new SwashbucklerInvalidWeaponException("Swashbucklers can only use daggers or swords");
        }

        equipment.equipItem(weapon);
    }

    public void equipArmor(Armor armor) throws InvalidArmorException {
        //Check if equipped swashbuckler armor is valid
        if (level < armor.getRequiredLevel()) {
            throw new InvalidArmorException("Hero level is too low to equip this armor");
        }

        ArmorType armorType = armor.getArmorType();
        Slot armorSlot = armor.getSlot();

        if (!(ArmorType.LEATHER.equals(armorType) || ArmorType.MAIL.equals(armorType))) {
            throw new SwashbucklerInvalidArmorException("Swashbucklers can only wear leather or mail armor");
        }

        if (Slot.HEAD.equals(armorSlot) || Slot.BODY.equals(armorSlot) || Slot.LEGS.equals(armorSlot)) {
            equipment.equipItem(armor);
        } else {
            throw new SwashbucklerInvalidArmorException("Swashbucklers can only wear armor on the head, body, or leg slots");
        }
    }

    public HeroAttribute calcTotalAttributes() {
        //Calculate swashbucklers total attributes
        HeroAttribute totalAttributes = new HeroAttribute(levelAttributes.getStrength(), levelAttributes.getDexterity(), levelAttributes.getIntelligence());
        HeroAttribute totalArmorAttributes = equipment.calculateTotalArmorAttributes();
        totalAttributes.addAttributes(totalArmorAttributes);
        return totalAttributes;
    }

    @Override
    public String displayHero() {
        StringBuilder heroDisplay = new StringBuilder(super.displayHero());

        heroDisplay.append("Class: ").append(className).append("\n");

        return heroDisplay.toString();
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
