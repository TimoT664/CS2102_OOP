import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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

        assertEquals("Biggy Big Dog Biggy is expected", "Biggy Big Dog Biggy", pr.petOfTheMonth);
    }

    @Test
    public void testClosestTo(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

       /* catCoords.add(new Coord("A", 1, 1));
       catCoords.add(new Coord("B", 4, 4));
        catCoords.add(new Coord("C", 8, 8));
        catCoords.add(new Coord("D", 12, 12)); */

        pr = new PetRescue(birdWeights, dogYears, "", catCoords);

        assertEquals("Conspiciously Catless", "Conspiciously Catless", pr.closestTo(3, 3));
    }

    @Test
    public void testFeedChinchilla(){
        LinkedList<Integer> birdWeights = new LinkedList<Integer>();
        LinkedList<Integer> dogYears = new LinkedList<Integer>();
        LinkedList<Coord> catCoords = new LinkedList<Coord>();

        pr = new PetRescue(birdWeights, dogYears, "Big Dog", catCoords);
        pr.pantryPelletes = 3;
        pr.pantryHay = 2;

        assertEquals("'Chinchilla: unknown pellets, unknown hay' is expected", "Chinchilla: unknown pellets, unknown hay", pr.feedChinchillas(4, 3));
    }

}