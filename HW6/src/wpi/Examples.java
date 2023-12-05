package wpi;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Test
    public void testMostFirstVotesWinner() {
        eData.setStrategy(new MostFirstVotesStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");

            eData.submitVote("gompei", "husky", "bristaco");
            eData.submitVote("husky", "bristaco", "gompei");
            eData.submitVote("gompei", "bristaco", "husky");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(Optional.of("gompei"), eData.calculateWinner());
    }
    @Test
    public void testMostAgreeableWinner() {
        eData.setStrategy(new MostAgreeableStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");
            eData.nominateCandidate("I");


            // Submitting votes
            eData.submitVote("gompei", "husky", "I"); // gompei: 1st, husky: 2nd, bristaco: 3rd
            eData.submitVote("husky", "gompei", "bristaco"); // husky: 1st, bristaco: 2nd, gompei: 3rd
            eData.submitVote("bristaco", "gompei", "husky"); // bristaco: 1st, gompei: 2nd, husky: 3rd
            // Add more votes as needed to test the strategy thoroughly

        } catch (Exception e) {
            fail(e.getMessage());
        }

        // Assuming the strategy calculates the most agreeable candidate based on the ranking.
        // Replace "expectedWinner" with the expected candidate's name based on strategy's logic
        String expectedWinner = "gompei";
        assertEquals(Optional.of(expectedWinner), eData.calculateWinner());
    }

    @Test(expected = CandidateNotNominatedException.class)
    public void testVotingForNonNominatedCandidate() throws CandidateNotNominatedException, MoreThanOnceException, AlreadyNominatedException {
        eData.nominateCandidate("gompei");
        eData.nominateCandidate("husky");
        // Do not nominate "bristaco"
        eData.submitVote("gompei", "husky", "bristaco"); // This should throw the exception
        fail("did not throw exception properly");
    }

    @Test
    public void testGetCandidatesImmutability() throws AlreadyNominatedException {
        eData.nominateCandidate("gompei");
        Set<String> candidates = eData.getCandidates();
        try {
            candidates.add("newCandidate");
            eData.nominateCandidate("newCandidate");
        } catch (AlreadyNominatedException e) {
            fail("Candidates set should be immutable");
        }

    }

    @Test
    public void testSimpleMajorityRequirement() {
        eData.setStrategy(new MostFirstVotesStrategy());
        try {
            eData.nominateCandidate("gompei");
            eData.nominateCandidate("husky");
            eData.nominateCandidate("bristaco");

            eData.submitVote("gompei", "husky", "bristaco");
            eData.submitVote("husky", "gompei", "bristaco");
            // No simple majority here
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertFalse(eData.calculateWinner().isPresent()); // No winner should be declared
    }
    @Test(expected = MoreThanOnceException.class)
    public void testVotingForCandidateMoreThanOnce() throws CandidateNotNominatedException, MoreThanOnceException, AlreadyNominatedException {
        eData.nominateCandidate("gompei");
        eData.nominateCandidate("husky");
        eData.nominateCandidate("bristaco");

        // Attempt to vote for "gompei" more than once
        eData.submitVote("gompei", "gompei", "bristaco"); // This should throw MoreThanOnceException
    }

}