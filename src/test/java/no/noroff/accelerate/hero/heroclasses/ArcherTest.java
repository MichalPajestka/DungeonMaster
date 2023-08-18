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


import static org.junit.jupiter.api.Assertions.*;

class ArcherTest {

    @Test
    public void testIfArcherIsCreatedWithCorrectName() {
        // Arrange
        final String expectedName = "Sniperman";

        // Act
        Archer archer = new Archer(expectedName);
        final String actualName = archer.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testIfArcherIsCreatedWithCorrectLevelWhichShouldBeOne() {
        //Arrange
        final String archerName = "Sniperman";
        final int expectedLevel = 1;

        //Act
        Archer archer = new Archer(archerName);
        final int actualLevel = archer.getLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testIfArcherLevelIncreases() {
        //Arrange
        final int expectedLevel = 2;
        final String archerName = "Sniperman";

        // Act
        Archer archer = new Archer(archerName);
        archer.levelUp();
        final int actualLevel = archer.getLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);

    }

    @Test
    public void testIfArchersDefaultAttributesAreCorrectWhenCreated() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(1, 7, 1);
        final String archerName = "Sniperman";

        // Act
        Archer archer = new Archer(archerName);
        HeroAttribute actualAttributes = archer.getLevelAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfArcherAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(2, 12, 2);
        final String archerName = "Sniperman";

        // Act
        Archer archer = new Archer(archerName);
        HeroAttribute actualAttributes = archer.getLevelAttributes();

        archer.levelUp();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @ Test
    public void testCheckIfArcherCanEquipWeaponWithValidWeaponTypeAndLevel() {
        //Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Primitve bow";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.BOW;
        final int weaponDamage = 10;

        //Act
        Archer archer = new Archer(archerName);
        Weapon expectedEquippedWeapon = new Weapon(weaponName, requiredLevel,weaponType, weaponDamage);

        try {
            archer.equipWeapon(expectedEquippedWeapon);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Weapon actualEquippedWeapon = archer.getEquippedWeapon();

        assertEquals(expectedEquippedWeapon, actualEquippedWeapon);
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingAWeaponWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Barret .50 cal";
        final int requiredLevel = 30;
        final WeaponType weaponType = WeaponType.BOW;
        final int weaponDamage = 500;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Hero level is too low to equip this weapon");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> archer.equipWeapon(weapon));

        ////Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingAWeaponWithInvalidTypeButValidLevelThrowsCorrectException() {
        //Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Hidden Blade";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.DAGGER;
        final int weaponDamage = 1000;

        //Act

        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Archers can only use bows");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> archer.equipWeapon(weapon));

        //Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateArcherDamageWithNoWeaponEquipped() {
        //Arrange
        final String archerName = "Sniperman";
        final int expectedDamage = 1;

        //Act
        Archer archer = new Archer(archerName);
        final double actualDamage = archer.calcDamage();

        //Assert
        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void testCalculateIfArcherDamageIsCalculatedProperlyWhenAWeaponIsEquipped() {
        //Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Enchanted bow";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.BOW;
        final int weaponDamage = 35;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        archer.equipWeapon(weapon);

        final double actualDamage = archer.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) archer.getLevelAttributes().getDexterity() / 100);


        //Assert
        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void testCalculateIfArcherDamageIsCalculatedProperlyWhenAWeaponIsEquippedAndADifferentOneIsEquippedAgain() {
        //Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Enchanted bow";
        final String weapon2Name = "Master bow";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.BOW;
        final WeaponType weapon2Type = WeaponType.BOW;
        final int weaponDamage = 35;
        final int weapon2Damage = 90;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Weapon weapon2 = new Weapon(weapon2Name, requiredLevel, weapon2Type, weapon2Damage);

        archer.equipWeapon(weapon);
        archer.equipWeapon(weapon2);

        final double calculatedDamage = archer.calcDamage();
        final double expectedDamage = weapon2Damage * (1 + (double) archer.getLevelAttributes().getDexterity() / 100);

        //TODO
        //Fix this
        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCalculateIfArcherDamageIsCalculatedProperlyWhenAWeaponAndArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Enchanted bow";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.BOW;
        final int weaponDamage = 35;

        final String armorName = "Master hood";
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(20, 54, 25);
        final Slot armorSlot = Slot.HEAD;

        // Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Armor helmet = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        archer.equipWeapon(weapon);
        archer.equipArmor(helmet);

        // Calculate total attributes after equipping armor
        HeroAttribute totalAttributes = archer.calcTotalAttributes();

        // Calculate expected damage using totalAttributes and weaponDamage
        final double calculatedDamage = archer.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) totalAttributes.getDexterity() / 100);

        // Assert
        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }


    @Test
    public void testCheckIfArcherCanEquipArmorWithValidArmorTypeAndLevel() {
        //Arrange
        final String archerName = "Sniperman";
        final String armorName = "Green Hood";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 8, 5);
        final Slot armorSlot = Slot.HEAD;

        //Act
        Archer archer = new Archer(archerName);
        Armor expectedEquippedArmor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        try {
            archer.equipArmor(expectedEquippedArmor);
        } catch (InvalidArmorException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Armor actualEquippedArmor = archer.getEquippedArmor(armorSlot);

        assertEquals(expectedEquippedArmor, actualEquippedArmor);
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingArmorWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String archerName = "Sniperman";
        final String armorName = "Master Archer Hood";
        final int requiredLevel = 30;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 8, 5);
        final Slot armorSlot = Slot.HEAD;

        //Act
        Archer archer = new Archer(archerName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Hero level is too low to equip this armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> archer.equipArmor(armor));

        ////Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingArmorWithInvalidTypeValidLevelThrowsCorrectException() {
        //Arrange
        final String archerName = "Sniperman";
        final String armorName = "Common Wizard Hat";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(0, 1, 3);
        final Slot armorSlot = Slot.HEAD;

        //Act
        Archer archer = new Archer(archerName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Archers can only wear leather or mail armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> archer.equipArmor(armor));

        ////Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateArcherAttributesWhenNoArmorIsEquipped() {
        // Arrange
        final String archerName = "Sniperman";
        final HeroAttribute expectedAttributes = new HeroAttribute(1, 7, 1);

        // Act
        Archer archer = new Archer(archerName);
        HeroAttribute actualAttributes = archer.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateArcherAttributesWhenOnePieceOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String archerName = "Sniperman";
        final String armorName = "Leather Hood";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 8, 5);
        final Slot armorSlot = Slot.HEAD;

        // Act
        Archer archer = new Archer(archerName);

        final HeroAttribute expectedAttributes = new HeroAttribute(4, 15, 6);
        Armor helmet = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        archer.equipArmor(helmet);
        HeroAttribute actualAttributes = archer.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateArcherAttributesWhenTwoPiecesOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String archerName = "Sniperman";
        final String armorName = "Leather Hood";
        final String armor2Name = "Chain-mail chestplate";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorType armorType2 = ArmorType.MAIL;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 8, 5);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(6, 1, 3);
        final Slot armorSlot = Slot.HEAD;
        final Slot armor2Slot = Slot.BODY;

        // Act
        Archer archer = new Archer(archerName);

        final HeroAttribute expectedAttributes = new HeroAttribute(10, 16, 9);
        Armor helmet = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor chestplate = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        archer.equipArmor(helmet);
        archer.equipArmor(chestplate);
        HeroAttribute actualAttributes = archer.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateArcherAttributesWhenAPieceOfArmorIsEquippedAndThenANewOnIsEquipped() throws InvalidArmorException {
        // Arrange
        final String archerName = "Sniperman";
        final String armorName = "Leather Hood";
        final String armor2Name = "EPIC LEATHER HOOD ";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorType armorType2 = ArmorType.LEATHER;
        final ArmorAttribute armorAttributes = new ArmorAttribute(3, 8, 5);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(12, 25, 8);
        final Slot armorSlot = Slot.HEAD;
        final Slot armor2Slot = Slot.HEAD;

        // Act
        Archer archer = new Archer(archerName);

        final HeroAttribute expectedAttributes = new HeroAttribute(13, 32, 9);
        Armor helmet = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor helmet2 = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        archer.equipArmor(helmet);

        archer.equipArmor(helmet2);
        HeroAttribute actualAttributes = archer.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }


    @Test
    public void testDisplayArcherHero() {
        // Arrange
        String archerName = "Sniperman";
        Archer archer = new Archer(archerName);

        String expectedOutput = "Name: Sniperman\n" +
                "Level: 1\n" +
                "Attributes: \n" +
                "    Strength: 1\n" +
                "    Dexterity: 7\n" +
                "    Intelligence: 1\n" +
                "Damage: 1.0\n" +
                "Class: Archer\n";

        // Act
        String actualOutput = archer.displayHero();

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

}
