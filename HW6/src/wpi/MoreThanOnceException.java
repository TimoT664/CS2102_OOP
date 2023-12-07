package wpi;

/**
 * Exception thrown when a candidate is voted for more than once by the same person in a single vote.
 */
public class MoreThanOnceException extends Exception {

    /**
     * Constructor for MoreThanOnceException.
     *
     * @param candidateVotedMultipleTimes The name of the candidate who was voted for multiple times.
     */
    public MoreThanOnceException(String candidateVotedMultipleTimes) {
        super(candidateVotedMultipleTimes + " was voted for more than once in a single vote");
    }
}
