package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.InvalidArmorException;
import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.hero.HeroAttribute;
import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorAttribute;
import no.noroff.accelerate.items.armor.ArmorType;
import no.noroff.accelerate.items.weapons.Weapon;
import no.noroff.accelerate.items.weapons.WeaponType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BarbarianTest {

    @Test
    public void testIfBarbarianIsCreatedWithCorrectName() {
        // Arrange
        final String expectedName = "Brutus";

        // Act
        Barbarian barbarian = new Barbarian(expectedName);
        final String actualName = barbarian.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testIfBarbarianIsCreatedWithCorrectLevelWhichShouldBeOne() {
        //Arrange
        final String barbarianName = "Brutus";
        final int expectedLevel = 1;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        final int actualLevel = barbarian.getLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testIfBarbarianLevelIncreases() {
        //Arrange
        final int expectedLevel = 2;
        final String barbarianName = "Brutus";

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);
        barbarian.levelUp();
        final int actualLevel = barbarian.getLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);

    }

    @Test
    public void testCheckIfBarbariansDefaultAttributesAreCorrectWhenCreated() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(5, 2, 1);
        final String barbarianName = "Brutus";

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);
        HeroAttribute actualAttributes = barbarian.getLevelAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfBarbarianAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(8, 4, 2);
        final String barbarianName = "Brutus";

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);
        HeroAttribute actualAttributes = barbarian.getLevelAttributes();

        barbarian.levelUp();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfBarbarianCanEquipWeaponWithValidWeaponTypeAndLevel() {
        //Arrange
        final String barbarianName = "Brutus";
        final String weaponName = "Hatchet";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.HATCHET;
        final int weaponDamage = 10;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Weapon expectedEquippedWeapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        try {
            barbarian.equipWeapon(expectedEquippedWeapon);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Weapon actualEquippedWeapon = barbarian.getEquippedWeapon();

        assertEquals(expectedEquippedWeapon, actualEquippedWeapon);
    }

    @Test
    public void testCheckIfWhenBarbarianIsEquippingAWeaponWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String barbarianName = "Brutus";
        final String weaponName = "Mighty Warhammer";
        final int requiredLevel = 30;
        final WeaponType weaponType = WeaponType.MACE;
        final int weaponDamage = 500;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Hero level is too low to equip this weapon");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> barbarian.equipWeapon(weapon));

        ////Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenBarbarianIsEquippingAWeaponWithInvalidTypeButValidLevelThrowsCorrectException() {
        //Arrange
        final String barbarianName = "Brutus";
        final String weaponName = "Magic Wand";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.WAND;
        final int weaponDamage = 1000;

        //Act

        Barbarian barbarian = new Barbarian(barbarianName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Barbarians can only use hatchets, maces, or swords");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> barbarian.equipWeapon(weapon));

        //Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateBarbarianDamageWithNoWeaponEquipped() {
        //Arrange
        final String barbarianName = "Brutus";
        final int expectedDamage = 1;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        final double actualDamage = barbarian.calcDamage();

        //Assert
        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void testCalculateIfBarbarianDamageIsCalculatedProperlyWhenAWeaponIsEquipped() {
        //Arrange
        final String barbarianName = "Brutus";
        final String weaponName = "Giga Sword";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.SWORD;
        final int weaponDamage = 35;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        barbarian.equipWeapon(weapon);

        final double actualDamage = barbarian.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) barbarian.getLevelAttributes().getStrength() / 100);

        //Assert
        assertEquals(expectedDamage, actualDamage, 0.01);
    }

    @Test
    public void testCalculateIfBarbarianDamageIsCalculatedProperlyWhenAWeaponIsEquippedAndADifferentOneIsEquippedAgain() {
        //Arrange
        final String barbarianName = "Brutus";
        final String weaponName = "Giga Sword";
        final String weapon2Name = "War Mace";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.SWORD;
        final WeaponType weapon2Type = WeaponType.MACE;
        final int weaponDamage = 35;
        final int weapon2Damage = 90;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Weapon weapon2 = new Weapon(weapon2Name, requiredLevel, weapon2Type, weapon2Damage);

        barbarian.equipWeapon(weapon);
        barbarian.equipWeapon(weapon2);

        final double calculatedDamage = barbarian.calcDamage();
        final double expectedDamage = weapon2Damage * (1 + (double) barbarian.getLevelAttributes().getStrength() / 100);

        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCalculateIfBarbarianDamageIsCalculatedProperlyWhenAWeaponAndArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String barbarianName = "Brutus";
        final String weaponName = "War Hammer";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.MACE;
        final int weaponDamage = 35;

        final String armorName = "Plate Chestplate";
        final ArmorType armorType = ArmorType.PLATE;
        final ArmorAttribute armorAttributes = new ArmorAttribute(20, 0, 10);
        final Slot armorSlot = Slot.BODY;

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Armor chestplate = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        barbarian.equipWeapon(weapon);
        barbarian.equipArmor(chestplate);

        // Calculate total attributes after equipping armor
        HeroAttribute totalAttributes = barbarian.calcTotalAttributes();

        // Calculate expected damage using totalAttributes and weaponDamage
        final double calculatedDamage = barbarian.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) totalAttributes.getStrength() / 100);

        // Assert
        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCheckIfBarbarianCanEquipArmorWithValidArmorTypeAndLevel() {
        //Arrange
        final String barbarianName = "Brutus";
        final String armorName = "Mail Leggings";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.MAIL;
        final ArmorAttribute armorAttributes = new ArmorAttribute(5, 10, 8);
        final Slot armorSlot = Slot.LEGS;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Armor expectedEquippedArmor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        try {
            barbarian.equipArmor(expectedEquippedArmor);
        } catch (InvalidArmorException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Armor actualEquippedArmor = barbarian.getEquippedArmor(armorSlot);

        assertEquals(expectedEquippedArmor, actualEquippedArmor);
    }

    @Test
    public void testCheckIfWhenBarbarianIsEquippingArmorWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String barbarianName = "Brutus";
        final String armorName = "Berserker Plate";
        final int requiredLevel = 30;
        final ArmorType armorType = ArmorType.PLATE;
        final ArmorAttribute armorAttributes = new ArmorAttribute(10, 15, 8);
        final Slot armorSlot = Slot.BODY;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Hero level is too low to equip this armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> barbarian.equipArmor(armor));

        ////Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenBarbarianIsEquippingArmorWithInvalidTypeValidLevelThrowsCorrectException() {
        //Arrange
        final String barbarianName = "Brutus";
        final String armorName = "Cloth Robe";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(0, 2, 5);
        final Slot armorSlot = Slot.BODY;

        //Act
        Barbarian barbarian = new Barbarian(barbarianName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Barbarians can only wear mail or plate armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> barbarian.equipArmor(armor));

        ////Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateBarbarianAttributesWhenNoArmorIsEquipped() {
        // Arrange
        final String barbarianName = "Brutus";
        final HeroAttribute expectedAttributes = new HeroAttribute(5, 2, 1);

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);
        HeroAttribute actualAttributes = barbarian.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateBarbarianAttributesWhenOnePieceOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String barbarianName = "Brutus";
        final String armorName = "Plate Leggings";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.PLATE;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 8, 5);
        final Slot armorSlot = Slot.LEGS;

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);

        final HeroAttribute expectedAttributes = new HeroAttribute(8, 10, 6);
        Armor leggings = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        barbarian.equipArmor(leggings);
        HeroAttribute actualAttributes = barbarian.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateBarbarianAttributesWhenTwoPiecesOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String barbarianName = "Brutus";
        final String armorName = "Chain mail Leggings";
        final String armor2Name = "Chain Mail Chestplate";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.MAIL;
        final ArmorType armorType2 = ArmorType.MAIL;
        final ArmorAttribute armorAttributes = new ArmorAttribute(12, 3, 2);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(16, 4, 2);
        final Slot armorSlot = Slot.LEGS;
        final Slot armor2Slot = Slot.BODY;

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);

        final HeroAttribute expectedAttributes = new HeroAttribute(33, 9, 5);
        Armor leggings = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor chestplate = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        barbarian.equipArmor(leggings);
        barbarian.equipArmor(chestplate);
        HeroAttribute actualAttributes = barbarian.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateBarbarianAttributesWhenAPieceOfArmorIsEquippedAndThenANewOneIsEquipped() throws InvalidArmorException {
        // Arrange
        final String barbarianName = "Brutus";
        final String armorName = "Chain Mail Leggings";
        final String armor2Name = "Plate Leggings";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.MAIL;
        final ArmorType armorType2 = ArmorType.PLATE;
        final ArmorAttribute armorAttributes = new ArmorAttribute(10, 8, 2);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(25, 4, 5);
        final Slot armorSlot = Slot.LEGS;
        final Slot armor2Slot = Slot.LEGS;

        // Act
        Barbarian barbarian = new Barbarian(barbarianName);

        final HeroAttribute expectedAttributes = new HeroAttribute(30, 6, 6);
        Armor leggings = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor leggings2 = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        barbarian.equipArmor(leggings);
        barbarian.equipArmor(leggings2);
        HeroAttribute actualAttributes = barbarian.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testBarbarianDisplayHero() {
        // Arrange
        String barbarianName = "Brutus";
        Barbarian barbarian = new Barbarian(barbarianName);

        String expectedOutput = "Name: Brutus\n" +
                "Level: 1\n" +
                "Attributes: \n" +
                "    Strength: 5\n" +
                "    Dexterity: 2\n" +
                "    Intelligence: 1\n" +
                "Damage: 1.0\n" +
                "Class: Barbarian\n";

        // Act
        String actualOutput = barbarian.displayHero();

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
