public class Bird extends AbstractPet {
    private boolean wingsClipped;

    public Bird(String name, int age, int weightInOz, Coord2D location, boolean wingsClipped) {
        super(name, age, weightInOz, location);
        this.wingsClipped = wingsClipped;
    }

    @Override
    public int eats(String food) {
        if ("seeds".equals(food)) {
            this.weightInOz += 1; // Bird eats 1 unit of seeds
        }
        // Additional food types can be added here if needed
        return 0;
    }

    public int ageInHumanYears() {
        int ageInHuman = this.age * 9;
        return ageInHuman;
    }

    @Override
    public Coord2D getLocation() {
        return this.location;
    }

    // Other bird-specific methods can be added here
}
