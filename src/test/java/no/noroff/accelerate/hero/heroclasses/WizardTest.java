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

class WizardTest {

    @Test
    public void testIfWizardIsCreatedWithCorrectName() {
        // Arrange
        final String expectedName = "Doctor Strange";

        // Act
        Wizard wizard = new Wizard(expectedName);
        final String actualName = wizard.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testIfWizardIsCreatedWithCorrectLevelWhichShouldBeOne() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final int expectedLevel = 1;

        // Act
        Wizard wizard = new Wizard(wizardName);
        final int actualLevel = wizard.getLevel();

        // Assert
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testIfWizardLevelIncreases() {
        // Arrange
        final int expectedLevel = 2;
        final String wizardName = "Doctor Strange";

        // Act
        Wizard wizard = new Wizard(wizardName);
        wizard.levelUp();
        final int actualLevel = wizard.getLevel();

        // Assert
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testCheckIfWizardsDefaultAttributesAreCorrectWhenCreated() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(1, 1, 8);
        final String wizardName = "Doctor Strange";

        // Act
        Wizard wizard = new Wizard(wizardName);
        HeroAttribute actualAttributes = wizard.getLevelAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfWizardAttributesIncreasesByTheRightAmountWhenLevellingUp() {
        // Arrange
        final HeroAttribute expectedAttributes = new HeroAttribute(2, 2, 13);
        final String wizardName = "Doctor Strange";

        // Act
        Wizard wizard = new Wizard(wizardName);
        HeroAttribute actualAttributes = wizard.getLevelAttributes();

        wizard.levelUp();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCheckIfWizardCanEquipWeaponWithValidWeaponTypeAndLevel() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String weaponName = "Elder Wand";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.WAND;
        final int weaponDamage = 20;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Weapon expectedEquippedWeapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        try {
            wizard.equipWeapon(expectedEquippedWeapon);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        // Assert
        Weapon actualEquippedWeapon = wizard.getEquippedWeapon();

        assertEquals(expectedEquippedWeapon, actualEquippedWeapon);
    }

    @Test
    public void testCheckIfWhenWizardIsEquippingAWeaponWithValidTypeButTooHighLevelThrowsCorrectException() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String weaponName = "Infinity Gauntlet";
        final int requiredLevel = 30;
        final WeaponType weaponType = WeaponType.STAFF;
        final int weaponDamage = 500;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Hero level is too low to equip this weapon");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> wizard.equipWeapon(weapon));

        // Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenWizardIsEquippingAWeaponWithInvalidTypeButValidLevelThrowsCorrectException() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String weaponName = "Dagger of Shadows";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.DAGGER;
        final int weaponDamage = 1000;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        InvalidWeaponException expectedException = new InvalidWeaponException("Wizards can only use staves or wands");
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> wizard.equipWeapon(weapon));

        // Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateWizardDamageWithNoWeaponEquipped() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final int expectedDamage = 1;

        // Act
        Wizard wizard = new Wizard(wizardName);
        final double actualDamage = wizard.calcDamage();

        // Assert
        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    public void testCalculateIfWizardDamageIsCalculatedProperlyWhenAWeaponIsEquipped() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String weaponName = "Staff of Power";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.STAFF;
        final int weaponDamage = 40;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);

        wizard.equipWeapon(weapon);

        final double actualDamage = wizard.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) wizard.getLevelAttributes().getIntelligence() / 100);

        // Assert
        assertEquals(expectedDamage, actualDamage, 0.01);
    }

    @Test
    public void testCalculateIfWizardDamageIsCalculatedProperlyWhenAWeaponIsEquippedAndADifferentOneIsEquippedAgain() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String weaponName = "Staff of Power";
        final String weapon2Name = "Arcane Crystal Wand";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.STAFF;
        final WeaponType weapon2Type = WeaponType.WAND;
        final int weaponDamage = 40;
        final int weapon2Damage = 60;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Weapon weapon2 = new Weapon(weapon2Name, requiredLevel, weapon2Type, weapon2Damage);

        wizard.equipWeapon(weapon);
        wizard.equipWeapon(weapon2);

        final double calculatedDamage = wizard.calcDamage();
        final double expectedDamage = weapon2Damage * (1 + (double) wizard.getLevelAttributes().getIntelligence() / 100);

        // Assert
        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCalculateIfWizardDamageIsCalculatedProperlyWhenAWeaponAndArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String weaponName = "Staff of Power";
        final int requiredLevel = 1;
        final WeaponType weaponType = WeaponType.STAFF;
        final int weaponDamage = 40;

        final String armorName = "Wizard's Robes";
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(5, 10, 15);
        final Slot armorSlot = Slot.BODY;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, weaponType, weaponDamage);
        Armor robe = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        wizard.equipWeapon(weapon);
        wizard.equipArmor(robe);

        // Calculate total attributes after equipping armor
        HeroAttribute totalAttributes = wizard.calcTotalAttributes();

        // Calculate expected damage using totalAttributes and weaponDamage
        final double calculatedDamage = wizard.calcDamage();
        final double expectedDamage = weaponDamage * (1 + (double) totalAttributes.getIntelligence() / 100);

        // Assert
        assertEquals(expectedDamage, calculatedDamage, 0.01);
    }

    @Test
    public void testCheckIfWizardCanEquipArmorWithValidArmorTypeAndLevel() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String armorName = "Aetherial Cloak";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(2, 5, 10);
        final Slot armorSlot = Slot.BODY;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Armor expectedEquippedArmor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        try {
            wizard.equipArmor(expectedEquippedArmor);
        } catch (InvalidArmorException e) {
            throw new RuntimeException(e);
        }

        // Assert
        Armor actualEquippedArmor = wizard.getEquippedArmor(armorSlot);

        assertEquals(expectedEquippedArmor, actualEquippedArmor);
    }

    @Test
    public void testCheckIfWhenWizardIsEquippingArmorWithValidTypeButTooHighLevelThrowsCorrectException() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String armorName = "Archmage's Robes";
        final int requiredLevel = 30;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(10, 20, 30);
        final Slot armorSlot = Slot.BODY;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Hero level is too low to equip this armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> wizard.equipArmor(armor));

        // Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCheckIfWhenWizardIsEquippingArmorWithInvalidTypeValidLevelThrowsCorrectException() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String armorName = "Plate Armor";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.PLATE;
        final ArmorAttribute armorAttributes = new ArmorAttribute(15, 10, 5);
        final Slot armorSlot = Slot.BODY;

        // Act
        Wizard wizard = new Wizard(wizardName);
        Armor armor = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        InvalidArmorException expectedException = new InvalidArmorException("Wizards can only wear cloth armor");
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> wizard.equipArmor(armor));

        // Assert
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testCalculateWizardAttributesWhenNoArmorIsEquipped() {
        // Arrange
        final String wizardName = "Doctor Strange";
        final HeroAttribute expectedAttributes = new HeroAttribute(1, 1, 8);

        // Act
        Wizard wizard = new Wizard(wizardName);
        HeroAttribute actualAttributes = wizard.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateWizardAttributesWhenOnePieceOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String armorName = "Silk Robes";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(2, 5, 10);
        final Slot armorSlot = Slot.BODY;

        // Act
        Wizard wizard = new Wizard(wizardName);

        final HeroAttribute expectedAttributes = new HeroAttribute(3, 6, 18);
        Armor robe = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);

        wizard.equipArmor(robe);
        HeroAttribute actualAttributes = wizard.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateWizardAttributesWhenTwoPiecesOfArmorIsEquipped() throws InvalidArmorException {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String armorName = "Silk Robes";
        final String armor2Name = "Silk Leggings";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorType armorType2 = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(2, 5, 10);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(6, 2, 5);
        final Slot armorSlot = Slot.BODY;
        final Slot armor2Slot = Slot.LEGS;

        // Act
        Wizard wizard = new Wizard(wizardName);

        final HeroAttribute expectedAttributes = new HeroAttribute(9, 8, 23);
        Armor robe = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor leggings = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        wizard.equipArmor(robe);
        wizard.equipArmor(leggings);
        HeroAttribute actualAttributes = wizard.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testCalculateWizardAttributesWhenAPieceOfArmorIsEquippedAndThenANewOneIsEquipped() throws InvalidArmorException {
        // Arrange
        final String wizardName = "Doctor Strange";
        final String armorName = "Silk Robe";
        final String armor2Name = "Master Robe";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorType armorType2 = ArmorType.CLOTH;
        final ArmorAttribute armorAttributes = new ArmorAttribute(2, 5, 10);
        final ArmorAttribute armor2Attributes = new ArmorAttribute(10, 15, 20);
        final Slot armorSlot = Slot.BODY;
        final Slot armor2Slot = Slot.BODY;

        // Act
        Wizard wizard = new Wizard(wizardName);

        final HeroAttribute expectedAttributes = new HeroAttribute(11, 16, 28);
        Armor robe = new Armor(armorName, requiredLevel, armorType, armorAttributes, armorSlot);
        Armor mantle = new Armor(armor2Name, requiredLevel, armorType2, armor2Attributes, armor2Slot);

        wizard.equipArmor(robe);

        wizard.equipArmor(mantle);
        HeroAttribute actualAttributes = wizard.calcTotalAttributes();

        // Assert
        assertEquals(expectedAttributes, actualAttributes);
    }
}
