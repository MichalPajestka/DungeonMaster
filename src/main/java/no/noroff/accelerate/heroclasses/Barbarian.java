package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.HeroAttribute;

public class Barbarian extends Hero {
    public Barbarian(String name) {
        super(name);
        this.levelAttributes = new HeroAttribute(5,2,1);
    }

    @Override
    public int calcDamage() {

    }

    @Override
    public int totalAttributes() {

    }
}
