import java.io.*;
import java.util.*;

/** The actual game class that runs the game
 * Most of your work will be in here!
 * 
 * @author Tenniel
 */
public class Game {

    /** The hero of this RPG game
     */
    private Hero hero;
    /** The monster that the hero is currently fighting with
     */
    private Monster monster;
    private Monster hardMonster;
    /** The boss
     */
    private Monster BossMonster;
    private Monster hardBossMonster;
    /** The starting location of the game
     */
    private Map start;
    /** The final location of the game
     */
    private Map middle1;
    
    private Map middle2;
    private Map finale;
    /** The current location of the player
     */
    private Map Current;
    /** amount of times u can heal
     */
    private int Heal = 5;
    //private int decide;

    /** to generate the game starting variables
     * 
     * @param hero The hero
     * @param monster The current monster
     * @param BossMonster The boss monster
     * @param Starting The Starting location
     * @param Final The Final Location
     */
    public Game(Hero hero, Monster monster, Monster hardMonster, Monster BossMonster, Monster hardBossMonster, Map Starting, Map middle1, Map middle2, Map finale) {
        this.hero = hero;
        this.monster = monster;
        this.hardMonster = monster;
        this.BossMonster = BossMonster;
        this.hardBossMonster = BossMonster;
        this.start = Starting;
        this.middle1 = middle1;
        this.middle2 = middle2;
        this.finale = finale;
        this.Current = Starting;
    }
    
    /** The actual game is placed in here
     * 
     * @exception IOException If reading from input doesnt work, throw this 
     *     exception
     */
    public void actualGameLoop() throws IOException{
        
        Map destination = null;
        //************ DO NOT TOUCH *******************//
        // Hook up br to standard input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //write main program here
        
        int decide;
        
        while (hero.getHealth() >= 0 && BossMonster.getLife() >= 0){
            //infinite amount of bad guys
            if (monster.getLife() == 500){
                //do nothing this is the boss
            }else {
            monster.setLife(20);
            }
            
            System.out.println("Your Hero stats are");
            System.out.println(hero.toString());
            
            System.out.println("what would you like to do?");
            System.out.println("(1)     fight Monster " + monster.getName());
            System.out.println("(2)     go to store selling " + 
                    Current.getBuildings().getStock().getName());
            System.out.println("(3)     heal yourself (only " + Heal + " times left)");
            //TODO, allows the user to chose a place to go
            //if user is in noob area, then you should be allows to go to 
            //boss area, and vice versa
            
            //to make it look nicer
            System.out.println("");
        
            decide = Integer.parseInt(br.readLine());
            //*********** ENTER MAIN PROGRAM HERE **************//
       
            if (decide == 1){
                fightMonster(monster);
            }else if (decide == 2){
                Current.getBuildings().storeMenu();
            }else if (decide == 3 && Heal > 0){
                hero.setHealth(hero.getStrength()* 2);
                Heal --;
            }else if (decide == 4){
                Current = destination;
                monster = Current.getMonster();
            }
        }
        
        if (hero.getHealth() > 0){
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(hero.getName() + "Wins");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            System.out.println("You have died");
        }
    }
    
    /** When we fight the monster, we go here
     * 
     * @param monster The monster the hero is fighting
     * @exception IOException When reading from input doesnt work
     */
    public void fightMonster(Monster monster)throws IOException{
        int attack = 0;
        int monster_attack;
        int decide2;
        Random generator = new Random();
        String menu = "(1)      attack \n(2)      suicide \n(3)      " +
                "hope for the best(still suicide) \n"; 
        
        //************ DO NOT TOUCH *******************//
        // Hook up br to standard input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ///*********** ENTER MAIN PROGRAM HERE **************//
        while (hero.getHealth() >= 0 && monster.getLife() >= 0){
            
            System.out.println(menu);
            
            decide2 = Integer.parseInt(br.readLine());

            if (decide2 == 1){
                
                //if hero has weapons
                if (hero.getWeapon() != null){
                    attack = generator.nextInt(hero.getStrength() +
                            hero.getWeapon().getAttack()) + 1;
                }
                //if hero doesnt have weapon
                else {
                    attack = generator.nextInt(hero.getStrength()) + 1; 
                }
                //TODO
                //when the hero attacks the monster
            
            }else {
                System.out.println("You'd better have a lot of life");
            }
                
            //Monster attack i this variable is 1
            monster_attack = generator.nextInt(2) + 1;
            
            if (monster.getLife() > 0 && monster_attack == 1){
                //TODO when the monster attacks the hero
            }
            else {
                //monster doesnt attack hero 
                System.out.println("The monster is scared of " + hero.getName());
            }
           
        }
        
        if (monster.getLife() <= 0){
            hero.addGold(monster.getGold());
            playerAddXp(monster.getXp());
        }
        
    }
    
    /** If player has defeated the monster, we add XP to the hero
     * 
     * @param xp The experience of the Hero to be added
     */
    public void playerAddXp(int xp){
        hero.addXp(xp);
        //TODO add the player xp if hero beats monsters
        //Level up hero if reaches a certain condition 
    }
    
}
