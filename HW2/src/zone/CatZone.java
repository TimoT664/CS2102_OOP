package zone;

import animals.Petable;

public class CatZone implements Zoneable{

    public CatZone(){

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
