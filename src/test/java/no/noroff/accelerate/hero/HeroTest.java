package no.noroff.accelerate.hero;

import no.noroff.accelerate.hero.heroclasses.Archer;
import no.noroff.accelerate.hero.heroclasses.Barbarian;
import no.noroff.accelerate.hero.heroclasses.Swashbuckler;
import no.noroff.accelerate.hero.heroclasses.Wizard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroTest {

    @Test
    public void testNewHeroBarbarianCreation() {
        // Arrange
        Barbarian barbarian = new Barbarian("Conan");

        assertEquals("Conan", barbarian.name);
        assertEquals(1, barbarian.level);
        assertEquals(5, barbarian.levelAttributes.getStrength());
        assertEquals(2, barbarian.levelAttributes.getDexterity());
        assertEquals(1, barbarian.levelAttributes.getIntelligence());

        System.out.println("New Barbarian hero created with the name: " + barbarian.name + "\n" +
                        "Barbarian Level: " + barbarian.level + "\n" +
                        "Barbarian attributes: " + "\n" + "Strength: " + barbarian.levelAttributes.getStrength()
                        + "\n" + "Dexterity: " + barbarian.levelAttributes.getDexterity()
                        + "\n" + "Intelligence: " + barbarian.levelAttributes.getIntelligence()
        );
    }

    @Test
    public void testNewHeroArcherCreation() {
        // Arrange
        Archer archer = new Archer("Sniperman");

        assertEquals("Sniperman", archer.name);
        assertEquals(1, archer.level);
        assertEquals(1, archer.levelAttributes.getStrength());
        assertEquals(7, archer.levelAttributes.getDexterity());
        assertEquals(1, archer.levelAttributes.getIntelligence());
    }

    @Test
    public void testNewHeroSwashbucklerCreation() {
        Swashbuckler swashbuckler = new Swashbuckler("Dawg");

        assertEquals("Dawg", swashbuckler.name);
        assertEquals(1, swashbuckler.level);
        assertEquals(2, swashbuckler.levelAttributes.getStrength());
        assertEquals(6, swashbuckler.levelAttributes.getDexterity());
        assertEquals(1, swashbuckler.levelAttributes.getIntelligence());
    }

    @Test
    public void testNewHeroWizardCreation() {
        Wizard wizard = new Wizard("Gandalf");

        assertEquals("Gandalf", wizard.name);
        assertEquals(1, wizard.level);
        assertEquals(1, wizard.levelAttributes.getStrength());
        assertEquals(1, wizard.levelAttributes.getDexterity());
        assertEquals(8, wizard.levelAttributes.getIntelligence());
    }

    @Test
    public void testCheckIfArcherAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        Archer archer = new Archer ("Insert Stereotypical Archer Name");
        assertEquals(1, archer.level);

        archer.levelUp();

        HeroAttribute expectedAttributeLevelUp = new HeroAttribute(1, 5, 1);
        HeroAttribute actualAttributeLevelUp = archer.getAttributeLevelUp();

        assertEquals(2, archer.level);
        assertEquals(expectedAttributeLevelUp.getStrength(), actualAttributeLevelUp.getStrength());
        assertEquals(expectedAttributeLevelUp.getDexterity(), actualAttributeLevelUp.getDexterity());
        assertEquals(expectedAttributeLevelUp.getIntelligence(), actualAttributeLevelUp.getIntelligence());
    }

    @Test
    public void testCheckIfBarbarianAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        Barbarian barbarian = new Barbarian ("Bigboy");
        assertEquals(1, barbarian.level);

        barbarian.levelUp();

        HeroAttribute expectedAttributeLevelUp = new HeroAttribute(3, 2, 1);
        HeroAttribute actualAttributeLevelUp = barbarian.getAttributeLevelUp();

        assertEquals(2, barbarian.level);
        assertEquals(expectedAttributeLevelUp.getStrength(), actualAttributeLevelUp.getStrength());
        assertEquals(expectedAttributeLevelUp.getDexterity(), actualAttributeLevelUp.getDexterity());
        assertEquals(expectedAttributeLevelUp.getIntelligence(), actualAttributeLevelUp.getIntelligence());
    }

    @Test
    public void testCheckIfSwashbucklerAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        Swashbuckler swashbuckler = new Swashbuckler ("Fastboi");
        assertEquals(1, swashbuckler.level);

        swashbuckler.levelUp();

        HeroAttribute expectedAttributeLevelUp = new HeroAttribute(1, 4, 1);
        HeroAttribute actualAttributeLevelUp = swashbuckler.getAttributeLevelUp();

        assertEquals(2, swashbuckler.level);
        assertEquals(expectedAttributeLevelUp.getStrength(), actualAttributeLevelUp.getStrength());
        assertEquals(expectedAttributeLevelUp.getDexterity(), actualAttributeLevelUp.getDexterity());
        assertEquals(expectedAttributeLevelUp.getIntelligence(), actualAttributeLevelUp.getIntelligence());
    }

    @Test
    public void testCheckIfWizardAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        Wizard wizard = new Wizard ("Insert Stereotypical Archer Name");
        assertEquals(1, wizard.level);

        wizard.levelUp();

        HeroAttribute expectedAttributeLevelUp = new HeroAttribute(1, 1, 5);
        HeroAttribute actualAttributeLevelUp = wizard.getAttributeLevelUp();

        assertEquals(2, wizard.level);
        assertEquals(expectedAttributeLevelUp.getStrength(), actualAttributeLevelUp.getStrength());
        assertEquals(expectedAttributeLevelUp.getDexterity(), actualAttributeLevelUp.getDexterity());
        assertEquals(expectedAttributeLevelUp.getIntelligence(), actualAttributeLevelUp.getIntelligence());

        System.out.println("The wizards attribute gain is S: " + actualAttributeLevelUp.getStrength());
    }

    @Test
    public void testCalculateDamageWithNoWeaponEquippedForTheBarbarian() {
        Barbarian barbarian = new Barbarian("Dingus");

        int calculatedDamage = barbarian.calcDamage();

        assertEquals(1, calculatedDamage);
    }

}