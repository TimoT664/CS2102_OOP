import java.util.LinkedList;

/**
 * Represents a zone specifically for birds.
 */
public class BirdZone extends AbstractZone {

    /**
     * Constructor for the BirdZone.
     * @param birds The list of birds in the zone.
     */
    public BirdZone(LinkedList<Bird> birds) {
        super(birds, "seeds", 0);
    }

    @Override
    protected int humanYearMultiplier() {
        return 9;
    }

    @Override
    protected String zoneLabel() {
        return "Bird";
    }
}
