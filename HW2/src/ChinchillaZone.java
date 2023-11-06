import java.util.LinkedList;

/**
 * Represents a zone specifically for chinchillas.
 */
public class ChinchillaZone extends AbstractZone {
    public int pellets;
    public int hay;

    /**
     * Constructor for the ChinchillaZone.
     * @param chinchillas The list of chinchillas in the zone.
     */
    public ChinchillaZone(LinkedList<Chinchilla> chinchillas) {
        super(chinchillas);
        this.pellets = 0;
        this.hay = 0;
    }

    @Override
    protected int humanYearMultiplier() {
        return 10;
    }

    @Override
    protected String zoneLabel() {
        return "Chinchilla";
    }

    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if ("pellets".equals(foodName)) {
            this.pellets += foodAmt;
        } else if("hay".equals(foodName)) {
            this.hay += foodAmt;
        }
        return this;
    }

    @Override
    public Zoneable feedZone() {
        for (Petable pet : this.pets) {
            int hayNeeded = pet.foodNeeded("hay");
            int pelletsNeeded = pet.foodNeeded("pellets");

            if (this.hay >= hayNeeded) {
                this.hay -= hayNeeded;
            }
            if (this.pellets >= pelletsNeeded) {
                this.pellets -= pelletsNeeded;
            }

            if (this.hay < 0) {
                this.hay = 0;
            }
            if (this.pellets < 0) {
                this.pellets = 0;
            }
        }
        return this;
    }

    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s, %d %s", zoneLabel(), this.pellets, "pellets", this.hay, "hay");
    }
}
