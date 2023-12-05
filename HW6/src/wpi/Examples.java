package wpi;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class Examples {

    ElectionData eData;

    public Examples() {
        eData = new ElectionData(new MostFirstVotesStrategy());
    }

    @Test
    public void testOneVote() {
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("gompei"), this.eData.calculateWinner());
    }

    // Step 5
    @Test(expected=AlreadyNominatedException.class)
    public void testCandidateAlreadyNominated() throws AlreadyNominatedException{
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("gompei");

        fail("did not throw exception properly");
    }



}