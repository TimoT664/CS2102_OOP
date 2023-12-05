package wpi;

import java.util.HashMap;
import java.util.Optional;

//implements I3VoteStrategy
public class MostAgreeableStrategy implements I3VoteStrategy{
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        //produces Optional.of(candidate) for the candidate that has the most votes in a single category (first, second, or third, respectively).
        //E.g. if bristaco has 2 first votes, but gompei has 3 second votes, gompei beats bristaco.
        //produces Optional.empty() otherwise

        return Optional.empty();
    }
}
