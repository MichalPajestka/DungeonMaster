package no.noroff.accelerate.heroclasses;

public class HeroAttribute {
    private int strenght;
    private int dexterity;
    private int intelligence;


    public HeroAttribute(int strength, int dexterity, int intelligence) {
        this.strenght = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strenght;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
