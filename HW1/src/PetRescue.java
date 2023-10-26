import java.util.Collections;
import java.util.LinkedList;

public class PetRescue extends Coord implements PetRescueable{

    public String petOfTheMonth;
    public LinkedList<Integer> birdWeights;
    public LinkedList<Integer> dogYears;
    public LinkedList<Coord> catCoords;
    public int pellets = 0;
    public int hay = 0;

    public PetRescue(LinkedList<Integer> birdWeights,
                     LinkedList<Integer> dogYears,
                     String petOfTheMonth,
                     LinkedList<Coord> catCoords){
        super("Test",1,2); // TODO
        this.birdWeights = birdWeights;
        this.dogYears = dogYears;
        this.petOfTheMonth = petOfTheMonth;
        this.catCoords = catCoords;
    }

    public int closestTo(Coord c){
        return 0;
    }

    @Override
    public int biggestBird() {
        return Collections.max(this.birdWeights);
    }

    @Override
    public LinkedList<Integer> inHumanYears() {
        LinkedList<Integer> inHumanYears = new LinkedList<Integer>();
        this.dogYears.forEach((dogYear) -> inHumanYears.add(dogYear * 7));

        return inHumanYears;
    }

    @Override
    public void bestowHonor(String title, String credential) {
        //TODO Check empty string to prevent blanks
        title = title == null  ? "" : title + " ";
        credential = credential == null ? "" : ", " + credential;

        this.petOfTheMonth =  title + this.petOfTheMonth + credential;
    }

    @Override
    public String feedChinchillas(int pellets, int hay) {
        this.pellets = this.pellets + pellets;
        this.hay = this.hay + hay;

        String amountHay = this.hay + "";
        String amountPellets = this.pellets + "";

        if(this.hay < 0){
            this.hay = 0;
            amountHay = "unknown";
        }
        if(this.pellets < 0){
            this.pellets = 0;
            amountPellets = "unknown";
        }

        return "Chinchilla: " + amountPellets + " pellets, " + amountHay + " hay";
    }

    @Override
    public String closestTo(int x, int y) {
        Coord closestCoord = new Coord("Conspiciously Catless", -111,-111);

        for(Coord currentCoord : this.catCoords){

            // https://www.baeldung.com/java-distance-between-two-points
            double closestCoordDist = Math.sqrt((closestCoord.y - y) * (closestCoord.y - y) + (closestCoord.x - x) * (closestCoord.x - x));
            double currentCoordDist = Math.sqrt((currentCoord.y - y) * (currentCoord.y - y) + (currentCoord.x - x) * (currentCoord.x - x));

            if(closestCoordDist > currentCoordDist){
                closestCoord = currentCoord;
            }
        }
        return closestCoord.name;
    }

    //private double distanceBetweenCoords(){

    //}
}
