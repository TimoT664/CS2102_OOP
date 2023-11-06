/**
 * Represents a Chinchilla pet with specific attributes and behaviors.
 * This class extends the AbstractPet class and includes additional properties
 * unique to a Chinchilla.
 */
public class Chinchilla extends AbstractPet {
    private int dustBathResidueInOz; // The weight of dust bath residue on the Chinchilla in ounces

    /**
     * Constructs a new Chinchilla instance with the specified attributes.
     *
     * @param name                The name of the Chinchilla.
     * @param age                 The age of the Chinchilla.
     * @param weightInOz          The weight of the Chinchilla in ounces.
     * @param location            The 2D coordinate location of the Chinchilla.
     * @param dustBathResidueInOz The weight of dust bath residue on the Chinchilla in ounces.
     */
    public Chinchilla(String name, int age, int weightInOz, Coord2D location, int dustBathResidueInOz) {
        super(name, age, weightInOz, location);
        this.dustBathResidueInOz = dustBathResidueInOz;
    }

    /**
     * Gets the total weight of the Chinchilla in ounces, including the dust bath residue.
     *
     * @return The total weight of the Chinchilla in ounces.
     */
    @Override
    public int getWeightInOz() {
        return super.getWeightInOz() + this.dustBathResidueInOz;
    }

    /**
     * Feeds the Chinchilla with the specified type of food, increasing its weight accordingly.
     *
     * @param food The type of food the Chinchilla eats.
     * @return The weight gained by the Chinchilla after eating, which is currently not used.
     */
    @Override
    public int eats(String food) {
        if ("pellets".equals(food)) {
            this.weightInOz += 3; // Chinchilla gains 3 ounces when eating pellets
        } else if ("hay".equals(food)) {
            this.weightInOz += 1; // Chinchilla gains 1 ounce when eating hay
        }
        return 0; // The method currently does not utilize the return value
    }

    /**
     * Calculates the age of the Chinchilla in human years.
     *
     * @return The Chinchilla's age in human years, which is currently not implemented.
     */
    @Override
    public int ageInHumanYears() {
        // The conversion logic is not implemented yet
        return 0;
    }

    /**
     * Determines the amount of food the Chinchilla needs based on the type of food.
     *
     * @param food The type of food to check.
     * @return The amount of food needed by the Chinchilla.
     */
    @Override
    public int foodNeeded(String food) {
        if ("pellets".equals(food)) {
            return 3; // Chinchilla needs 3 units of pellets
        } else if ("hay".equals(food)) {
            return 1; // Chinchilla needs 1 unit of hay
        }
        return 0; // If the food type is not recognized, no food is needed
    }

    /**
     * Gets the location of the Chinchilla.
     *
     * @return The 2D coordinate location of the Chinchilla.
     */
    @Override
    public Coord2D getLocation() {
        return this.location;
    }

    // Additional chinchilla-specific methods can be added here
}
