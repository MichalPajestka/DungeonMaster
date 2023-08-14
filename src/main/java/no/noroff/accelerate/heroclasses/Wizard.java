package no.noroff.accelerate.heroclasses;

import no.noroff.accelerate.HeroAttribute;

public class Wizard extends Hero{
    public Wizard(String name) {
        super(name);
        this.levelAttributes = new HeroAttribute(1, 1, 8);
    }

    @Override
    public int calcDamage() {

    }

    @Override
    public int totalAttributes() {

    }
}
