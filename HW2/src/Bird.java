/**
 * Represents a bird, which is a specific type of pet.
 * This class extends the AbstractPet class and provides implementations
 * for the abstract methods defined there.
 */
public class Bird extends AbstractPet {
    public boolean wingsClipped; // Indicates whether the bird's wings are clipped

    /**
     * Constructs a new Bird instance with the specified details.
     *
     * @param name         The name of the bird.
     * @param age          The age of the bird in years.
     * @param weightInOz   The weight of the bird in ounces.
     * @param location     The 2D coordinate location of the bird.
     * @param wingsClipped A boolean indicating whether the bird's wings are clipped.
     */
    public Bird(String name, int age, int weightInOz, Coord2D location, boolean wingsClipped) {
        super(name, age, weightInOz, location);
        this.wingsClipped = wingsClipped;
    }

    /**
     * Feeds the bird with the specified type of food, affecting its weight.
     *
     * @param food The type of food the bird will eat.
     * @return The amount of weight gained in ounces after eating, which is 1 if seeds are eaten.
     */
    @Override
    public int eats(String food) {
        if ("seeds".equals(food)) {
            this.weightInOz += 1; // Bird eats 1 unit of seeds
            return 1; // Return the weight gained
        }
        // Additional food types can be added here if needed
        return 0;
    }

    /**
     * Calculates the age of the bird in terms equivalent to human years.
     *
     * @return The age of the bird converted to human years.
     */
    @Override
    public int ageInHumanYears() {
        return this.age * 9; // The multiplier for bird age to human years
    }

    /**
     * Determines the amount of food the bird needs.
     *
     * @param food The type of food to check for the required amount.
     * @return The amount of food needed, which is 1 unit if seeds are the food type.
     */
    @Override
    public int foodNeeded(String food) {
        if ("seeds".equals(food)) {
            return 1; // Bird eats 1 unit of seeds
        }
        return 0;
    }

    /**
     * Retrieves the current location of the bird.
     *
     * @return The 2D coordinate representing the bird's location.
     */
    @Override
    public Coord2D getLocation() {
        return this.location;
    }

    // Other bird-specific methods can be added here
}
