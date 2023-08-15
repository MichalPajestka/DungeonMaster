package no.noroff.accelerate.items.armor;

public class ArmorAttribute {
    protected int strengthBonus;
    protected int dexterityBonus;
    private int intelligenceBonus;

    public ArmorAttribute(int strengthBonus, int dexterityBonus, int intelligenceBonus) {
        this.strengthBonus = strengthBonus;
        this.dexterityBonus = dexterityBonus;
        this.intelligenceBonus = intelligenceBonus;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public int getDexterityBonus() {
        return dexterityBonus;
    }

    public int getIntelligenceBonus() {
        return intelligenceBonus;
    }

    public void addAttributes(ArmorAttribute other) {
        this.strengthBonus += other.strengthBonus;
        this.dexterityBonus += other.dexterityBonus;
        this.intelligenceBonus += other.intelligenceBonus;
    }
}
