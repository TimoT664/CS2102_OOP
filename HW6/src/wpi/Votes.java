package wpi;

public class Votes {
    private int firstVotes;
    private int secondVotes;
    private int thirdVotes;

    /*public Votes() {

    }*/
    public Votes(Votes v){
        this.firstVotes = v.firstVotes;
        this.secondVotes = v.secondVotes;
        this.thirdVotes = v.thirdVotes;
    }
    public Votes(int first, int second, int third){
        this.firstVotes = first;
        this.secondVotes = second;
        this.thirdVotes = third;
    }

    public int getFirstVotes(){
        return this.firstVotes;
    }
    public int getSecondVotes(){
        return this.secondVotes;
    }
    public int getThirdVotes(){
        return this.thirdVotes;
    }
    public void voteFirst(){
        this.firstVotes++;
    }
    public void voteSecond(){
        this.secondVotes++;
    }
    public void voteThird(){
        this.thirdVotes++;
    }
/*
    public void incrementFirstVotes() {
    }

    public void incrementSecondVotes() {
    }

    public void incrementThirdVotes() {
    }*/
}
