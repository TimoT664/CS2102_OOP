public class Chinchilla extends AbstractPet {
    private int dustBathResidueInOz;

    public Chinchilla(String name, int age, int weightInOz, Coord2D location, int dustBathResidueInOz) {
        super(name, age, weightInOz, location);
        this.dustBathResidueInOz = dustBathResidueInOz;
    }

    @Override
    public int getWeightInOz() {
        return super.getWeightInOz() + this.dustBathResidueInOz;
    }

    @Override
    public int eats(String food) {
        if ("pellets".equals(food)) {
            this.weightInOz += 3;
         } else if ("hay".equals(food)) {
            this.weightInOz += 1;
        }
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int ageInHumanYears() {
        return 0;
    }

    @Override
    public int foodNeeded(String food) {
        if ("pellets".equals(food)) {
           return 3;
        } else if ("hay".equals(food)) {
            return 1;
        }
        return 0;
    }

    // Other chinchilla-specific methods can be added here
    @Override
    public Coord2D getLocation() {
        return this.location;
    }
}
