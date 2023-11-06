public class Cat extends AbstractPet {

    private boolean hasBeenPetToday;

    public Cat(String name, int age, int weightInOz, Coord2D location, boolean hasBeenPetToday) {
        super(name, age, weightInOz, location);
        this.hasBeenPetToday = hasBeenPetToday;
    }

    @Override
    public int eats(String food) {
        if ("cans".equals(food)) {
            this.weightInOz += hasBeenPetToday ? 2 : 1;
        } else if ("treats".equals(food)) {
            this.weightInOz += (1 + (this.weightInOz / 8));
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
        if ("cans".equals(food)) {
           return (hasBeenPetToday ? 2 : 1);
        } else if ("treats".equals(food)) {
            return  (1 + (this.weightInOz / 8));
        }
        return 0;
    }

    @Override
    public Coord2D getLocation() {
        return this.location;
    }

    // Other cat-specific methods can be added here
}
