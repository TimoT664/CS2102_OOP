package zone;

import animals.Bird;
import animals.Petable;

import java.util.LinkedList;

public class BirdZone implements Zoneable{

    public LinkedList<Bird> birds;

    public BirdZone(LinkedList<Bird> birds){

    }

    @Override
    public int petsInZone() {
        return 0;
    }

    @Override
    public Petable heaviestPet() {
        return null;
    }

    @Override
    public int inHumanYears(String petName) {
        return 0;
    }

    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        return null;
    }

    @Override
    public Zoneable feedZone() {
        return null;
    }

    @Override
    public Petable getPet(String petName) {
        return null;
    }

    @Override
    public String getPantryLabel() {
        return null;
    }

    @Override
    public String closestPet(int x, int y) {
        return null;
    }
}
