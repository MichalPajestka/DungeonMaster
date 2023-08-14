package no.noroff.accelerate.heroclasses;

public abstract class Hero {
    protected String name;
    protected int level;
    protected int levelAttributes;

    protected String validWeaponTypes;
    protected String validArmorTypes;

    public Hero(String name, int level, int levelAttributes, String validWeaponTypes, String validArmorTypes) {
        this.name = name;
        this.level = level;
        this.levelAttributes = levelAttributes;
        this.validWeaponTypes = validWeaponTypes;
        this.validArmorTypes = validArmorTypes;
    }

    public void levelUp() {

    }

    public void equipArmor(){

    }

    public void equipWeapon() {

    }

    public void damageDealt() {

    }

    public void totalAttributes() {

    }

    public void displayHero() {

    }

}
