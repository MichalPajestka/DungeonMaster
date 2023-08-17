package no.noroff.accelerate.exceptions.weapons;

public class InvalidWeaponException extends RuntimeException {
    public InvalidWeaponException(String message) {
        super(message);
    }
}