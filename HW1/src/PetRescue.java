import java.util.Collections;
import java.util.LinkedList;

/**
 * Represents a Pet Rescue center that manages various pets and their related operations.
 */
public class PetRescue extends Coord implements PetRescueable{

    /** The name of the pet of the month. */
    public String petOfTheMonth;

    /** A list of weights of birds in the rescue. */
    public LinkedList<Integer> birdWeights;

    /** A list of dog ages in dog years. */
    public LinkedList<Integer> dogYears;

    /** A list of coordinates where cats are located. */
    public LinkedList<Coord> catCoords;

    /** The number of pellets available for chinchillas. */
    public int pellets = 0;

    /** The amount of hay available for chinchillas. */
    public int hay = 0;

    /**
     * Constructs a new PetRescue with the given parameters.
     *
     * @param birdWeights A list of weights of birds.
     * @param dogYears A list of dog ages in dog years.
     * @param petOfTheMonth The name of the pet of the month.
     * @param catCoords A list of coordinates where cats are located.
     */
    public PetRescue(LinkedList<Integer> birdWeights,
                     LinkedList<Integer> dogYears,
                     String petOfTheMonth,
                     LinkedList<Coord> catCoords){
        super("Test",1,2); // TODO
        this.birdWeights = birdWeights;
        this.dogYears = dogYears;
        this.petOfTheMonth = petOfTheMonth;
        this.catCoords = catCoords;
    }

    public int closestTo(Coord c){
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int biggestBird() {
        if (this.birdWeights.isEmpty()) {
            return 0;  // Return 0 for an empty list
        }
        return Collections.max(this.birdWeights);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<Integer> inHumanYears() {
        LinkedList<Integer> inHumanYears = new LinkedList<Integer>();
        this.dogYears.forEach((dogYear) -> inHumanYears.add(dogYear * 7));

        return inHumanYears;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bestowHonor(String title, String credential) {
        String prefix = "";
        String suffix = "";

        // Check for null or empty string for title
        if (! (title.equals("")) ) {
            prefix = title + " ";
        }

        // Check for null or empty string for credential
        if (! (credential.equals(""))) {
            // Check if petOfTheMonth already ends with a comma
            if (this.petOfTheMonth.contains(", ")) {
                suffix = " " + credential;
            } else {
                suffix = ", " + credential;
            }
        }

        this.petOfTheMonth = prefix + this.petOfTheMonth + suffix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String feedChinchillas(int pellets, int hay) {
        this.pellets = this.pellets + pellets;
        this.hay = this.hay + hay;

        String amountHay = this.hay + "";
        String amountPellets = this.pellets + "";

        if(this.hay < 0){
            this.hay = 0;
            amountHay = "unknown";
        }
        if(this.pellets < 0){
            this.pellets = 0;
            amountPellets = "unknown";
        }

        return "Chinchilla: " + amountPellets + " pellets, " + amountHay + " hay";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String closestTo(int x, int y) {
        Coord targetCoord = new Coord("Conspiciously Catless", -111,-111);

        for(Coord currentCoord : this.catCoords){
            double closestCoordDist = distanceBetweenCoords(targetCoord, new Coord("", x,y));
            double currentCoordDist = distanceBetweenCoords(currentCoord, new Coord("", x,y));

            if(closestCoordDist > currentCoordDist){
                targetCoord = currentCoord;
            }
        }
        return targetCoord.name;
    }

    /**
     * Calculates the distance between two coordinates.
     *
     * @param from The starting coordinate.
     * @param to The ending coordinate.
     * @return The distance between the two coordinates.
     */
    public double distanceBetweenCoords(Coord from, Coord to) {
        return Math.sqrt((from.y - to.y) * (from.y - to.y) + (from.x - to.x) * (from.x - to.x));
    }
}