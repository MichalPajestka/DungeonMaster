    package no.noroff.accelerate.hero.heroclasses;

    import no.noroff.accelerate.exceptions.InvalidArmorException;
    import no.noroff.accelerate.exceptions.armor.ArcherInvalidArmorException;
    import no.noroff.accelerate.exceptions.weapons.ArcherInvalidWeaponException;
    import no.noroff.accelerate.hero.Hero;
    import no.noroff.accelerate.hero.HeroAttribute;
    import no.noroff.accelerate.hero.HeroClass;
    import no.noroff.accelerate.items.Slot;
    import no.noroff.accelerate.items.armor.Armor;
    import no.noroff.accelerate.items.armor.ArmorType;
    import no.noroff.accelerate.items.weapons.Weapon;
    import no.noroff.accelerate.items.weapons.WeaponType;

    import java.util.Arrays;

    public class Archer extends Hero {
        public Archer(String name) {
            super(name, HeroClass.ARCHER);
            this.levelAttributes = new HeroAttribute(1, 7, 1);
            this.validWeaponTypes = Arrays.asList(WeaponType.BOW);
            this.validArmorTypes = Arrays.asList(ArmorType.LEATHER, ArmorType.MAIL);
        }

        @Override
        public HeroAttribute getAttributeLevelUp() {
            return new HeroAttribute(1, 5, 1);
        }

        @Override
        protected void levelUpAttributes() {
            levelAttributes.addAttributes(new HeroAttribute(1, 5, 1));
        }

        @Override
        public double calcDamage() {
            Weapon equippedWeapon = getEquippedWeapon();

            if (equippedWeapon != null) {
                int weaponDamage = equippedWeapon.getWeaponDamage();
                double damageAttribute = calcTotalAttributes().getDexterity() / 100.0;
                double damage = weaponDamage * (1 + damageAttribute);
                return Math.round(damage * 100.0) / 100.0;
            } else {
                return 1.0;
            }
        }

        @Override
        public void equipWeapon(Weapon weapon) throws ArcherInvalidWeaponException {
            if (level < weapon.getRequiredLevel()) {
                throw new ArcherInvalidWeaponException("Hero level is too low to equip this weapon");
            }

            if (!WeaponType.BOW.equals(weapon.getWeaponType())) {
                throw new ArcherInvalidWeaponException("Archers can only use bows");
            }

            equipment.equipItem(weapon);
        }

        public void equipArmor(Armor armor) throws InvalidArmorException {
            if (level < armor.getRequiredLevel()) {
                throw new InvalidArmorException("Hero level is too low to equip this armor");
            }

            ArmorType armorType = armor.getArmorType();
            Slot armorSlot = armor.getSlot();

            if (!(ArmorType.LEATHER.equals(armorType) || ArmorType.MAIL.equals(armorType))) {
                throw new ArcherInvalidArmorException("Archers can only wear leather or mail armor");
            }

            if (Slot.HEAD.equals(armorSlot) || Slot.BODY.equals(armorSlot) || Slot.LEGS.equals(armorSlot)) {
                equipment.equipItem(armor);
            } else {
                throw new ArcherInvalidArmorException("Archers can only wear armor on the head, body, or leg slots");
            }
        }


        public HeroAttribute calcTotalAttributes() {
            HeroAttribute totalAttributes = new HeroAttribute(levelAttributes.getStrength(), levelAttributes.getDexterity(), levelAttributes.getIntelligence());
            HeroAttribute totalArmorAttributes = equipment.calculateTotalArmorAttributes();
            totalAttributes.addAttributes(totalArmorAttributes);
            return totalAttributes;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public HeroAttribute getLevelAttributes() {
            return levelAttributes;
        }

    }
