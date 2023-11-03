
import jdk.internal.jimage.ImageStrings;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExamplesHW2 {

    public ExamplesHW2(){


    }

    /* ANIMAL TESTS */

    @Test
    public void testBirdEats(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        assertEquals(1,b.eats("seeds"));
    }
    @Test
    public void testBirdEatsNone(){
        Bird b = new Bird("Blue",4,12,new Coord2D(2,2),false);
        assertEquals(0,b.eats("worm"));
    }

    @Test
    public void testBirdGetName(){
        Bird b = new Bird("Blue",4,3,new Coord2D(0,0), true);
        assertEquals("Blue", b.getName());
    }

    @Test
    public void testCatEatsYesPet(){
        Cat c = new Cat("Aria",4,12,new Coord2D(2,2),true);
        assertEquals(2,c.eats("cans"));
    }
    @Test
    public void testCatEatsTreatsBaby(){
        Cat c = new Cat("Kitten",4,6,new Coord2D(2,2),true);
        assertEquals(1,c.eats("treats"));
    }


    @Test
    public void testChinchillaWeightInOz(){
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals(24, ch.getWeightInOz());
    }

    @Test
    public void animalGetName(){
        Bird b = new Bird("Blue",4,3,new Coord2D(0,0), true);
        Cat c = new Cat("Aria",4,20,new Coord2D(0,0), true);
        Chinchilla ch = new Chinchilla("Dusty",4,20,new Coord2D(0,0), 4);
        assertEquals("Blue", b.getName());
        assertEquals("Aria", c.getName());
        assertEquals("Dusty", ch.getName());
    }

    /** ZONE Tests */

    //Test that an empty BirdZone returns zero pets.
    @Test
    public void birdZoneTestEmpty() {
        LinkedList<Bird> birds = new LinkedList<>();
        BirdZone bz = new BirdZone(birds);
        assertEquals(0, bz.petsInZone());
    }
    @Test
    public void testBirdZoneAgeInHumanYears() {
        LinkedList<Bird> birds = new LinkedList<>();
        birds.add(new Bird("Tweety", 2, 10, new Coord2D(0,0), false));
        birds.add(new Bird("Light", 3, 10, new Coord2D(0,0), false));
        birds.add(new Bird("Mary", 0, 10, new Coord2D(0,0), false));

        BirdZone birdZone = new BirdZone(birds);

        assertEquals(18, birdZone.inHumanYears("Tweety"));
        assertEquals(27, birdZone.inHumanYears("Light"));
        assertEquals(0, birdZone.inHumanYears("Mary"));
        assertEquals(-1, birdZone.inHumanYears("John"));
    }

    @Test
    public void birdZoneTestClosestClipped(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        assertEquals("Blue", bz.closestPet(4,5));
    }

    @Test
    public void birdZoneTestHeaviest1Bird(){
        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Blue",1,3,new Coord2D(2,2),true));
        birds.add(new Bird("Light",1,5,new Coord2D(2,2),true));
        birds.add(new Bird("Mary",1,3,new Coord2D(2,2),true));
        BirdZone bz = new BirdZone(birds);
        assertEquals("Light", bz.heaviestPet().getName());
    }



    @Test
    public void sprTotalPets(){
        LinkedList<Zoneable> zones = new LinkedList<>();

        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Fluffy", 10 , 10, new Coord2D(0,0), true));
        BirdZone birdZone = new BirdZone(birds);
        zones.add(birdZone);

        LinkedList<Cat> cats = new LinkedList<>();
        cats.add(new Cat("Ginger", 10, 30, new Coord2D(0,0), true));
        CatZone catZone = new CatZone(cats);
        zones.add(catZone);

        LinkedList<Chinchilla> chinchilla = new LinkedList<Chinchilla>();
        chinchilla.add(new Chinchilla("Dusty", 10, 20, new Coord2D(0,0), 4));
        ChinchillaZone chinchillaZone = new ChinchillaZone(chinchilla);
        zones.add(chinchillaZone);

        SuperPetRescue spr = new SuperPetRescue(zones);
        assertEquals(3, spr.totalPets());

    }
    //A cat's age "in human years" is its actual age * 6
    @Test
    public void testCatageInHuman(){
        LinkedList<Cat> cats = new LinkedList<>();
        cats.add(new Cat("Ginger", 10, 30, new Coord2D(0,0), true));
        CatZone catZone = new CatZone(cats);
        assertEquals(60,catZone.inHumanYears("Ginger"));
    }

    @Test
    public void testCatAgeInHuman() {
        LinkedList<Cat> cats = new LinkedList<>();
        cats.add(new Cat("Ginger", 10, 30, new Coord2D(0,0), true));
        cats.add(new Cat("Whiskers", 2, 20, new Coord2D(1,1), false));
        cats.add(new Cat("Shadow", 5, 25, new Coord2D(2,2), true));
        cats.add(new Cat("Oreo", 7, 22, new Coord2D(3,3), false));

        CatZone catZone = new CatZone(cats);

        assertEquals(60, catZone.inHumanYears("Ginger"));
        assertEquals(12, catZone.inHumanYears("Whiskers"));
        assertEquals(30, catZone.inHumanYears("Shadow"));
        assertEquals(42, catZone.inHumanYears("Oreo"));
        assertEquals(-1,catZone.inHumanYears("White"));
    }

    @Test
    public void testCatInZone() {
        LinkedList<Cat> cats = new LinkedList<>();
        cats.add(new Cat("Ginger", 10, 30, new Coord2D(0,0), true));
        CatZone gingerzone = new CatZone(cats);
        assertEquals(1,gingerzone.petsInZone());
    }

    //Cats eat "cans" of cat food and "treats"
    @Test
    public void testCatEatsCans() {
        LinkedList<Cat> cats = new LinkedList<>();
        Cat cat = new Cat("Ginger", 10, 30, new Coord2D(0,0), true);
        cats.add(cat);
        CatZone catZone = new CatZone(cats);
        assertEquals("Cat: 0 cans, 0 treats", catZone.getPantryLabel());
        catZone.restockPetFood("cans", 10);
        assertEquals("Cat: 10 cans, 0 treats", catZone.getPantryLabel());
        catZone.feedZone();
        assertEquals("Cat: 8 cans, 0 treats", catZone.getPantryLabel());
        catZone.restockPetFood("meat", 2);
        assertEquals("Cat: 8 cans, 0 treats", catZone.getPantryLabel());
        catZone.restockPetFood("treats", 2);
        assertEquals("Cat: 8 cans, 2 treats", catZone.getPantryLabel());
        catZone.restockPetFood("cans", 6);
        assertEquals("Cat: 14 cans, 2 treats", catZone.getPantryLabel());
    }



    @Test
    public void testCatEatsNoPet() {
        Cat c = new Cat("Aria", 4, 12, new Coord2D(2, 2), false);
        assertEquals(1, c.eats("cans"));
    }

    //eats 3 units of food when eats() is called with "pellets"
    @Test
    public void testCatEatsNoPetPellets() {
        Cat c = new Cat("Aria", 4, 12, new Coord2D(2, 2), false);
        assertEquals(0, c.eats("pellets"));}

    //eats 1 "treat" plus 1 extra treat for every 8 oz they weigh when eats() is called with "treats"
    @Test
    public void testCatEatsNoPetTreats() {
        Cat c = new Cat("Aria", 4, 12, new Coord2D(2, 2), false);
        assertEquals(2, c.eats("treats"));}

    @Test
    public void testChinchillaEatsPellets() {
        Chinchilla ch = new Chinchilla("Dusty", 4, 20, new Coord2D(0, 0), 4);
        assertEquals(3, ch.eats("pellets"));
    }

    @Test
    public void testChinchillaEatsHay() {
        Chinchilla ch = new Chinchilla("Dusty", 4, 20, new Coord2D(0, 0), 4);
        assertEquals(1, ch.eats("hay"));
    }


    @Test
    public void testChinchillaZoneLabel() {
        LinkedList<Chinchilla> chinchillas = new LinkedList<>();
        Chinchilla ch = new Chinchilla("Dusty", 4, 20, new Coord2D(0, 0), 4);
        chinchillas.add(ch);
        ChinchillaZone dustyzone = new ChinchillaZone(chinchillas);
        assertEquals(1,dustyzone.petsInZone());
    }

    @Test
    public void testChinchillaZoneClosest() {
        LinkedList<Chinchilla> chinchillas = new LinkedList<>();
        Chinchilla ch = new Chinchilla("Dusty", 4, 20, new Coord2D(0, 0), 4);
        Chinchilla chC= new Chinchilla("Dummy", 4, 20, new Coord2D(1, 0), 5);
        Chinchilla chD = new Chinchilla("White", 4, 20, new Coord2D(2, 2), 4);

        chinchillas.add(ch);
        chinchillas.add(chC);
        chinchillas.add(chD);

        ChinchillaZone dustyzone = new ChinchillaZone(chinchillas);
        assertEquals("Dusty", dustyzone.petsInZone());
    }

    @Test
    public void testChinchillaTestEmpty() {
        LinkedList<Chinchilla> chinchillas = new LinkedList<>();
        ChinchillaZone chinchillaZone = new ChinchillaZone(chinchillas);
        assertNull(chinchillaZone.getPet("Hat"));
    }

    @Test
    public void testChinchillaTestInZone() {
        LinkedList<Chinchilla> chinchillas = new LinkedList<>();
        chinchillas.add(new Chinchilla("Dusty", 4, 20, new Coord2D(0, 0), 4));
        ChinchillaZone chinchillaZone = new ChinchillaZone(chinchillas);
        assertEquals("Dusty", chinchillaZone.getPet("Dusty").getName());
    }



    @Test
    public void testchinchillEats() {
        LinkedList<Chinchilla> chinchillas = new LinkedList<>();
        Chinchilla chinchilla = new Chinchilla("Ginger", 10, 30, new Coord2D(0,0), 10);
        chinchillas.add(chinchilla);
        ChinchillaZone chinchillaZone = new ChinchillaZone(chinchillas);
        chinchillaZone.feedZone();
        assertEquals("Chinchilla: 0 pellets, 0 hay", chinchillaZone.getPantryLabel());
        chinchillaZone.restockPetFood("pellets", 10).restockPetFood("hay", 2);

        assertEquals("Chinchilla: 10 pellets, 2 hay", chinchillaZone.getPantryLabel());
        chinchillaZone.feedZone();
        assertEquals("Chinchilla: 7 pellets, 1 hay", chinchillaZone.getPantryLabel());
        chinchillaZone.restockPetFood("meat", 2);
        assertEquals("Chinchilla: 7 pellets, 1 hay", chinchillaZone.getPantryLabel());
        chinchillaZone.restockPetFood("hay", 2);
        assertEquals("Chinchilla: 7 pellets, 3 hay", chinchillaZone.getPantryLabel());
        chinchillaZone.restockPetFood("pellets", 6);
        assertEquals("Chinchilla: 13 pellets, 3 hay", chinchillaZone.getPantryLabel());
    }

    @Test
    public void sprTotalPetsHeaviest(){
        LinkedList<Zoneable> zones = new LinkedList<>();

        LinkedList<Bird> birds = new LinkedList<Bird>();
        birds.add(new Bird("Fluffy", 10 , 10, new Coord2D(0,0), true));
        BirdZone birdZone = new BirdZone(birds);
        zones.add(birdZone);

        LinkedList<Cat> cats = new LinkedList<>();
        cats.add(new Cat("Ginger", 10, 30, new Coord2D(0,0), true));
        CatZone catZone = new CatZone(cats);
        zones.add(catZone);

        LinkedList<Chinchilla> chinchilla = new LinkedList<Chinchilla>();
        chinchilla.add(new Chinchilla("Dusty", 10, 20, new Coord2D(0,0), 4));
        ChinchillaZone chinchillaZone = new ChinchillaZone(chinchilla);
        zones.add(chinchillaZone);

        SuperPetRescue spr = new SuperPetRescue(zones);
        assertEquals("Ginger", spr.getHeaviestPetsName());

    }
}


