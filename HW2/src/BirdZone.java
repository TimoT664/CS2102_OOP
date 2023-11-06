import java.util.LinkedList;

/**
 * Represents a zone specifically for birds.
 */
public class BirdZone extends AbstractZone {

    public int seeds;
    //public LinkedList<Bird> birds;
    /**
     * Constructor for the BirdZone.
     * @param birds The list of birds in the zone.
     */
    public BirdZone(LinkedList<Bird> birds) {
        super(birds);
    }

    @Override
    protected int humanYearMultiplier() {
        return 9;
    }

    @Override
    protected String zoneLabel() {
        return "Bird";
    }

    @Override
    public String closestPet(int x, int y) {
        if (pets.isEmpty()) return "No Pets Found";

        boolean withPerch = true; // Add to Method to decide??
        Petable closest = pets.getFirst();
        double minDistance = Double.MAX_VALUE;

        for (Petable pet : pets) {
            Bird currentBird = (Bird) pet;
            double distance = pet.getLocation().distanceTo(x, y);
            if (distance < minDistance && currentBird.wingsClipped) {
                minDistance = distance;
                closest = pet;
            }
        }
        return closest.getName();
    }

    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if ("seeds".equals(foodName) && foodAmt >= 0) {
            this.seeds += foodAmt;
        }
        return this;
    }

    @Override
    public Zoneable feedZone() {
        for (Petable pet : pets) {
            int foodNeeded = pet.foodNeeded("seeds");
            if (seeds >= foodNeeded) {
                seeds -= foodNeeded;
            } else {
                seeds = 0;
            }
        }
        return this;
    }
    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s", zoneLabel(), seeds, "seeds");
    }
}
