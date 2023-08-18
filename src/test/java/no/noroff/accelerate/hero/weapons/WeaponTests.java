package no.noroff.accelerate.hero.weapons;

import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeaponTests {
    @Test
    public void testIfWeaponIsCreatedWithCorrectName() {
    // Arrange
    final String expectedName = "Master bow";
    final int requiredLevel = 1;
    final WeaponType weaponType = WeaponType.BOW;
    final int weaponDamage = 100;

    // Act
    Weapon bow = new Weapon(expectedName, requiredLevel, weaponType, weaponDamage);
    final String actualName = bow.getName();

    // Assert
    assertEquals(expectedName, actualName);
}


   @Test
   public void testIfWeaponIsCreatedWithCorrectRequiredLevel() {
       //Arrange
        final String weaponName = "Hidden blade";
        final int expectedLevel = 2;
        final WeaponType weaponType = WeaponType.DAGGER;
        final int weaponDamage = 1000;

        //Act
       Weapon dagger = new Weapon(weaponName, expectedLevel, weaponType, weaponDamage);
       final int actualLevel = dagger.getRequiredLevel();

       //Assert
       assertEquals(expectedLevel, actualLevel);
   }

    @Test
    public void testIfWeaponIsCreatedWithCorrectWeaponType() {
        //Arrange
        final String weaponName = "Tomahawk";
        final int requiredLevel = 15;
        final WeaponType weaponType = WeaponType.HATCHET;
        final int weaponDamage = 125;

        //Act
        Weapon hatchet = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        final String expectedType = String.valueOf(WeaponType.HATCHET);
        final String actualType = String.valueOf(hatchet.getWeaponType());

        //Assert
        assertEquals(expectedType, actualType);
    }

    @Test
    public void testIfWeaponIsCreatedWithCorrectWeaponDamage() {
        //Arrange
        final String weaponName = "Skull Crusher";
        final int requiredLevel = 34;
        final WeaponType weaponType = WeaponType.MACE;
        final int expectedWeaponDamage = 200;

        //Act
        Weapon hatchet = new Weapon(weaponName, requiredLevel, weaponType, expectedWeaponDamage);

        final int actualWeaponDamage = hatchet.getWeaponDamage();

        //Assert
        assertEquals(expectedWeaponDamage, actualWeaponDamage);
    }

    @Test
    public void testIfWeaponIsAutomaticallyAssignedToTheWeaponSlotWhenCreated() {
        //Arrange
        final String weaponName = "Sword of Eden";
        final int requiredLevel = 45;
        final int weaponDamage = 500;
        final WeaponType weaponType = WeaponType.SWORD;
        final Slot expectedSlot = Slot.WEAPON;

        //Act
        Weapon sword = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        final Slot actualSlot = sword.getSlot();

        //Assert
        assertEquals(expectedSlot, actualSlot);
    }
}
