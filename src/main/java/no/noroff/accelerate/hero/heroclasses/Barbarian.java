package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.armor.BarbarianInvalidArmorException;
import no.noroff.accelerate.exceptions.weapons.BarbarianInvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.hero.HeroClass;
import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorType;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

import java.util.Arrays;

public class Barbarian extends Hero {
    public Barbarian(String name) {
        super(name, HeroClass.BARBARIAN);
        this.levelAttributes = new HeroAttribute(5, 2, 1);
        this.validWeaponTypes = Arrays.asList(WeaponType.HATCHET, WeaponType.MACE, WeaponType.SWORD);
        this.validArmorTypes = Arrays.asList(ArmorType.MAIL, ArmorType.PLATE);
    }

    @Override
    public HeroAttribute getAttributeLevelUp() {
        return new HeroAttribute(3, 2, 1);
    }

    @Override
    protected void levelUpAttributes() {
        levelAttributes.addAttributes(new HeroAttribute(3, 2, 1));
    }

    @Override
    public double calcDamage() {
        //Calculate barbarian damage
        Weapon equippedWeapon = getEquippedWeapon();

        if (equippedWeapon != null) {
            int weaponDamage = equippedWeapon.getWeaponDamage();
            double damageAttribute = calcTotalAttributes().getStrength() / 100.0;
            double damage = weaponDamage * (1 + damageAttribute);
            return Math.round(damage * 100.0) / 100.0;
        } else {
            return 1.0;
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) throws BarbarianInvalidWeaponException {
        //Check if barbarian equipped weapons are valid
        if (level < weapon.getRequiredLevel()) {
            throw new BarbarianInvalidWeaponException("Hero level is too low to equip this weapon");
        }

        if (!validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new BarbarianInvalidWeaponException("Barbarians can only use hatchets, maces, or swords");
        }

        equipment.equipItem(weapon);
    }

    public void equipArmor(Armor armor) throws InvalidArmorException {
        //Check if equipped barbarian armor is valid
        if (level < armor.getRequiredLevel()) {
            throw new InvalidArmorException("Hero level is too low to equip this armor");
        }

        ArmorType armorType = armor.getArmorType();
        Slot armorSlot = armor.getSlot();

        if (!(ArmorType.MAIL.equals(armorType) || ArmorType.PLATE.equals(armorType))) {
            throw new BarbarianInvalidArmorException("Barbarians can only wear mail or plate armor");
        }

        if (Slot.HEAD.equals(armorSlot) || Slot.BODY.equals(armorSlot) || Slot.LEGS.equals(armorSlot)) {
            equipment.equipItem(armor);
        } else {
            throw new BarbarianInvalidArmorException("Barbarians can only wear armor on the head, body, or leg slots");
        }
    }

    public HeroAttribute calcTotalAttributes() {
        //Calculate barbarians total attributes
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
