import org.junit.Test;
import java.util.LinkedList;
import static org.junit.Assert.*;

public class Examples {

    PetRescue pr;
    LinkedList<Integer> someBirdWeights = new LinkedList<Integer>();

    public Examples(){
        someBirdWeights.add(10); // 10oz
    }

    @Test
    public void testBirdWeightsNonEmpty(){
        pr = new PetRescue(someBirdWeights, new LinkedList<Integer>(), "", new LinkedList<Coord>());
        assertEquals("10 should be the biggest bird out of 1 bird", 10, pr.biggestBird()); // label text (optional), expected value, actual/check value
    }

    @Test
    public void testDogYearsNonEmpty(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        dogYears.add(2);
        dogYears.add(3);
        pr = new PetRescue(birdWeights, dogYears, "", catCoords);

        LinkedList<Integer> someHumanYears = new LinkedList<Integer>();
        someHumanYears.add(14);
        someHumanYears.add(21);

        assertEquals("DogYears in human years should be 14 and 21.", someHumanYears, pr.inHumanYears());
    }

    @Test
    public void testBestowHonor(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
        pr.bestowHonor("Biggy","Biggy");

        assertEquals("Biggy Big Dog Biggy is expected", "Biggy Big Dog, Biggy", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorAllEmpty(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
        pr.bestowHonor("","");

        assertEquals("Big Dog is expected", "Big Dog", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorCredEmpty(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
        pr.bestowHonor("Biggy","");

        assertEquals("Biggy Big Dog is expected", "Biggy Big Dog", pr.petOfTheMonth);
    }

    @Test
    public void testBestowHonorTitleEmpty(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
        pr.bestowHonor("","Biggy");

        assertEquals("Big Dog,Biggy is expected", "Big Dog, Biggy", pr.petOfTheMonth);
    }
/*
    @Test
    public void testBestowHonorNull(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
        pr.bestowHonor(null,null);

        assertEquals("Big Dog is expected", "Big Dog", pr.petOfTheMonth);
    }*/

    @Test
    public void testClosestTo(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

   /*    catCoords.add(new Coord("A", 1, 1));
       catCoords.add(new Coord("B", 4, 4));
        catCoords.add(new Coord("C", 8, 8));
        catCoords.add(new Coord("D", 12, 12));
*/
        pr = new PetRescue(birdWeights, dogYears, "", catCoords);

        assertEquals("Conspiciously Catless", "Conspiciously Catless", pr.closestTo(3, 3));
    }

    @Test
    public void testClosestToOneLetter(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

       catCoords.add(new Coord("A", 1, 1));
       catCoords.add(new Coord("B", 4, 4));
        catCoords.add(new Coord("C", 8, 8));
        catCoords.add(new Coord("D", 12, 12));

        pr = new PetRescue(birdWeights, dogYears, "", catCoords);

        assertEquals("B is expexcted", "B", pr.closestTo(3, 3));
    }

  /*  @Test
    public void testClosestToRightInTheMiddle(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        catCoords.add(new Coord("A", 1, 1));
        catCoords.add(new Coord("B", 4, 4));
        catCoords.add(new Coord("C", 6, 6));
        catCoords.add(new Coord("D", 12, 12));

        pr = new PetRescue(birdWeights, dogYears, "", catCoords);

        assertEquals("Between 2 ", "B", pr.closestTo(5, 5));
    }*/

    @Test
    public void testFeedChinchilla(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
       // pr.pellets = 3;
       // pr.hay = 2;

        assertEquals("'Chinchilla: unknown pellets, unknown hay' is expected", "Chinchilla: 4 pellets, 3 hay", pr.feedChinchillas(4, 3));
    }

    @Test
    public void testNegativeFeedChinchilla(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);

        assertEquals("'Chinchilla: unknown pellets, unknown hay' is expected", "Chinchilla: unknown pellets, unknown hay", pr.feedChinchillas(-4, -3));
    }
/*
    @Test
    public void testZeroFeedChinchilla(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);

        assertEquals("'Chinchilla: unknown pellets, unknown hay' is expected", "Chinchilla: unknown pellets, unknown hay", pr.feedChinchillas(0, 0));
    }*/
}