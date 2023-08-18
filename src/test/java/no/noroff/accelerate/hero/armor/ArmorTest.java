package no.noroff.accelerate.hero.armor;

import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorAttribute;
import no.noroff.accelerate.items.armor.ArmorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {
    @Test
    public void testIfArmorIsCreatedWithTheCorrectName(){
        //Arrange
        final String expectedName = "Peasant helmet";
        final int requiredLevel = 1;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute armorAttribute = new ArmorAttribute(1, 2, 0);
        final Slot armorSlot = Slot.HEAD;


        //Act
        Armor helmet = new Armor(expectedName, requiredLevel, armorType, armorAttribute, armorSlot);
        final String actualName = helmet.getName();

        //Assert
        assertEquals(expectedName, actualName);

    }

    @Test
    public void testIfArmorIsCreatedWithTheCorrectRequiredLevel(){
        //Arrange
        final String armorName = "Heavy Knight Chestplate";
        final int expectedLevel = 10;
        final ArmorType armorType = ArmorType.PLATE;
        final ArmorAttribute armorAttribute = new ArmorAttribute(10, 0, 2);
        final Slot armorSlot = Slot.BODY;

        //Act
        Armor chestplate = new Armor(armorName, expectedLevel, armorType, armorAttribute, armorSlot);
        final int actualLevel = chestplate.getRequiredLevel();

        //Assert
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testIfArmorIsCreatedWithTheCorrectArmorType(){
        //Arrange
        final String armorName = "Rare Leather Leggings";
        final int requiredLevel = 15;
        final ArmorType armorType = ArmorType.LEATHER;
        final ArmorAttribute armorAttribute = new ArmorAttribute(3, 6, 1);
        final Slot armorSlot = Slot.LEGS;

        //Act
        Armor leggings = new Armor(armorName, requiredLevel, armorType, armorAttribute, armorSlot);

        final String expectedType = String.valueOf(ArmorType.LEATHER);
        final String actualType = String.valueOf(leggings.getArmorType());

        //Assert
        assertEquals(expectedType, actualType);
    }

    @Test
    public void testIfArmorIsCreatedWithTheCorrectArmorAttributes(){
        //Arrange
        final String armorName = "Grand Wizard Robe";
        final int requiredLevel = 60;
        final ArmorType armorType = ArmorType.CLOTH;
        final ArmorAttribute expectedAttributes = new ArmorAttribute(12, 8, 30);
        final Slot armorSlot = Slot.BODY;

        //Act
        Armor robe = new Armor(armorName, requiredLevel, armorType, expectedAttributes, armorSlot);
        final ArmorAttribute actualAttributes = robe.getArmorAttribute();

        //Assert
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testIfArmorIsCreatedWithTheCorrectAssignedSlot(){
        //Arrange
        final String armorName = "Chain-mail Helmet";
        final int requiredLevel = 3;
        final ArmorType armorType = ArmorType.MAIL;
        final ArmorAttribute armorAttributes = new ArmorAttribute(4, 1, 2);
        final Slot expectedSlot = Slot.HEAD;

        //Act
        Armor robe = new Armor(armorName, requiredLevel, armorType, armorAttributes, expectedSlot);
        final Slot actualSlot = robe.getSlot();

        //Assert
        assertEquals(expectedSlot, actualSlot);
    }
}