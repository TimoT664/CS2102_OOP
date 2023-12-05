package wpi;

public class MoreThanOnceException extends Exception {
    //does not need any fields

    //Has a one-argument constructor that consumes the candidate name that was voted for multiple times
    public MoreThanOnceException(String candidateVotedMultipleTimes){
        //Gets thrown when a candidate is voted for more than once by the same person (in the same vote)

        //calls the super constructor with an appropriate message saying that the candidate was voted for more than once in a single vote
        super(candidateVotedMultipleTimes + " candidate was voted for more than once in a single vote");
    }
}
