public abstract class AbstractPet implements Petable {

    protected String name;
    protected int age;
    protected int weightInOz;
    protected Coord2D location;

    /**
     * Constructor for the AbstractPet class.
     *
     * @param name       Name of the pet.
     * @param age        Age of the pet.
     * @param weightInOz Weight of the pet in ounces.
     * @param location   2D coordinate location of the pet.
     */
    public AbstractPet(String name, int age, int weightInOz, Coord2D location) {
        this.name = name;
        this.age = age;
        this.weightInOz = weightInOz;
        this.location = location;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public int getWeightInOz() {
        return this.weightInOz;
    }

    // Other common methods can be added here

    // Abstract methods that subclasses must implement
    @Override
    public abstract int eats(String food);

    @Override
    public abstract int ageInHumanYears();

    // Assuming Petable interface and Coord2D class are defined elsewhere
}
