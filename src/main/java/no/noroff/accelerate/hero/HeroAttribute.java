package no.noroff.accelerate.hero;



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

    //Overide the equals method
    //Need this to compare attributes in the tests
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HeroAttribute that = (HeroAttribute) obj;
        return strength == that.strength && dexterity == that.dexterity && intelligence == that.intelligence;
    }

}
