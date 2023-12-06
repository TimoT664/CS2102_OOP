package wpi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//implements I3VoteStrategy

public class MostFirstVotesStrategy implements I3VoteStrategy{

    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        //produces Optional.of(candidate) for the candidate that has both:
        //the most first votes
        //first votes that are strictly greater (> not >=) than 50% the total # of first votes
        //E.g. a clear majority. having 1/3rd the total first votes is not good enough
        //Beware of integer division, it is easiest to compare current first votes > (total first votes / 2)
        //produces Optional.empty() otherwise
        votes.keySet();
        for (Map.Entry<String, Votes> mapElement: votes.entrySet()) {
            String candidate = mapElement.getKey();
            Votes values = mapElement.getValue();
        }
        return Optional.empty();
    }
}
