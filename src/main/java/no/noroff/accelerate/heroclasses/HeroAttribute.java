package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.items.armor.ArmorAttribute;

public class HeroAttribute {
    protected int strength;
    protected int dexterity;
    protected int intelligence;


    public HeroAttribute(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void addAttributes(HeroAttribute other) {
        this.strength += other.strength;
        this.dexterity += other.dexterity;
        this.intelligence += other.intelligence;
    }
}
