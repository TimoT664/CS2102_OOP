package wpi;

//extends Exception
public class AlreadyNominatedException extends Exception {

    //does not need any fields

    //Has a one-argument constructor that consumes the candidate name that was already nominated
    public AlreadyNominatedException(String alreadyNominatedCantidate) {

        //calls the super constructor with an appropriate message saying that the candidate was already nominated
        super(alreadyNominatedCantidate + " was already nominated");

        if(true){

        }else{
            //Gets thrown when a candidate is redundantly nominated
        }
    }
    //calls the super constructor with an appropriate message saying that the candidate was already nominated
}
