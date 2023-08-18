package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.armor.WizardInvalidArmorException;
import no.noroff.accelerate.exceptions.weapons.WizardInvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorType;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

import java.util.Arrays;

public class Wizard extends Hero {
    public Wizard(String name) {
        super(name, HeroClass.WIZARD);
        this.levelAttributes = new HeroAttribute(1, 1, 8);
        this.validWeaponTypes = Arrays.asList(WeaponType.STAFF, WeaponType.WAND);
        this.validArmorTypes = Arrays.asList(ArmorType.CLOTH);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(1, 5, 0);
    }

    @Override
    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(1, 1, 5));
    }

    @Override
    public double calcDamage() {
        //Calculate wizard damage
        Weapon equippedWeapon = getEquippedWeapon();

        if (equippedWeapon != null) {
            int weaponDamage = equippedWeapon.getWeaponDamage();
            double damageAttribute = calcTotalAttributes().getIntelligence() / 100.0;
            double damage = weaponDamage * (1 + damageAttribute);
            return Math.round(damage * 100.0) / 100.0;
        } else {
            return 1.0;
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) throws WizardInvalidWeaponException {
        //Check if wizard equipped weapons are valid
        if (level < weapon.getRequiredLevel()) {
            throw new WizardInvalidWeaponException("Hero level is too low to equip this weapon");
        }

        if (!validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new WizardInvalidWeaponException("Wizards can only use staves or wands");
        }

        equipment.equipItem(weapon);
    }

    public void equipArmor(Armor armor) throws InvalidArmorException {
        //Check if equipped wizard armor is valid
        if (level < armor.getRequiredLevel()) {
            throw new InvalidArmorException("Hero level is too low to equip this armor");
        }

        ArmorType armorType = armor.getArmorType();
        Slot armorSlot = armor.getSlot();

        if (!ArmorType.CLOTH.equals(armorType)) {
            throw new WizardInvalidArmorException("Wizards can only wear cloth armor");
        }

        if (Slot.HEAD.equals(armorSlot) || Slot.BODY.equals(armorSlot) || Slot.LEGS.equals(armorSlot)) {
            equipment.equipItem(armor);
        } else {
            throw new WizardInvalidArmorException("Wizards can only wear armor on the head, body, or leg slots");
        }
    }

    public HeroAttribute calcTotalAttributes() {
        //Calculate wizards total attributes
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
