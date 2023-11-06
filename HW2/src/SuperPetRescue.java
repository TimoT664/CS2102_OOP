import java.util.LinkedList;

/**
 * The top-level class for managing a pet rescue operation which contains multiple zones.
 * Each zone is responsible for a specific type of pet and implements the Zoneable interface.
 */
public class SuperPetRescue {

    // Field to hold the list of zones within the pet rescue
    private LinkedList<Zoneable> zones;

    /**
     * Constructor for the SuperPetRescue class.
     *
     * @param zones A LinkedList of Zoneable objects, each representing a different zone within the pet rescue.
     */
    public SuperPetRescue(LinkedList<Zoneable> zones){
        this.zones = zones;
    }

    /**
     * Calculates the total number of pets across all zones in the pet rescue.
     *
     * @return An integer representing the sum of all pets in each zone.
     */
    public int totalPets(){
        int sum = 0;
        // Iterate through each zone and add the number of pets in that zone to the sum.
        for(Zoneable zone : this.zones){
            sum += zone.petsInZone();
        }
        return sum;
    }

    /**
     * Identifies the heaviest pet across all zones in the pet rescue.
     *
     * @return A String representing the name of the heaviest pet found across all zones.
     *         Returns null if there are no pets in any of the zones.
     */
    public String getHeaviestPetsName() {
        Petable chonkiest = null; // Variable to hold the heaviest pet found so far.
        // Iterate through each zone to find the heaviest pet.
        for(Zoneable zone : this.zones){
            Petable heaviestInZone = zone.heaviestPet();
            // If chonkiest is null or the current pet is heavier than the chonkiest, update chonkiest.
            if(chonkiest == null || (heaviestInZone != null && chonkiest.getWeightInOz() < heaviestInZone.getWeightInOz())){
                chonkiest = heaviestInZone;
            }
        }
        // Return the name of the heaviest pet, or null if no pets are found.
        return chonkiest == null ? null : chonkiest.getName();
    }
}
