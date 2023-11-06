import java.util.LinkedList;

/**
 * An abstract class representing a zone in the pet rescue.
 * This class provides common functionality for all zones.
 */
public abstract class AbstractZone implements Zoneable {
    protected LinkedList<? extends Petable> pets;
    //protected String foodType;
    //protected int foodAmount;

    /**
     * Constructor for the AbstractZone.
     * @param pets The list of pets in the zone.
     */
    public AbstractZone(LinkedList<? extends Petable> pets) {
        this.pets = pets;
    }

    @Override
    public int petsInZone() {
        return pets.size();
    }

    @Override
    public Petable heaviestPet() {
        if (pets.isEmpty()) return null;

        Petable heaviest = pets.getFirst();
        for (Petable pet : pets) {
            if (pet.getWeightInOz() > heaviest.getWeightInOz()) {
                heaviest = pet;
            }
        }
        return heaviest;
    }

    @Override
    public int inHumanYears(String petName) {
        for (Petable pet : pets) {
            if (pet.getName().equals(petName)) {
                return pet.getAge() * humanYearMultiplier();
            }
        }
        return -1;
    }

    @Override
    public Petable getPet(String petName) {
        for (Petable pet : pets) {
            if (pet.getName().equals(petName)) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public String closestPet(int x, int y) {
        if (pets.isEmpty()) return "No Pets Found";

        Petable closest = pets.getFirst();
        double minDistance = Double.MAX_VALUE;

        for (Petable pet : pets) {
             double distance = pet.getLocation().distanceTo(x, y);
             if (distance < minDistance) {
                 minDistance = distance;
                 closest = pet;
             }
        }
        return closest.getName();
    }

    /**
     * Returns the multiplier to convert pet age to human years.
     * @return the multiplier for the specific pet type.
     */
    protected abstract int humanYearMultiplier();

    /**
     * Returns the label for the zone.
     * @return the label for the specific zone type.
     */
    protected abstract String zoneLabel();

           /* @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if (this.foodType.equals(foodName) && foodAmt >= 0) {
            this.foodAmount += foodAmt;
        }
        return this;
    }*/


/*    @Override
    public Zoneable feedZone() {
        for (Petable pet : pets) {
             int foodNeeded = pet.foodNeeded(this.foodType);
             if (foodAmount >= foodNeeded) {
                 foodAmount -= foodNeeded;
             } else {
                 foodAmount = 0;
             }
        }
        return this;
    }*/
    /*
    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s", zoneLabel(), foodAmount, foodType);
    }*/
}

