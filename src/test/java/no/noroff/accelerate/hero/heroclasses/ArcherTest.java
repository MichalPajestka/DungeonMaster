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

    private void assertHeroAttributesEqual(HeroAttribute expected, HeroAttribute actual) {
        assertEquals(expected.getStrength(), actual.getStrength());
        assertEquals(expected.getDexterity(), actual.getDexterity());
        assertEquals(expected.getIntelligence(), actual.getIntelligence());
    }
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
    public void testIfArchersDefaultStrengthAttributeIsCorrectWhenCreated(){
        //Arrange
        final int expectedAttribute = 1;
        final String archerName = "Sniperman";

        //Act
        Archer archer = new Archer(archerName);
        final int actualStrengthAttribute = archer.getLevelAttributes().getStrength();


        //Assert
        assertEquals(expectedAttribute, actualStrengthAttribute);
    }

    @Test
    public void testIfArchersDefaultDexterityAttributeIsCorrectWhenCreated(){
        //Arrange
        final int expectedAttribute = 7;
        final String archerName = "Sniperman";

        //Act
        Archer archer = new Archer(archerName);
        final int actualDexterityAttribute = archer.getLevelAttributes().getDexterity();

        //Assert
        assertEquals(expectedAttribute, actualDexterityAttribute);
    }

    @Test
    public void testIfArchersDefaultIntelligenceAttributeIsCorrectWhenCreated(){
        //Arrange
        final int expectedAttribute = 1;
        final String archerName = "Sniperman";

        //Act
        Archer archer = new Archer(archerName);
        final int actualIntelligenceAttribute = archer.getLevelAttributes().getIntelligence();

        //Assert
        assertEquals(expectedAttribute, actualIntelligenceAttribute);
    }

    @Test
    public void testCheckIfArcherStrengthAttributeIncreasesByTheRightAmountWhenLevellingUp() {
        //Arrange
        final int expectedAttribute = 2;
        final String archerName = "Sniperman";
        Archer archer = new Archer(archerName);

        //Act
        archer.levelUp();
        final int actualStrengthOnLvlUp = archer.getLevelAttributes().getStrength();

        //Assert
        assertEquals(expectedAttribute, actualStrengthOnLvlUp);
    }

    @Test
    public void testCheckIfArcherDexterityAttributeIncreasesByTheRightAmountWhenLevellingUp() {
        //Arrange
        final int expectedAttribute = 12;
        final String archerName = "Sniperman";
        Archer archer = new Archer(archerName);

        //Act
        archer.levelUp();
        final int actualDexterityOnLvlUp = archer.getLevelAttributes().getDexterity();

        //Assert
        assertEquals(expectedAttribute, actualDexterityOnLvlUp);
    }

    @Test
    public void testCheckIfArcherIntelligenceAttributeIncreasesByTheRightAmountWhenLevellingUp() {
        //Arrange
        final int expectedAttribute = 2;
        final String archerName = "Sniperman";

        //Act
        Archer archer = new Archer(archerName);
        archer.levelUp();
        final int actualIntelligenceOnLvlUp = archer.getLevelAttributes().getIntelligence();

        //Assert
        assertEquals(expectedAttribute, actualIntelligenceOnLvlUp);
    }

    @Test
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
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> {
            archer.equipWeapon(weapon);
        });

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
        InvalidWeaponException actualException = assertThrows(InvalidWeaponException.class, () -> {
            archer.equipWeapon(weapon);
        });

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
        assertEquals(expectedDamage, calculatedDamage);
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
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> {
            archer.equipArmor(armor);
        });

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
        InvalidArmorException actualException = assertThrows(InvalidArmorException.class, () -> {
            archer.equipArmor(armor);
        });

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

        int expectedStrength = expectedAttributes.getStrength() + archer.getAttributeLevelUp().getStrength();
        int expectedDexterity = expectedAttributes.getDexterity() + archer.getAttributeLevelUp().getDexterity();
        int expectedIntelligence = expectedAttributes.getIntelligence() + archer.getAttributeLevelUp().getIntelligence();
        HeroAttribute expectedTotalAttributes = new HeroAttribute(expectedStrength, expectedDexterity, expectedIntelligence);

        // Assert
        assertEquals(expectedTotalAttributes, actualAttributes);
    }

}