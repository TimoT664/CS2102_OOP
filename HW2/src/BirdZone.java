import java.util.LinkedList;

/**
 * Represents a zone specifically for birds within a pet rescue environment.
 * This class extends the AbstractZone class and provides implementations
 * for the abstract methods defined there.
 */
public class BirdZone extends AbstractZone {

    private int seeds; // The amount of seeds available in the BirdZone for feeding

    /**
     * Constructor for the BirdZone.
     *
     * @param birds The list of birds in the zone.
     */
    public BirdZone(LinkedList<Bird> birds) {
        super(birds);
    }

    /**
     * Returns the multiplier to convert bird age to human years.
     *
     * @return The multiplier for converting bird age to human years, which is 9.
     */
    @Override
    protected int humanYearMultiplier() {
        return 9;
    }

    /**
     * Returns the label for the bird zone.
     *
     * @return The label "Bird" for the bird zone.
     */
    @Override
    protected String zoneLabel() {
        return "Bird";
    }

    /**
     * Finds the name of the closest bird to the given coordinates that has clipped wings.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The name of the closest bird with clipped wings, or "No Pets Found" if no such bird is in the zone.
     */
    @Override
    public String closestPet(int x, int y) {
        if (pets.isEmpty()) return "No Pets Found";

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

    /**
     * Restocks the bird zone with the specified amount of food.
     *
     * @param foodName The type of food to restock, which should be "seeds" for the bird zone.
     * @param foodAmt  The amount of food to add to the stock.
     * @return The current instance of the zone after restocking.
     */
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if ("seeds".equals(foodName) && foodAmt >= 0) {
            this.seeds += foodAmt;
        }
        return this;
    }

    /**
     * Feeds all birds in the zone, reducing the seed stock accordingly.
     *
     * @return The current instance of the zone after feeding.
     */
    @Override
    public Zoneable feedZone() {
        for (Petable pet : pets) {
            int foodNeeded = pet.foodNeeded("seeds");
            if (seeds >= foodNeeded) {
                seeds -= foodNeeded;
                pet.eats("seeds"); // Ensure the bird's weight is updated after eating
            } else {
                seeds = 0; // No more seeds left to feed
                break; // Exit the loop if there are no seeds left
            }
        }
        return this;
    }

    /**
     * Returns a label for the pantry indicating the amount of seeds available.
     *
     * @return A string label indicating the amount of seeds in the bird zone.
     */
    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s", zoneLabel(), seeds, "seeds");
    }
}
