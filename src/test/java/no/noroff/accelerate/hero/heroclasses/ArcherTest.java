package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.weapons.ArcherInvalidWeaponException;
import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.hero.Hero;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ArcherTest {
    @Test
    public void testNewHeroArcherCreation() {
        // Arrange
        Archer archer = new Archer("Sniperman");

        //Act and assert
        assertEquals("Sniperman", archer.getName());
        assertEquals(1, archer.getLevel());
        assertEquals(1, archer.getLevelAttributes().getStrength());
        assertEquals(7, archer.getLevelAttributes().getDexterity());
        assertEquals(1, archer.getLevelAttributes().getIntelligence());

    }

    @Test
    public void testIfArcherLevelIncreases() {
        //Arrange
        Archer archer = new Archer("Sniperman");
        assertEquals(1, archer.getLevel());

        // Act
        archer.levelUp();

        //Assert
        assertEquals(2, archer.getLevel());

    }

    @Test
    public void testCheckIfArcherAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        //Arrange
        Archer archer = new Archer ("Sniperman");

        //Act
        HeroAttribute expectedAttributeLevelUp = new HeroAttribute(1, 5, 1);
        HeroAttribute actualAttributeLevelUp = archer.getAttributeLevelUp();

        //Assert
        assertEquals(expectedAttributeLevelUp.getStrength(), actualAttributeLevelUp.getStrength());
        assertEquals(expectedAttributeLevelUp.getDexterity(), actualAttributeLevelUp.getDexterity());
        assertEquals(expectedAttributeLevelUp.getIntelligence(), actualAttributeLevelUp.getIntelligence());

    }

    @Test
    public void testChechIfArcherAttributesLevelUpCorrectlyWhenTheArcherLevelsUp() {
        //Arrange
        Archer archer = new Archer("Sniperman");

        //Act
        archer.levelUp();

        //Assert
        assertEquals(2, archer.getLevel());
        assertEquals(2, archer.getLevelAttributes().getStrength());
        assertEquals(12, archer.getLevelAttributes().getDexterity());
        assertEquals(2, archer.getLevelAttributes().getIntelligence());

    }

    @Test
    public void testCheckIfArcherCanEquipWeaponWithValidWeaponTypeAndLevel() {
        //Arrange
        Archer archer = new Archer("Sniperman");
        Weapon weapon = new Weapon("Primitive bow", 1, WeaponType.BOW, 10);

        //Act
        try {
            archer.equipWeapon(weapon);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        //Assert
        assertEquals(weapon, archer.getEquippedWeapon());
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingAWeaponWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        Archer archer = new Archer("Sniperman");
        Weapon weapon = new Weapon("Barret .50 cal", 30, WeaponType.BOW, 500);

        //Act & Assert
        InvalidWeaponException exception = assertThrows(InvalidWeaponException.class, () ->  {
            archer.equipWeapon(weapon);
        });

        assertEquals("Hero level is too low to equip this weapon", exception.getMessage());
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingAWeaponWithInvalidTypeButValidLevelThrowsCorrectException() {
        //Arrange
        Archer archer = new Archer("Sniperman");
        Weapon weapon = new Weapon("Hidden Blade", 1, WeaponType.DAGGER, 1000);

        //Act & Assert
        InvalidWeaponException exception = assertThrows(InvalidWeaponException.class, () ->  {
            archer.equipWeapon(weapon);
        });

        assertEquals("Archers can only use bows", exception.getMessage());
    }

    @Test
    public void testCalculateArcherDamageWithNoWeaponEquipped() {
        Archer archer = new Archer("Sniperman");

        double calculatedDamage = archer.calcDamage();

        assertEquals(1, calculatedDamage);
    }

    @Test
    public void testCalculateIfArcherDamageIsCalculatedProperlyWhenAWeaponIsEquipped() {
        Archer archer = new Archer("Sniperman");
        Weapon weapon = new Weapon("Enchanted bow", 1, WeaponType.BOW, 35);

        archer.equipWeapon(weapon);

        double calculatedDamage = archer.calcDamage();

        assertEquals(37.45, calculatedDamage);
    }


}