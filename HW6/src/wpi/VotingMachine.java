package wpi;

import java.util.Scanner;
import java.util.Optional;
import java.util.Set;

public class VotingMachine {

    private ElectionData electionData;
    private Scanner scanner;

    public VotingMachine() {
        this.electionData = new ElectionData(new MostFirstVotesStrategy());
        this.scanner = new Scanner(System.in);
    }

    private void printCandidates() {
        Set<String> candidates = electionData.getCandidates();
        System.out.println("Current candidates are: " + candidates);
    }

    private void nominateCandidate() {
        System.out.println("Who do you want to nominate?");
        String candidate = scanner.nextLine();
        try {
            electionData.nominateCandidate(candidate);
        } catch (AlreadyNominatedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void vote() {
        System.out.println("Who is your first choice?");
        String first = scanner.nextLine();
        System.out.println("Who is your second choice?");
        String second = scanner.nextLine();
        System.out.println("Who is your third choice?");
        String third = scanner.nextLine();

        try {
            electionData.submitVote(first, second, third);
        } catch (CandidateNotNominatedException | MoreThanOnceException e) {
            System.out.println(e.getMessage());
            if (e instanceof CandidateNotNominatedException) {
                System.out.println("Vote not counted, but would you like to nominate them for the ballot? [y]/[n]");
                String response = scanner.nextLine();
                if (response.toLowerCase().startsWith("y")) {
                    try {
                        electionData.nominateCandidate(((CandidateNotNominatedException) e).getCandidate());
                    } catch (AlreadyNominatedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }

    private void changeStrategy() {
        System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?");
        String response = scanner.nextLine();
        if (response.toLowerCase().startsWith("f")) {
            electionData.setStrategy(new MostFirstVotesStrategy());
        } else if (response.toLowerCase().startsWith("a")) {
            electionData.setStrategy(new MostAgreeableStrategy());
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void printWinner() {
        Optional<String> winner = electionData.calculateWinner();
        if (winner.isPresent()) {
            System.out.println("The winner is: " + winner.get());
        } else {
            System.out.println("No clear winner under the current strategy.");
        }
    }

    public static void main(String[] args) {
        VotingMachine votingMachine = new VotingMachine();

        while (true) {
            votingMachine.printCandidates();
            System.out.println("Do you want to [n]ominate someone, [v]ote for someone, change winner [s]trategy, see who [w]on, or [q]uit?");
            String choice = votingMachine.scanner.nextLine().toLowerCase();

            if (choice.startsWith("n")) {
                votingMachine.nominateCandidate();
            } else if (choice.startsWith("v")) {
                votingMachine.vote();
            } else if (choice.startsWith("s")) {
                votingMachine.changeStrategy();
            } else if (choice.startsWith("w")) {
                votingMachine.printWinner();
            } else if (choice.startsWith("q")) {
                System.out.println("Thanks for voting!");
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
