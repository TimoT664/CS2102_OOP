import java.util.LinkedList;

/**
 * Represents a zone specifically for chinchillas.
 */
public class ChinchillaZone extends AbstractZone {

    /**
     * Constructor for the ChinchillaZone.
     * @param chinchillas The list of chinchillas in the zone.
     */
    public ChinchillaZone(LinkedList<Chinchilla> chinchillas) {
        super(chinchillas, "pellets", 0);
    }

    @Override
    protected int humanYearMultiplier() {
        return 10;
    }

    @Override
    protected String zoneLabel() {
        return "Chinchilla";
    }
}
