public class RaceCarMain {

    public static void main(String[] args) {
        
        //create two new race car objects with parameters for the colour and speed
        RaceCar ferrari = new RaceCar("red", 300);
        RaceCar theHomer = new RaceCar("nasty", 0);
        RaceCar ppc = new RaceCar("Transparent", 999999999);
        
        //who are the drivers for each car at the moment?
        
        ferrari.setDriver("Kyle");
        theHomer.setDriver("Bush");
        
        System.out.println("The Colour is: " + ppc.getColour());
        System.out.println("Info on the ppc" + ppc.getInfo());
        
        //who are the drivers for each car now?

        
    }

}
