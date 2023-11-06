import java.util.LinkedList;

/**
 * Represents a zone specifically for cats.
 */
public class CatZone extends AbstractZone {

    private int treats;
    private int cans;
    /**
     * Constructor for the CatZone.
     * @param cats The list of cats in the zone.
     */
    public CatZone(LinkedList<Cat> cats) {
        super(cats);
        this.cans = 0;
        this.treats = 0;
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
        if ("treats".equals(foodName)) {
            this.treats += foodAmt;
        } else if("cans".equals(foodName)) {
            this.cans += foodAmt;
        }
        return this;
    }

    @Override
    public Zoneable feedZone() {
        for (Petable pet : this.pets) {
            int treatsNeeded = pet.foodNeeded("treats");
            int cansNeeded = pet.foodNeeded("cans");

            if (this.treats >= treatsNeeded) {
                this.treats -= treatsNeeded;
            }
            if (this.cans >= cansNeeded) {
                this.cans -= cansNeeded;
            }

            if (this.treats < 0) {
                this.treats = 0;
            }
            if (this.cans < 0) {
                this.cans = 0;
            }
        }
        return this;
    }

    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s, %d %s", zoneLabel(), this.cans, "cans", treats, "treats");
    }
}
