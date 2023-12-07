package wpi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Strategy for calculating the election winner based on who has the most first votes,
 * and ensuring these votes are strictly greater than 50% of the total number of first votes.
 */
public class MostFirstVotesStrategy implements I3VoteStrategy {

    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        String leadingCandidate = null;
        int totalFirstVotes = 0;
        int maxFirstVotes = 0;

        // Calculate total first votes and find the candidate with the most first votes
        for (Map.Entry<String, Votes> entry : votes.entrySet()) {
            int candidateFirstVotes = entry.getValue().getFirstVotes();
            totalFirstVotes += candidateFirstVotes;

            if (candidateFirstVotes > maxFirstVotes) {
                maxFirstVotes = candidateFirstVotes;
                leadingCandidate = entry.getKey();
            }
        }

        // Check if the leading candidate has more than 50% of the total first votes
        if (maxFirstVotes > totalFirstVotes / 2) {
            return Optional.of(leadingCandidate);
        }

        return Optional.empty();
    }
}
