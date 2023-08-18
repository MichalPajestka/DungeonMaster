package no.noroff.accelerate.hero.weapons;

import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeaponTests {

//  WAND
    @Test
    public void testCreateABowWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot() {
        //Arrange
        Weapon bow = new Weapon("Master bow", 20, WeaponType.BOW, 90);

        //Act & assert
        assertEquals("Master bow", bow.getName());
        assertEquals(20, bow.getRequiredLevel());
        assertEquals(WeaponType.BOW, bow.getWeaponType());
        assertEquals(90, bow.getWeaponDamage());
        assertEquals(Slot.WEAPON, bow.getSlot());
    }

    @Test
    public void testCreateADaggerWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot() {
        //Arrange
        Weapon weapon = new Weapon("Stealth blade", 10, WeaponType.DAGGER, 65);

        //Act & assert
        assertEquals("Stealth blade", weapon.getName());
        assertEquals(10, weapon.getRequiredLevel());
        assertEquals(WeaponType.DAGGER, weapon.getWeaponType());
        assertEquals(65, weapon.getWeaponDamage());
        assertEquals(Slot.WEAPON, weapon.getSlot());
    }

    @Test
    public void testCreateAHatchetWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot() {
        //Arrange
        Weapon weapon = new Weapon("Tomahawk", 13, WeaponType.HATCHET, 45);

        //Act & assert
        assertEquals("Tomahawk", weapon.getName());
        assertEquals(13, weapon.getRequiredLevel());
        assertEquals(WeaponType.HATCHET, weapon.getWeaponType());
        assertEquals(45, weapon.getWeaponDamage());
        assertEquals(Slot.WEAPON, weapon.getSlot());
    }

    @Test
    public void testCreateAMaceWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot() {
        //Arrange
        Weapon weapon = new Weapon("Skull Crusher", 23, WeaponType.MACE, 75);

        //Act & assert
        assertEquals("Skull Crusher", weapon.getName());
        assertEquals(23, weapon.getRequiredLevel());
        assertEquals(WeaponType.MACE, weapon.getWeaponType());
        assertEquals(75, weapon.getWeaponDamage());
        assertEquals(Slot.WEAPON, weapon.getSlot());
    }

    @Test
    public void testCreateAStaffWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot() {
        //Arrange
        Weapon weapon = new Weapon("Staff of Doom", 50, WeaponType.STAFF, 500);

        //Act & assert
        assertEquals("Staff of Doom", weapon.getName());
        assertEquals(50, weapon.getRequiredLevel());
        assertEquals(WeaponType.STAFF, weapon.getWeaponType());
        assertEquals(500, weapon.getWeaponDamage());
        assertEquals(Slot.WEAPON, weapon.getSlot());
    }

    @Test
    public void testCreateASwordWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot(){
        //Arrange
        Weapon weapon = new Weapon("Sword of Eden", 78, WeaponType.SWORD, 230);

        //Act & assert
        assertEquals("Sword of Eden", weapon.getName());
        assertEquals(78, weapon.getRequiredLevel());
        assertEquals(WeaponType.SWORD, weapon.getWeaponType());
        assertEquals(230, weapon.getWeaponDamage());
        assertEquals(Slot.WEAPON, weapon.getSlot());
    }

    @Test
    public void testCreateAHWandWithCorrectNameCorrectRequiredLevelCorrectWeaponTypeCorrectDamageAndCorrectSlot() {
        //Arrange
        Weapon weapon = new Weapon("The Elder Wand", 100, WeaponType.WAND, 1000);

        //Act & assert
        assertEquals("The Elder Wand", weapon.getName());
        assertEquals(100, weapon.getRequiredLevel());
        assertEquals(WeaponType.WAND, weapon.getWeaponType());
        assertEquals(1000, weapon.getWeaponDamage());
        assertEquals(Slot.WEAPON, weapon.getSlot());
    }
}
