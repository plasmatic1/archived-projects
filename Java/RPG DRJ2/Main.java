import java.io.*;
/** This class will initialize all the objects and allow
 * the game to begin.  If you want to cheat, do it here!
 * 
 * @author 04chante
 */
public class Main {

    /** This class will initialize all the objects and allow
     * the game to begin.  If you want to cheat, do it here!
     * 
     * @param args the command line arguments
     * @exception IOException if not able to read name, throw this exception
     */
    public static void main(String[] args)throws IOException {
        
        String name;
        System.out.println("Welcome warrior, Please state your name \n");
        
        //************ DO NOT TOUCH *******************//
        // Hook up br to standard input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
        String decide;
        decide = br.readLine();
        //*********** ENTER MAIN PROGRAM HERE **************//
    
        name = decide;
        
        Hero hero = new Hero(name, 3, 999, 999999);
        
        System.out.println("Enter your story here");
        System.out.println("\nPrepare for your adventure\n");
        
        Monster simpleMonster = new Monster("Basic Zomble", 2, 1, 8, 25.0, 30);
        Monster simpleBoss = new Monster("Zomble Overlord", 7, 4, 100, 150.0, 100);
        Monster strongMonster = new Monster("Armoured Zomble", 3, 3, 75, 35.0, 75);
        Monster strongBoss = new Monster("Corrupter of Worlds", 10, 15, 200, 0.0, 0);
        
        Weapon shortsword = new Weapon("short sword", 7, 25);
        Weapon snakesword = new Weapon("Snake Sword", 45, 60, "LifeSteal");
        Weapon godlysword = new Weapon("Godly sword", 80, 90);
        Weapon ultimatesword = new Weapon("Ultimate sword", 125, 150, "Breserk");
        
        Store store1 = new Store(shortsword, hero);
        Store store2 = new Store(snakesword, hero);
        Store store3 = new Store(godlysword, hero);
        Store store4 = new Store(ultimatesword, hero);
        
        Map NoobArea = new Map("Noobland", store1, simpleMonster);
        Map MiniBossArea = new Map("Gates of Hell", store2, simpleBoss);
        Map HardArea = new Map("Kingdom of Hell", store3, strongMonster);
        Map FinalArea = new Map("Below the Void", store4, strongBoss);
        
        Game game = new Game(hero, simpleMonster, strongMonster, simpleBoss, strongBoss, NoobArea, MiniBossArea, HardArea, FinalArea);
        game.actualGameLoop(); 
    }

}
