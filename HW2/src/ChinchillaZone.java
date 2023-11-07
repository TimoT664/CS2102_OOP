import java.util.LinkedList;

/**
 * Represents a zone specifically for chinchillas within a pet rescue environment.
 * This class extends AbstractZone and manages a collection of Chinchilla instances.
 */
public class ChinchillaZone extends AbstractZone {
    private int pellets; // The amount of pellets available in the zone for feeding
    private int hay;     // The amount of hay available in the zone for feeding

    /**
     * Constructs a ChinchillaZone with a list of chinchillas.
     *
     * @param chinchillas A LinkedList of Chinchilla instances representing the chinchillas in the zone.
     */
    public ChinchillaZone(LinkedList<Chinchilla> chinchillas) {
        super(chinchillas);
        this.pellets = 0;
        this.hay = 0;
    }

    /**
     * Provides the multiplier to convert chinchilla age to human years.
     *
     * @return An integer representing the multiplier for chinchilla age conversion.
     */
    @Override
    protected int humanYearMultiplier() {
        return 10; // The multiplier for converting chinchilla years to human years
    }

    /**
     * Returns the label that identifies the zone as a Chinchilla zone.
     *
     * @return A string representing the label for the zone.
     */
    @Override
    protected String zoneLabel() {
        return "Chinchilla";
    }

    /**
     * Restocks the pet food in the zone with the specified amount.
     *
     * @param foodName The name of the food to restock.
     * @param foodAmt  The amount of food to add to the zone's stock.
     * @return The current instance of the zone after restocking food.
     */
    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if ("pellets".equals(foodName)) {
            this.pellets += foodAmt;
        } else if ("hay".equals(foodName)) {
            this.hay += foodAmt;
        }
        return this;
    }

    /**
     * Feeds all chinchillas in the zone, reducing the stock of food accordingly.
     *
     * @return The current instance of the zone after feeding the pets.
     */
    @Override
    public Zoneable feedZone() {
        for (Petable pet : this.pets) {
            int hayNeeded = pet.foodNeeded("hay");
            int pelletsNeeded = pet.foodNeeded("pellets");

            if (this.hay >= hayNeeded) {
                this.hay -= hayNeeded;
            }
            else {
                this.hay = 0;
            }
            if (this.pellets >= pelletsNeeded) {
                this.pellets -= pelletsNeeded;
            } else{
                this.pellets = 0;
            }

            // Ensure that food quantities do not go negative
            //this.hay = Math.max(this.hay, 0);
            //this.pellets = Math.max(this.pellets, 0);
        }
        return this;
    }

    /**
     * Returns a string representation of the food stock in the zone.
     *
     * @return A formatted string indicating the amount of each type of food available in the zone.
     */
    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s, %d %s", zoneLabel(), this.pellets, "pellets", this.hay, "hay");
    }
}
