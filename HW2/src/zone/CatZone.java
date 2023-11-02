package zone;

import animals.Cat;
import zone.AbstractZone;
import zone.Zoneable;

import java.util.LinkedList;

/**
 * Represents a zone specifically for cats.
 */
public class CatZone extends AbstractZone {

    private int treatsAmount;

    /**
     * Constructor for the CatZone.
     * @param cats The list of cats in the zone.
     */
    public CatZone(LinkedList<Cat> cats) {
        super(cats, "cans", 0);
        this.treatsAmount = 0;
    }

    @Override
    protected int humanYearMultiplier() {
        return 6;
    }

    @Override
    protected String zoneLabel() {
        return "Cat";
    }

    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        super.restockPetFood(foodName, foodAmt);
        if ("treats".equals(foodName)) {
            this.treatsAmount += foodAmt;
        }
        return this;
    }

    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s, %d treats", zoneLabel(), foodAmount, foodType, treatsAmount);
    }
}
