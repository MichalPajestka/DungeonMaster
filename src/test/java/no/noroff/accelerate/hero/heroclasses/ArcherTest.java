package no.noroff.accelerate.hero.heroclasses;

import no.noroff.accelerate.exceptions.weapons.InvalidWeaponException;
import no.noroff.accelerate.hero.HeroAttribute;
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
        Archer archer = new Archer(archerName);

        // Act
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
        Archer archer = new Archer(archerName);;

        //Act
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
        final int weaponDamage = 10;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, WeaponType.BOW, weaponDamage);

        try {
            archer.equipWeapon(weapon);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        //Assert
        Weapon expectedEquippedWeapon = weapon;
        Weapon actualEquippedWeapon = archer.getEquippedWeapon();

        assertEquals(expectedEquippedWeapon, actualEquippedWeapon);
    }

    @Test
    public void testCheckIfWhenArcherIsEquippingAWeaponWithValidTypeButTooHighLevelThrowsCorrectException() {
        //Arrange
        final String archerName = "Sniperman";
        final String weaponName = "Barret .50 cal";
        final int requiredLevel = 30;
        final int weaponDamage = 500;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, WeaponType.BOW, weaponDamage);

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
        final int weaponDamage = 1000;

        //Act

        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, WeaponType.DAGGER, weaponDamage);

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
        final int weaponDamage = 35;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, WeaponType.BOW, weaponDamage);

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
        final int weaponDamage = 35;
        final int weapon2Damage = 90;

        //Act
        Archer archer = new Archer(archerName);
        Weapon weapon = new Weapon(weaponName, requiredLevel, WeaponType.BOW, weaponDamage);
        Weapon weapon2 = new Weapon(weapon2Name, requiredLevel, WeaponType.BOW, weapon2Damage);

        archer.equipWeapon(weapon);
        archer.equipWeapon(weapon2);

        final double calculatedDamage = archer.calcDamage();
        final double expectedDamage = weapon2Damage * (1 + (double) archer.getLevelAttributes().getDexterity() / 100);

        assertEquals(expectedDamage, calculatedDamage);
    }

}