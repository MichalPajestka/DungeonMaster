package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.HeroAttribute;

public class Archer extends Hero{
    public Archer(String name) {
        super(name);
        this.levelAttributes = new HeroAttribute(1, 7, 1);
    }

    @Override
    public int calcDamage() {

    }

    @Override
    public int totalAttributes() {

    }
}
