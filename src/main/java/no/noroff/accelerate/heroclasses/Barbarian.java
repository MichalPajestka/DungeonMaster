package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.items.weapons.Weapon;

public class Barbarian extends Hero {
    public Barbarian(String name) {
        super(name);
        this.levelAttributes = new HeroAttribute(5,2,1);
    }

    @Override
    public int calcDamage(Weapon weapon) {


    }

    @Override
    public int totalAttributes() {

    }
}
