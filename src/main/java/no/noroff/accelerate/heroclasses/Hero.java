package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.InvalidWeaponException;
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

        levelAttributes.addAttributes(getAttributeLevelUp());
    }

    protected abstract HeroAttribute getAttributeLevelUp();

    public void equipWeapon(Weapon weapon) throws InvalidWeaponException {
        if(!validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new InvalidWeaponException("Invalid weapon type for this hero");
        }
        equipment.equipItem(weapon);
    }

    protected Weapon getEquippedWeapon() {
        return (Weapon) equipment.getEquippedItems().get(Slot.WEAPON);
    }


    public void equipArmor(Armor armor) throws InvalidArmorException {
        if(!validArmorTypes.contains(armor.getArmorType())) {
            throw new InvalidArmorException("Invalid armor type for this hero");
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

    public abstract int calcDamage();

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

        int heroDamage = calcDamage();
        heroDisplay.append("Damage: ").append(heroDamage).append("\n");

        return heroDisplay.toString();



    }

}
