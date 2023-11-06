/**
 * AbstractPet serves as a base class for different types of pets.
 * It implements the Petable interface and provides common functionality
 * for all pet types. This class should be extended by specific pet classes
 * that implement the abstract methods defined herein.
 */
public abstract class AbstractPet implements Petable {

    protected String name;       // Name of the pet
    protected int age;           // Age of the pet
    protected int weightInOz;    // Weight of the pet in ounces
    protected Coord2D location;  // 2D coordinate location of the pet

    /**
     * Constructor for the AbstractPet class.
     *
     * @param name       The name of the pet.
     * @param age        The age of the pet in years.
     * @param weightInOz The weight of the pet in ounces.
     * @param location   The 2D coordinate location of the pet.
     */
    public AbstractPet(String name, int age, int weightInOz, Coord2D location) {
        this.name = name;
        this.age = age;
        this.weightInOz = weightInOz;
        this.location = location;
    }

    /**
     * Retrieves the name of the pet.
     *
     * @return The name of the pet.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the age of the pet.
     *
     * @return The age of the pet in years.
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /**
     * Retrieves the weight of the pet in ounces.
     *
     * @return The weight of the pet in ounces.
     */
    @Override
    public int getWeightInOz() {
        return this.weightInOz;
    }

    /**
     * Abstract method to be implemented by subclasses for the pet to consume food.
     * The implementation should handle the type of food and the effect on the pet's weight.
     *
     * @param food The type of food the pet will eat.
     * @return The amount of weight gained in ounces after eating.
     */
    @Override
    public abstract int eats(String food);

    /**
     * Abstract method to be implemented by subclasses to calculate the pet's age in human years.
     *
     * @return The age of the pet converted to human years.
     */
    @Override
    public abstract int ageInHumanYears();

    // Additional common methods and abstract methods can be added here

    // Assuming Petable interface and Coord2D class are defined elsewhere
}
