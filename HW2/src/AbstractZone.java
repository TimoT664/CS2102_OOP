import java.util.LinkedList;

/**
 * An abstract class representing a zone in the pet rescue.
 * This class provides common functionality for all zones and defines abstract methods
 * that must be implemented by subclasses to handle specific zone behaviors.
 */
public abstract class AbstractZone implements Zoneable {
    protected LinkedList<? extends Petable> pets; // List of pets in the zone

    /**
     * Constructor for the AbstractZone.
     *
     * @param pets The list of pets in the zone.
     */
    public AbstractZone(LinkedList<? extends Petable> pets) {
        this.pets = pets;
    }

    /**
     * Counts the number of pets in the zone.
     *
     * @return The total number of pets in the zone.
     */
    @Override
    public int petsInZone() {
        return pets.size();
    }

    /**
     * Finds the heaviest pet in the zone.
     *
     * @return The heaviest pet, or null if the zone is empty.
     */
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

    /**
     * Converts the age of the pet with the given name to human years.
     *
     * @param petName The name of the pet whose age is to be converted.
     * @return The age of the pet in human years, or -1 if the pet is not found.
     */
    @Override
    public int inHumanYears(String petName) {
        if (petName == "" || petName == null)
        {
            throw new IllegalArgumentException();
        }
        for (Petable pet : pets) {
            if (pet.getName().equals(petName)) {
                return pet.getAge() * humanYearMultiplier();
            }
        }
        return -1;
    }

    /**
     * Retrieves the pet with the given name from the zone.
     *
     * @param petName The name of the pet to retrieve.
     * @return The pet with the specified name, or null if not found.
     */
    @Override
    public Petable getPet(String petName) {
        for (Petable pet : pets) {
            if (pet.getName().equals(petName)) {
                return pet;
            }
        }
        return null;
    }

    /**
     * Finds the name of the pet closest to the given coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The name of the closest pet, or "No Pets Found" if the zone is empty.
     */
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
     * This method must be implemented by subclasses to provide the correct conversion
     * based on the type of pets in the zone.
     *
     * @return The multiplier for converting pet age to human years.
     */
    protected abstract int humanYearMultiplier();

    /**
     * Returns the label for the zone.
     * This method must be implemented by subclasses to provide the label specific to the zone type.
     *
     * @return The label for the zone.
     */
    protected abstract String zoneLabel();

    // Uncomment and implement these methods if you need functionality related to pet food stock and feeding.
    /*
    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        // Implementation here
    }

    @Override
    public Zoneable feedZone() {
        // Implementation here
    }

    @Override
    public String getPantryLabel() {
        // Implementation here
    }
    */
}
