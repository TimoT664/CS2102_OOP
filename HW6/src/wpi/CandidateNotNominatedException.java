package wpi;

/**
 * Exception thrown when a vote is cast for a candidate who has not been nominated.
 */
public class CandidateNotNominatedException extends Exception {

    // Stores the candidate name in a private field
    private String candidate;

    /**
     * Constructor for CandidateNotNominatedException.
     *
     * @param candidate The name of the candidate who was not nominated.
     */
    public CandidateNotNominatedException(String candidate) {
        super("Candidate " + candidate + " was not nominated");
        this.candidate = candidate;
    }

    /**
     * Getter for the candidate name.
     *
     * @return The name of the candidate.
     */
    public String getCandidate() {
        return this.candidate;
    }
}
