/**
 * Represents a dog, which is a specific type of pet.
 * This class extends the AbstractPet class and provides implementations
 * for the abstract methods defined there.
 */
public class Dog extends AbstractPet {

    private boolean hasBeenWalkedToday; // Indicates whether the dog has been walked today

    /**
     * Constructs a new Dog instance with the specified details.
     *
     * @param name              The name of the dog.
     * @param age               The age of the dog in years.
     * @param weightInOz        The weight of the dog in ounces.
     * @param location          The 2D coordinate location of the dog.
     * @param hasBeenWalkedToday A boolean indicating whether the dog has been walked today.
     */
    public Dog(String name, int age, int weightInOz, Coord2D location, boolean hasBeenWalkedToday) {
        super(name, age, weightInOz, location);
        this.hasBeenWalkedToday = hasBeenWalkedToday;
    }

    /**
     * Feeds the dog with the specified type of food, affecting its weight.
     * The weight gain depends on whether the dog has been walked today and the type of food.
     *
     * @param food The type of food the dog will eat.
     * @return The amount of weight gained in ounces after eating.
     */
    @Override
    public int eats(String food) {
        if ("bones".equals(food)) {
            int weightGain = hasBeenWalkedToday ? 3 : 4; // Dogs gain more weight if they haven't been walked
            this.weightInOz += weightGain;
            return weightGain;
        }
        // Add other food types if necessary
        return 0;
    }

    /**
     * Calculates the age of the dog in terms equivalent to human years.
     * This method needs to be implemented to provide the correct conversion.
     *
     * @return The age of the dog converted to human years.
     */
    @Override
    public int ageInHumanYears() {
        // The conversion logic needs to be implemented
        // Commonly, the first two years of a dog's life count as 10.5 human years each,
        // and after that, each dog year equals about 4 human years.
        if (this.age <= 2) {
            return this.age * 10;
        } else {
            return 20 + ((this.age - 2) * 4);
        }
    }

    /**
     * Determines the amount of food the dog needs.
     * The amount depends on whether the dog has been walked today and the type of food.
     *
     * @param food The type of food to check for the required amount.
     * @return The amount of food needed in ounces.
     */
    @Override
    public int foodNeeded(String food) {
        if ("bones".equals(food)) {
            return (hasBeenWalkedToday ? 3 : 4);
        }
        // Add other food types if necessary
        return 0;
    }

    /**
     * Retrieves the current location of the dog.
     *
     * @return The 2D coordinate representing the dog's location.
     */
    @Override
    public Coord2D getLocation() {
        return this.location;
    }

    public int getAgeInHumanYears() {
        return 0;
    }

    public boolean isPet() {
        return false;
    }

    // Other dog-specific methods can be added here
}
