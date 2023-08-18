package no.noroff.accelerate.hero.armor;

import no.noroff.accelerate.items.Slot;
import no.noroff.accelerate.items.armor.Armor;
import no.noroff.accelerate.items.armor.ArmorAttribute;
import no.noroff.accelerate.items.armor.ArmorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {
    @Test
    public void testCreateArmorHeadPieceWithCorrectNameCorrectRequiredLevelCorrectSlotCorrectArmorTypeCorrectAttributesAndCorrectSlot() {
        //Arrange
        final int requiredLevel = 1;
        final String armorName = "Peasant helmet";
        ArmorAttribute armorAttribute = new ArmorAttribute(3, 5, 0);

        //Act
        Armor helmet = new Armor(armorName, requiredLevel, ArmorType.CLOTH, armorAttribute, Slot.HEAD);

        //Assert
        assertEquals("Peasant helmet", helmet.getName());
        assertEquals(1, helmet.getRequiredLevel());
        assertEquals(ArmorType.CLOTH, helmet.getArmorType());
        assertEquals(3, helmet.getArmorAttribute().getStrengthBonus());
        assertEquals(5, helmet.getArmorAttribute().getDexterityBonus());
        assertEquals(0, helmet.getArmorAttribute().getIntelligenceBonus());
        assertEquals(Slot.HEAD, helmet.getSlot());
    }
}