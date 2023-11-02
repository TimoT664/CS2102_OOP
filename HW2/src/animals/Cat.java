package animals;

import animals.AbstractPet;
import coord.Coord2D;

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
            this.weightInOz += 1 + (this.weightInOz / 8);
        }
        return 0;}

    /**
     * @return
     */
    @Override
    public int ageInHumanYears() {
        return 0;
    }

    // Other cat-specific methods can be added here
}
