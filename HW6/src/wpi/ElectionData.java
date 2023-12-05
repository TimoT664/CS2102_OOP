package wpi;

import java.util.Optional;
import java.util.Set;

public class ElectionData {

    // TODO
    //Has at least fields with the following types:
    //a HashMap<String, Votes>
    //stores the votes for every nominated candidate
    //I3VoteStrategy
    // stores the current winner calculation strategy
    //Has at least one constructor with the signature:
    //public ElectionData(I3VoteStrategy strategy)
    //initializes all of the fields
    //Has the methods:
    //public void setStrategy(I3VoteStrategy strategy)
    //updates the strategy field
    //public Set<String> getCandidates()
    //returns the list of candidates using HashMap's keySet() method
    //public void nominateCandidate(String person) throws AlreadyNominatedException
    //throws an exception if the person is already on the ballot since redundantly adding them could throw away the current data
    //otherwise, adds the person to the ballot by putting them in the hashmap with 0 votes.
    //public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException
    //throws a CandidateNotNominatedException  if any of the choices are not on the ballot
    //throws a MoreThanOnceException if any of the choices are duplicates
    //otherwise increases the candidates votes, respectively
    //public Optional<String> calculateWinner()
    //produces the name of the Optional.of(candidate) if a clear winner was chosen; produces Optional.empty() if there is no clear winner based on the current strategy.
    //[Encapsulation] makes a deep copy of the HashMap to pass to the strategy
    //calls the strategy's calculateWinner(...) method and returns whatever the strategy returns.

    public ElectionData(I3VoteStrategy strategy){}
    public void setStrategy(I3VoteStrategy strategy){}
    public Set<String> getCandidates(){return null;}
    public void nominateCandidate(String person) throws AlreadyNominatedException{}
    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException{}
    public Optional<String> calculateWinner(){return Optional.empty();}
}
