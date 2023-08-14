package no.noroff.accelerate;

public class HeroAttribute {
    private int strenght;
    private int dexterity;
    private int intelligence;


    public HeroAttribute(int strenght, int dexterity, int intelligence) {
        this.strenght = strenght;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
