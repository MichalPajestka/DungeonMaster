package no.noroff.accelerate.hero;


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

    public void addArmorAttributes(ArmorAttribute armorAttribute) {
        this.strength += armorAttribute.getStrengthBonus();
        this.dexterity += armorAttribute.getDexterityBonus();
        this.intelligence += armorAttribute.getIntelligenceBonus();
    }

    //These two methods were necessary for the armor attribute tests to work
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HeroAttribute that = (HeroAttribute) obj;
        return strength == that.strength && dexterity == that.dexterity && intelligence == that.intelligence;
    }

    @Override
    public String toString() {
        return "HeroAttribute{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                '}';
    }
}
