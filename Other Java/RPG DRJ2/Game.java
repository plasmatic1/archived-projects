import java.io.*;
import java.util.*;

/** The actual game class that runs the game
 * Most of your work will be in here!
 * 
 * @author Tenniel
 */
public class Game {
    public int attack = 0;
    public int monster_attack;

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
    
    private Monster CurrentMonster;
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
    private Store CurrentStore;
    private Boolean finished;
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
        this.hardMonster = hardMonster;
        this.BossMonster = BossMonster;
        this.hardBossMonster = hardBossMonster;
        this.start = Starting;
        this.middle1 = middle1;
        this.middle2 = middle2;
        this.finale = finale;
        this.Current = Starting;
        CurrentStore = Current.getBuildings();
        CurrentMonster = Current.getMonster();
        finished = false;
    }
    
    /** The actual game is placed in here
     * 
     * @exception IOException If reading from input doesnt work, throw this 
     *     exception
     */
    public void actualGameLoop() throws IOException{
        
        //************ DO NOT TOUCH *******************//
        // Hook up br to standard input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //write main program here
        
        int decide;
        
        while (hero.getHealth() > 0 && finished == false){
            System.out.println("");
            System.out.println("Your Hero stats are");
            System.out.println(hero.toString());
            System.out.println("");
            
            System.out.println("what would you like to do?");
            System.out.println("(1)     Fight Monster " + CurrentMonster.getName());
            System.out.println("(2)     Go to store selling " + Current.getBuildings().getStock().getName());
            System.out.println("(3)     Heal yourself (only " + Heal + " times left)");
            System.out.println("(4)     Change Your Area");
            System.out.println("(5)     Spellbook");
            //TODO, allows the user to chose a place to go
            //if user is in noob area, then you should be allows to go to 
            //boss area, and vice versa
            
            //to make it look nicer
            System.out.println("");
        
            decide = Integer.parseInt(br.readLine());
            //*********** ENTER MAIN PROGRAM HERE **************//
       
            if (decide == 1){
                fightMonster(monster);
            }
            else if (decide == 2){
                Current.getBuildings().storeMenu();
            }
            else if (decide == 3 && Heal > 0){
                hero.setHealth(hero.getStrength()* 2);
                Heal--;
            }
            else if (decide == 4){
                System.out.println("Would You like to go to...");
                System.out.println("(1) " + start.getName());
                System.out.println("(2) " + middle1.getName());
                System.out.println("(3) " + middle2.getName());
                System.out.println("(4) " + finale.getName());
                
                decide = Integer.parseInt(br.readLine());
                if(decide == 1){
                  Current = start;
                }
                else if(decide == 2){
                  Current = middle1;
                }
                else if(decide == 3){
                  Current = middle2;
                }
                else if(decide == 4){
                  Current = finale;
                }
                
                CurrentMonster = Current.getMonster();
                CurrentStore = Current.getBuildings();
                System.out.println("In this area... There are:");
                System.out.println(Current.toString());
                System.out.println("");
                
            }
            else if(decide == 5){
              System.out.println("Spellbook");
              System.out.println("-----------------");
              System.out.println("STR = your strength stat");
              System.out.println("Intelligence is mana");
              
              System.out.println("-----------------");
              System.out.println("Perice Spell");
              System.out.println("Bypasses Defence of opponent and autoattacks");
              System.out.println("Costs STR amount of health");
              System.out.println("Cast With: \"> O' - H $ D\"");
              
              System.out.println("-----------------");
              System.out.println("Heal Spell");
              System.out.println("Heals you HP by STR*2");
              System.out.println("Costs STR amount of intelligence");
              System.out.println("Cast With: \"> O + I $ H\"");
              
              System.out.println("-----------------");
              System.out.println("Weaken Spell");
              System.out.println("Lowers Attack of opponent by STR");
              System.out.println("Costs STR amount of intelligence");
              System.out.println("Cast With: \"> O' - I $ A\"");
            }
        }
        
        if (hero.getHealth() > 0){
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(hero.getName() + " Wins");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("You have saved VulnerableLand");
            System.out.println("Thank you for your help");
            System.out.println("See you nex time");
            System.out.println("Farewell!");
        } 
        else {
            System.out.println("You have died");
        }
    }
    
    /** When we fight the monster, we go here
     * 
     * @param monster The monster the hero is fighting
     * @exception IOException When reading from input doesnt work
     */
    public void fightMonster(Monster monster)throws IOException{
        
        int decide2;
        Random generator = new Random();
        String menu = "(1)      attack \n(2)      suicide \n(3)      " +
                "Activate Ability \n"; 
        
        //************ DO NOT TOUCH *******************//
        // Hook up br to standard input.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ///*********** ENTER MAIN PROGRAM HERE **************//
        while (hero.getHealth() >= 0 && CurrentMonster.getLife() >= 0){
            
            System.out.println(menu);
            
            decide2 = Integer.parseInt(br.readLine());

            if (decide2 == 1){
                
                //if hero has weapons
                if (hero.getWeapon() != null){
                    attack = generator.nextInt(hero.getStrength() + hero.getWeapon().getAttack());
                    if(hero.getAbility().equals("LifeSteal")){
                      System.out.println("");
                      hero.damage(-10);
                      System.out.println(hero.getName() + " has used the Life Steal ability!!!");
                      System.out.println(hero.getName() + " has healed by 10 health!!!");
                      System.out.println("");
                      System.out.println("Your new stats are:");
                      System.out.println(hero.toString());
                    }
                    else if(hero.getAbility().equals("Bereserk") && hero.getHealth() < 15){
                      System.out.println("");
                      System.out.println(hero.getName() + " has used the breserk ability!!!");
                      CurrentMonster.loseLife(150);
                    }
                }
                //if hero doesnt have weapon
                else {
                    attack = generator.nextInt(hero.getStrength()) + 1; 
                }
            
                
                CurrentMonster.loseLife((attack - CurrentMonster.getDefence()));
                System.out.println(hero.getName() + " did " + (attack - CurrentMonster.getDefence()) + " damage to " + CurrentMonster.getName());
                System.out.println("");
                System.out.println("New Monster's Stats");
                System.out.println(CurrentMonster.toString());
                System.out.println("");
                //when the hero attacks the monster
            }
            else if(decide2 == 2){
                System.out.println("You'd better have a lot of life");
                System.out.println("");
            }
            else if(decide2 == 3){
                System.out.println("");
                System.out.println("You have activated an ability!!!");
                System.out.println("");
                handleAbilities();
            }
                
            //Monster attack i this variable is 1
            monster_attack = generator.nextInt(2) + 1;
            
            if(monster.getLife() > 0 && monster_attack == 1){
              System.out.println("");
              hero.damage(CurrentMonster.getAttack());
              System.out.println(hero.getName() + " has been damaged by " + CurrentMonster.getName() + " for " + CurrentMonster.getAttack() + " damage!!!");
              System.out.println("Your new stats are:");
              System.out.println(hero.toString());
                //TODO when the monster attacks the hero
            }
            else{
                //monster doesnt attack hero 
                System.out.println("");
                System.out.println("The monster is scared of " + hero.getName());
            }
           
        }
        
        if (CurrentMonster.getLife() <= 0){
            System.out.println("");
            System.out.println(hero.getName() + " has killed " + CurrentMonster.getName());
            System.out.println(hero.getName() + " has gained " + CurrentMonster.getXp() + "XP");
            System.out.println(hero.getName() + " has gained " + CurrentMonster.getGold() + "gold");
            System.out.println("");
          
            hero.addGold(CurrentMonster.getGold());
            playerAddXp(CurrentMonster.getXp());
            CurrentMonster.resetLife();
            hero.resetHealth();
            
            if(CurrentMonster.getName().equals("Corrupter of Worlds")){
              finished = true;
              endGame();
            }
        }
        
    }
    
    /** If player has defeated the monster, we add XP to the hero
     * 
     * @param xp The experience of the Hero to be added
     */
    public void playerAddXp(int xp){
        hero.addXp(xp);
        hero.checkLevelUp();
        //TODO add the player xp if hero beats monsters
        //Level up hero if reaches a certain condition 
    }
    
    public void endGame(){
      System.out.println("...");
    }
    
    public void handleAbilities() throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      Random generator = new Random();
      
      String spell;
      System.out.println("Cast the proper spell!!!");
      spell = br.readLine();
      
      if(spell.equals("> O' - L $ D")){
        if(hero.getHealth() > hero.getStrength()){
          System.out.println(hero.getName() + " has used the sacrifical altar ability!!!\n" + hero.getName() + " will inflict pericing damage for a price of health");
                if(hero.getWeapon() != null){
                  attack = hero.getStrength() + 1 + hero.getWeapon().getAttack();
                
                  System.out.println("");
                  System.out.println(hero.getName() + " did " + attack + " damage to " + CurrentMonster.getName());
                  System.out.println("");
                  System.out.println("You now have: ");
                  System.out.println(hero.toString());
                  System.out.println("");
                }
                else{
                  attack = hero.getStrength() + 1;
                }
                CurrentMonster.loseLife(attack);
                hero.damage(hero.getStrength());
              
                System.out.println("");
                System.out.println(hero.getName() + " did " + attack + " damage to " + CurrentMonster.getName());
                System.out.println("");
                System.out.println("You now have: ");
                System.out.println(hero.toString());
                System.out.println("");
                System.out.println("Enemy has: ");
                System.out.println(CurrentMonster.toString());
                System.out.println("");
        }
      }
        
      else if(spell.equals("> O + I $ L")){
        if(hero.getInt() > hero.getStrength()){
          System.out.println(hero.getName() + " has activated the heal ability and will now heal by: " + (hero.getStrength()*2) + " health");
          System.out.println(hero.getName() + " has used " + hero.getStrength() + " Intelligence!!!");
          
          hero.heal((hero.getStrength()*2));
          hero.loseInt(hero.getStrength());
          
          System.out.println(hero.getName() + " now has:");
          System.out.println(hero.toString());
        }
        else{
          System.out.println("You do not have enough intelligence to activate the ability!!! (You need " + hero.getStrength() + " health)");
          System.out.println("AutoAttacking enemy...");
          System.out.println("");
          
          if(hero.getWeapon() != null){
            attack = generator.nextInt(hero.getStrength() + hero.getWeapon().getAttack());
          }
          else{
            attack = generator.nextInt(hero.getStrength()) + 1; 
          }
          CurrentMonster.loseLife((attack - CurrentMonster.getDefence()));
          System.out.println(hero.getName() + " did " + (attack - CurrentMonster.getDefence()) + " damage to " + CurrentMonster.getName());
        }
      }
      else if(spell.equals("> O' - I $ A")){
        if(hero.getInt() > hero.getStrength()){
          System.out.println(hero.getName() + " has used the weaken ability!!!");
          System.out.println(hero.getName() + " has weakened the monster's attack by " + hero.getStrength());
          
          CurrentMonster.lowerAttack(hero.getStrength());
          hero.loseInt(hero.getStrength());
          
          System.out.println("Your new stats are:");
          System.out.println(hero.toString());
          System.out.println("");
          System.out.println("The Monster's new stats are...");
          System.out.println(CurrentMonster.toString());
          System.out.println("");
        }
        else{
          System.out.println("You do not have enough intelligence to activate the ability!!! (You need " + hero.getStrength() + " health)");
          System.out.println("AutoAttacking enemy...");
          System.out.println("");
          
          if(hero.getWeapon() != null){
            attack = generator.nextInt(hero.getStrength() + hero.getWeapon().getAttack());
          }
          else{
            attack = generator.nextInt(hero.getStrength()) + 1; 
          }
          CurrentMonster.loseLife((attack - CurrentMonster.getDefence()));
          System.out.println(hero.getName() + " did " + (attack - CurrentMonster.getDefence()) + " damage to " + CurrentMonster.getName());
        }
      }
  }
}
