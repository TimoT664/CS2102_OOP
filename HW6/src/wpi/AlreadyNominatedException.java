package wpi;

/**
 * Exception thrown when a candidate is redundantly nominated.
 */
public class AlreadyNominatedException extends Exception {

    /**
     * Constructor that takes the name of the already nominated candidate.
     *
     * @param alreadyNominatedCandidate The name of the candidate that was already nominated.
     */
    public AlreadyNominatedException(String alreadyNominatedCandidate) {
        super(alreadyNominatedCandidate + " was already nominated");
    }
}
