package wpi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Strategy for calculating the election winner based on the most agreeable candidate.
 * The most agreeable candidate is the one with the most votes in any single category (first, second, or third).
 */
public class MostAgreeableStrategy implements I3VoteStrategy {

    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        String mostAgreeableCandidate = null;
        int highestVoteCount = 0;

        // Iterate through each candidate's votes
        for (Map.Entry<String, Votes> entry : votes.entrySet()) {
            String candidate = entry.getKey();
            Votes voteCounts = entry.getValue();

            // Check for the highest vote count in any category for this candidate
            if (voteCounts.getFirstVotes() > highestVoteCount) {
                highestVoteCount = voteCounts.getFirstVotes();
                mostAgreeableCandidate = candidate;
            }
            if (voteCounts.getSecondVotes() > highestVoteCount) {
                highestVoteCount = voteCounts.getSecondVotes();
                mostAgreeableCandidate = candidate;
            }
            if (voteCounts.getThirdVotes() > highestVoteCount) {
                highestVoteCount = voteCounts.getThirdVotes();
                mostAgreeableCandidate = candidate;
            }
        }

        // Return the most agreeable candidate if found
        if (mostAgreeableCandidate != null) {
            return Optional.of(mostAgreeableCandidate);
        }

        return Optional.empty();
    }
}
