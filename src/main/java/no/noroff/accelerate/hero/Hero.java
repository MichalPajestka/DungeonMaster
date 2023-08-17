package no.noroff.accelerate.hero;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.hero.heroclasses.Archer;
import no.noroff.accelerate.hero.heroclasses.Barbarian;
import no.noroff.accelerate.hero.heroclasses.Swashbuckler;
import no.noroff.accelerate.hero.heroclasses.Wizard;
import no.noroff.accelerate.items.Equipment;
import no.noroff.accelerate.items.Item;
import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorAttribute;
import no.noroff.accelerate.items.armor.ArmorType;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;

import java.util.List;

public abstract class Hero {
    protected String name;
    protected String className;
    protected int level;
    protected HeroAttribute levelAttributes;

    protected List<WeaponType> validWeaponTypes;
    protected List<ArmorType> validArmorTypes;
    protected Equipment equipment;

    public Hero(String name, HeroClass heroClass) {
        this.name = name;
        this.className = getClassName(heroClass);
        this.level = 1; //Heroes starts at level 1
        equipment = new Equipment();
    }

    private String getClassName(HeroClass heroClass) {
        switch (heroClass) {
            case ARCHER:
                return "Archer";
            case SWASHBUCKLER:
                return "Swashbuckler";
            case BARBARIAN:
                return "Barbarian";
            case WIZARD:
                return "Wizard";
        }
        return "";
    }

    public void levelUp() {
        level++;
        levelUpAttributes();
    }

    protected abstract HeroAttribute getAttributeLevelUp();

    public abstract void equipWeapon(Weapon weapon) throws InvalidWeaponException;

    public Weapon getEquippedWeapon() {
        return (Weapon) equipment.getEquippedItems().get(Slot.WEAPON);
    }


    public void equipArmor(Armor armor) throws InvalidArmorException {
        if(!validArmorTypes.contains(armor.getArmorType())) {
            throw new InvalidArmorException("Invalid armor type for this hero");
        }

        if (level < armor.getRequiredLevel()) {
            throw new InvalidArmorException("Hero level is too low to equip this armor");
        }

        if ((this instanceof Archer) && !ArmorType.LEATHER.equals(armor.getArmorType())
                || !ArmorType.MAIL.equals(armor.getArmorType())) {
            throw new InvalidArmorException("Archers can only wear light armor, such as leather or mail");

        } else if ((this instanceof Barbarian) && !ArmorType.MAIL.equals(armor.getArmorType())
                || !ArmorType.PLATE.equals(armor.getArmorType())) {
            throw new InvalidArmorException(("Barbarians can only wear mail or plate armor"));

        } else if ((this instanceof Swashbuckler) && !ArmorType.MAIL.equals(armor.getArmorType())
                || !ArmorType.LEATHER.equals(armor.getArmorType())) {
            throw new InvalidArmorException("Swashbucklers can only wear light armor, such as leather or mail");

        } else if ((this instanceof Wizard) && !ArmorType.CLOTH.equals(armor.getArmorType())) {
            throw new InvalidArmorException("Wizards can only wear cloth");
        }

        equipment.equipItem(armor);
    }

    public HeroAttribute calcTotalAttributes() {
        HeroAttribute totalAttributes = new HeroAttribute(
                levelAttributes.getStrength(),
                levelAttributes.getDexterity(),
                levelAttributes.getIntelligence()
        );

        for (Item item : equipment.getEquippedItems().values()) {
            if (item instanceof Armor) {
                ArmorAttribute armorAttribute = ((Armor) item).getArmorAttribute();
                totalAttributes.addAttributes(new HeroAttribute(
                        armorAttribute.getStrengthBonus(),
                        armorAttribute.getDexterityBonus(),
                        armorAttribute.getIntelligenceBonus()
                ));
            }
        }
        // Return the HeroAttribute object, not the sum of attributes
        return totalAttributes;
    }

    protected abstract void levelUpAttributes();

    public abstract double calcDamage();

    public String displayHero() {
        StringBuilder heroDisplay = new StringBuilder();

        heroDisplay.append("Name: ").append(name).append("\n");
        heroDisplay.append("Class: ").append(className).append("\n");
        heroDisplay.append("Level: ").append(level).append("\n");

        HeroAttribute totalAttributes = calcTotalAttributes();
        heroDisplay.append("Attributes: ").append("\n");
        heroDisplay.append("    Strength: ").append(totalAttributes.getStrength()).append("\n");
        heroDisplay.append("    Dexterity: ").append(totalAttributes.getDexterity()).append("\n");
        heroDisplay.append("    Intelligence: ").append(totalAttributes.getIntelligence()).append("\n");

        double heroDamage = calcDamage();
        heroDisplay.append("Damage: ").append(heroDamage).append("\n");

        return heroDisplay.toString();



    }

}
