import java.util.LinkedList;

/**
 * Represents a zone specifically for cats within a pet rescue environment.
 * This class extends the AbstractZone class and provides specific implementations
 * for cats' food stock management and feeding.
 */
public class CatZone extends AbstractZone {

    private int treats; // The stock of treats available in the CatZone
    private int cans;   // The stock of cans available in the CatZone

    /**
     * Constructs a new CatZone instance with the specified list of cats.
     *
     * @param cats The list of cats in the zone.
     */
    public CatZone(LinkedList<Cat> cats) {
        super(cats);
        this.cans = 0;   // Initializes the number of cans to zero
        this.treats = 0; // Initializes the number of treats to zero
    }

    /**
     * Provides the multiplier to convert cat age to human years.
     *
     * @return The multiplier for converting cat age to human years.
     */
    @Override
    protected int humanYearMultiplier() {
        return 6; // The average multiplier for cat age to human years
    }

    /**
     * Returns the label for the CatZone.
     *
     * @return The label representing the type of zone.
     */
    @Override
    protected String zoneLabel() {
        return "Cat"; // The label for this zone
    }

    /**
     * Restocks the pet food in the CatZone based on the type of food and amount.
     *
     * @param foodName The name of the food to restock.
     * @param foodAmt  The amount of food to add to the stock.
     * @return The current instance of the zone after restocking food.
     */
    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if ("treats".equals(foodName)) {
            this.treats += foodAmt; // Adds the amount to the treats stock
        } else if ("cans".equals(foodName)) {
            this.cans += foodAmt;   // Adds the amount to the cans stock
        }
        return this;
    }

    /**
     * Feeds all the cats in the zone. It deducts the required amount of food from the stock.
     * If the stock runs out, it sets the stock to zero.
     *
     * @return The current instance of the zone after feeding the pets.
     */
    @Override
    public Zoneable feedZone() {
        for (Petable pet : this.pets) {
            int treatsNeeded = pet.foodNeeded("treats");
            int cansNeeded = pet.foodNeeded("cans");

            if (this.treats >= treatsNeeded) {
                this.treats -= treatsNeeded; // Deducts the treats needed from the stock
            }
            if (this.cans >= cansNeeded) {
                this.cans -= cansNeeded;     // Deducts the cans needed from the stock
            }

            // Ensures the stock doesn't go below zero
            this.treats = Math.max(this.treats, 0);
            this.cans = Math.max(this.cans, 0);
        }
        return this;
    }

    /**
     * Returns a string representation of the food stock in the CatZone.
     *
     * @return A formatted string indicating the amount of each type of food in the pantry.
     */
    @Override
    public String getPantryLabel() {
        return String.format("%s: %d %s, %d %s", zoneLabel(), this.cans, "cans", this.treats, "treats");
    }
}
