package wpi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ElectionData {

    private HashMap<String, Votes> candidatesData;
    private I3VoteStrategy strategy;

    public ElectionData(I3VoteStrategy strategy) {
        this.candidatesData = new HashMap<>();
        this.strategy = strategy;
    }

    public void setStrategy(I3VoteStrategy strategy) {
        this.strategy = strategy;
    }

    public Set<String> getCandidates() {
        return new HashSet<>(candidatesData.keySet());
    }
    public void nominateCandidate(String candidate) throws AlreadyNominatedException {
        if (candidatesData.containsKey(candidate)) {
            throw new AlreadyNominatedException(candidate);
        }
        // Initialize the Votes object with zero votes in all categories
        candidatesData.put(candidate, new Votes(0, 0, 0));
    }


    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException {
        if (first.equals(second) || first.equals(third) || second.equals(third)) {
            throw new MoreThanOnceException(first.equals(second) ? first : third);
        }

        if (!candidatesData.containsKey(first) || !candidatesData.containsKey(second) || !candidatesData.containsKey(third)) {
            String notNominated = !candidatesData.containsKey(first) ? first : (!candidatesData.containsKey(second) ? second : third);
            throw new CandidateNotNominatedException(notNominated);
        }

        candidatesData.get(first).voteFirst();
        candidatesData.get(second).voteSecond();
        candidatesData.get(third).voteThird();
    }

    public Optional<String> calculateWinner() {
        HashMap<String, Votes> candidatesCopy = new HashMap<>();
        for (String candidate : candidatesData.keySet()) {
            candidatesCopy.put(candidate, new Votes(candidatesData.get(candidate)));
        }

        return strategy.calculateWinner(candidatesCopy);
    }
}
