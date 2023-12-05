package wpi;

//extends Exception
public class CandidateNotNominatedException extends Exception {
    //Stores the candidate name in a private field
    private String candidate;
    public CandidateNotNominatedException(){

    }
    //Has a one-argument constructor that consumes the candidate name that was voted for but not nominated
    public CandidateNotNominatedException(String candidate){
        //calls the super constructor with a message string that says the candidate was not nominated
        super("Candiate was not nominated");
        this.candidate = candidate;

        //TODO
        //Gets thrown when a candidate is voted for but has not been nominated
    }

    //has a getter for the candidate name (no need for a setter)
    public String getCandidate(){
        return this.candidate;
    }
    public void setCandidate(String candidate){
        this.candidate = candidate;
    }
}
