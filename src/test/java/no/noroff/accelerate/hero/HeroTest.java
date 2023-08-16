package no.noroff.accelerate.hero;

import no.noroff.accelerate.hero.heroclasses.Archer;
import no.noroff.accelerate.hero.heroclasses.Barbarian;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroTest {

    @Test
    public void testBarbarianCreation() {
        // Arrange
        Barbarian barbarian = new Barbarian("Conan");

        assertEquals("Conan", barbarian.name);
        assertEquals(1, barbarian.level);
        assertEquals(5, barbarian.levelAttributes.getStrength());
        assertEquals(2, barbarian.levelAttributes.getDexterity());
        assertEquals(1, barbarian.levelAttributes.getIntelligence());
    }

    @Test
    public void testArcherCreation() {
        // Arrange
        Archer archer = new Archer("Sniperman");

        assertEquals("Sniperman", archer.name);
        assertEquals(1, archer.level);
        assertEquals(1, archer.levelAttributes.getStrength());
        assertEquals(7, archer.levelAttributes.getDexterity());
        assertEquals(1, archer.levelAttributes.getIntelligence());
    }

    @Test
    public void testCalculateDamageWithNoWeaponEquipped() {
        Barbarian barbarian = new Barbarian("Dingus");

        int calculatedDamage = barbarian.calcDamage();

        assertEquals(1, calculatedDamage);
    }

}