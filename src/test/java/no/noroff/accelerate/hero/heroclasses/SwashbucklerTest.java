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

class SwashbucklerTest {

    @Test
    public void testIfSwashbucklerIsCreatedWithCorrectName() {
        // Arrange
        final String expectedName = "Altair";

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(expectedName);
        final String actualName = swashbuckler.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testIfSwashbucklerIsCreatedWithCorrectLevelWhichShouldBeOne() {
        //Arrange
        final String swashbucklerName = "Altair";
        final int expectedLevel = 1;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        final int actualLevel = swashbuckler.getLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testIfSwashbucklerLevelIncreases() {
        //Arrange
        final int expectedLevel = 2;
        final String swashbucklerName = "Altair";

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        swashbuckler.levelUp();
        final int actualLevel = swashbuckler.getLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);

    }

    @Test
    public void testCheckIfSwashbucklerDefaultAttributesAreCorrectWhenCreated() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(2, 6, 1);
        final String swashbucklerName = "Altair";

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        HeroAttribute actualAttributes = swashbuckler.getLevelAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfSwashbucklerAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(3, 10, 2);
        final String swashbucklerName = "Altair";

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        HeroAttribute actualAttributes = swashbuckler.getLevelAttributes();

        swashbuckler.levelUp();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfSwashbucklerCanEquipWeaponWithValidWeaponTypeAndLevel() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String weaponName = "Hidden blade";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.DAGGER;
        final int weaponDamage = 1000;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Weapon expectedEquippedWeapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        try {
            swashbuckler.equipWeapon(expectedEquippedWeapon);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Weapon actualEquippedWeapon = swashbuckler.getEquippedWeapon();

        assertEquals(expectedEquippedWeapon, actualEquippedWeapon);
    }

    @Test
    public void testCheckIfWhenSwashbucklerIsEquippingAWeaponWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String weaponName = "Sword of Eden";
        final int requiredLevel = 30;
        final WeaponType weaponType = WeaponType.SWORD;
        final int weaponDamage = 500;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Hero level is too low to equip this weapon");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> swashbuckler.equipWeapon(weapon));

        //Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenSwashbucklerIsEquippingAWeaponWithInvalidTypeButValidLevelThrowsCorrectException() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String weaponName = "Big boi staff";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.STAFF;
        final int weaponDamage = 10000;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Swashbucklers can only use daggers or swords");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> swashbuckler.equipWeapon(weapon));

        //Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateSwashbucklerDamageWithNoWeaponEquipped() {
        //Arrange
        final String swashbucklerName = "Altair";
        final int expectedDamage = 1;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        final double actualDamage = swashbuckler.calcDamage();

        //Assert
        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void testCalculateIfSwashbucklerDamageIsCalculatedProperlyWhenAWeaponIsEquipped() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String weaponName = "Hidden Blade";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.DAGGER;
        final int weaponDamage = 1000;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        swashbuckler.equipWeapon(weapon);

        final double actualDamage = swashbuckler.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) swashbuckler.getLevelAttributes().getDexterity() / 100);

        //Assert
        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void testCalculateIfSwashbucklerDamageIsCalculatedProperlyWhenAWeaponIsEquippedAndADifferentOneIsEquippedAgain() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String weaponName = "Hidden Blade";
        final String weapon2Name = "Sword of Eden";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.DAGGER;
        final WeaponType weapon2Type = WeaponType.SWORD;
        final int weaponDamage = 1000;
        final int weapon2Damage = 500;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Weapon weapon2 = new Weapon(weapon2Name, requiredLevel, weapon2Type, weapon2Damage);

        swashbuckler.equipWeapon(weapon);
        swashbuckler.equipWeapon(weapon2);

        final double calculatedDamage = swashbuckler.calcDamage();
        final double expectedDamage = weapon2Damage * (1 + (double) swashbuckler.getLevelAttributes().getDexterity() / 100);

        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCalculateIfSwashbucklerDamageIsCalculatedProperlyWhenAWeaponAndArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String swashbucklerName = "Altair";
        final String weaponName = "Hidden Blade";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.DAGGER;
        final int weaponDamage = 1000;

        final String armorName = "Leather Vest";
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(5, 10, 5);
        final Slot armorSlot = Slot.BODY;

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Armor vest = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        swashbuckler.equipWeapon(weapon);
        swashbuckler.equipArmor(vest);

        HeroAttribute totalAttributes = swashbuckler.calcTotalAttributes();

        final double calculatedDamage = swashbuckler.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) totalAttributes.getDexterity() / 100);

        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCheckIfSwashbucklerCanEquipArmorWithValidArmorTypeAndLevel() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String armorName = "Assassin Robe";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 6, 4);
        final Slot armorSlot = Slot.BODY;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Armor expectedEquippedArmor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        try {
            swashbuckler.equipArmor(expectedEquippedArmor);
        } catch (InvalidArmorException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Armor actualEquippedArmor = swashbuckler.getEquippedArmor(armorSlot);

        assertEquals(expectedEquippedArmor, actualEquippedArmor);
    }

    @Test
    public void testCheckIfWhenSwashbucklerIsEquippingArmorWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String armorName = "Assassin Hood";
        final int requiredLevel = 30;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(5, 10, 5);
        final Slot armorSlot = Slot.HEAD;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Hero level is too low to equip this armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> swashbuckler.equipArmor(armor));

        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenSwashbucklerIsEquippingArmorWithInvalidTypeValidLevelThrowsCorrectException() {
        //Arrange
        final String swashbucklerName = "Altair";
        final String armorName = "Common Mage Robes";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(0, 1, 3);
        final Slot armorSlot = Slot.BODY;

        //Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Swashbucklers can only wear leather or mail armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> swashbuckler.equipArmor(armor));

        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateSwashbucklerAttributesWhenNoArmorIsEquipped() {
        // Arrange
        final String swashbucklerName = "Altair";
        final HeroAttribute expectedAttributes = new HeroAttribute(2, 6, 1);

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);
        HeroAttribute actualAttributes = swashbuckler.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateSwashbucklerAttributesWhenOnePieceOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String swashbucklerName = "Altair";
        final String armorName = "Assassin Robe";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(4, 10, 6);
        final Slot armorSlot = Slot.BODY;

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);

        final HeroAttribute expectedAttributes = new HeroAttribute(6, 16, 7);
        Armor vest = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        swashbuckler.equipArmor(vest);
        HeroAttribute actualAttributes = swashbuckler.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateSwashbucklerAttributesWhenTwoPiecesOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String swashbucklerName = "CaptainHook";
        final String armorName = "Assassin Robe";
        final String armor2Name = "Assassin Leggings";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorType armorType2 = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(4, 10, 6);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(2, 12, 3);
        final Slot armorSlot = Slot.BODY;
        final Slot armor2Slot = Slot.LEGS;

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);

        final HeroAttribute expectedAttributes = new HeroAttribute(8, 28, 10);
        Armor vest = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor leggings = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        swashbuckler.equipArmor(vest);
        swashbuckler.equipArmor(leggings);
        HeroAttribute actualAttributes = swashbuckler.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateSwashbucklerAttributesWhenAPieceOfArmorIsEquippedAndThenANewOnIsEquipped() throws InvalidArmorException {
        // Arrange
        final String swashbucklerName = "Altair";
        final String armorName = "Assassin Robe";
        final String armor2Name = "Epic Assassin Robe";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorType armorType2 = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(4, 10, 6);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(10, 35, 15);
        final Slot armorSlot = Slot.BODY;

        // Act
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);

        final HeroAttribute expectedAttributes = new HeroAttribute(12, 41, 16);
        Armor vest = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor epicVest = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armorSlot);

        swashbuckler.equipArmor(vest);
        swashbuckler.equipArmor(epicVest);
        HeroAttribute actualAttributes = swashbuckler.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testSwashbucklerDisplayHero() {
        // Arrange
        String swashbucklerName = "Altair";
        Swashbuckler swashbuckler = new Swashbuckler(swashbucklerName);

        String expectedOutput = "Name: Altair\n" +
                "Level: 1\n" +
                "Attributes: \n" +
                "    Strength: 2\n" +
                "    Dexterity: 6\n" +
                "    Intelligence: 1\n" +
                "Damage: 1.0\n" +
                "Class: Swashbuckler\n";

        // Act
        String actualOutput = swashbuckler.displayHero();

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
