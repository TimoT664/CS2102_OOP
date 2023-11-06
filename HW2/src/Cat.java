/**
 * Represents a cat, which is a specific type of pet.
 * This class extends the AbstractPet class and provides implementations
 * for the abstract methods defined there.
 */
public class Cat extends AbstractPet {

    private boolean hasBeenPetToday; // Indicates whether the cat has been petted today

    /**
     * Constructs a new Cat instance with the specified details.
     *
     * @param name            The name of the cat.
     * @param age             The age of the cat in years.
     * @param weightInOz      The weight of the cat in ounces.
     * @param location        The 2D coordinate location of the cat.
     * @param hasBeenPetToday A boolean indicating whether the cat has been petted today.
     */
    public Cat(String name, int age, int weightInOz, Coord2D location, boolean hasBeenPetToday) {
        super(name, age, weightInOz, location);
        this.hasBeenPetToday = hasBeenPetToday;
    }

    /**
     * Feeds the cat with the specified type of food, affecting its weight.
     * The weight gain depends on whether the cat has been petted today and the type of food.
     *
     * @param food The type of food the cat will eat.
     * @return The amount of weight gained in ounces after eating.
     */
    @Override
    public int eats(String food) {
        if ("cans".equals(food)) {
            int weightGain = hasBeenPetToday ? 2 : 1;
            this.weightInOz += weightGain;
            return weightGain;
        } else if ("treats".equals(food)) {
            int weightGain = 1 + (this.weightInOz / 8);
            this.weightInOz += weightGain;
            return weightGain;
        }
        return 0;
    }

    /**
     * Calculates the age of the cat in terms equivalent to human years.
     * This method needs to be implemented to provide the correct conversion.
     *
     * @return The age of the cat converted to human years.
     */
    @Override
    public int ageInHumanYears() {
        // The conversion logic needs to be implemented
        return 0;
    }

    /**
     * Determines the amount of food the cat needs.
     * The amount depends on whether the cat has been petted today and the type of food.
     *
     * @param food The type of food to check for the required amount.
     * @return The amount of food needed in ounces.
     */
    @Override
    public int foodNeeded(String food) {
        if ("cans".equals(food)) {
            return (hasBeenPetToday ? 2 : 1);
        } else if ("treats".equals(food)) {
            return (1 + (this.weightInOz / 8));
        }
        return 0;
    }

    /**
     * Retrieves the current location of the cat.
     *
     * @return The 2D coordinate representing the cat's location.
     */
    @Override
    public Coord2D getLocation() {
        return this.location;
    }

    // Other cat-specific methods can be added here
}
