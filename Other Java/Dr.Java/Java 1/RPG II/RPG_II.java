import java.io.*;
import java.util.*;

public class RPG_II{
  //Player Stat Variables
  public static String name = "";
  public static int level = 1;
  public static int health = 10;
  public static int attack = 2;
  public static int mana = 5;
  public static int xp = 0;
  public static int maxHealth = 10;
  public static int maxAttack = 2;
  public static int maxMana = 5;
  public static int itemHealthBonus = 0;
  public static int itemAttackBonus = 0;
  public static int itemManaBonus = 0;
  
  //Misc. Player Variables
  public static String chosenAction = "";
  public static String[] abilityBox = new String[3];
  public static String chosenAbility = "";
  public static String[] itemBox = new String[2];
  public static String chosenItem = "";
  public static int defFactor = 1;
  public static int defEffectLasting = 0;
  
  //enemy variables
  public static int enemyHealth = 10;
  public static int enemyAttack = 2;
  public static String enemyName = "";
  public static int enemyXp = 25;
  public static int enemyKilled = 0;
  public static int[] enemyAlreadyFought = {0,0}; //used so that you can't get an item twice
  // items are dropped from enemies
  
  //Misc. Variables
  public static int turn = 1;
  public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  public static int stopBattle = 0;

  public static void main(String args[])throws IOException{
    init();// main method
    mainMenu();
  }

  public static int attack(int damageD)throws IOException{
    enemyHealth -= damageD;//deals damage to the opponent
    checkWinLoss();
    return 0;
  }
  
  public static int defend(int damageT)throws IOException{
    if(defEffectLasting >= 1){//handles how you take damage
      if(defFactor > enemyAttack){
        health -= 1;
      }
      else{
        health -= damageT/defFactor;
      }
    }
    else{
      health -= damageT;
    }
    checkWinLoss();
    return 0;// makes you take damage and handles the forcefield effect
  }

  public static void checkWinLoss()throws IOException{//checks if you win or lost and handles dropped items
    if(health <= 0){
      System.out.println("Oh no!!! You Lost, better luck next time.");//lose
      stopBattle = 1;
    }
    else if(enemyHealth <= 0){//if you beat the final boss
      if(enemyKilled == 5){
        System.out.println(name + " has defeated CRYPT010CK3R and his army and has");
        System.out.println(" restored peace to netRealm");
        stopBattle = 1;
      }
      else if(enemyKilled == 2 && enemyAlreadyFought[0] == 0){//getting the first drop
        enemyAlreadyFought[0] = 1;
        System.out.println(name + " has defeated " + enemyName + "!!!");
        System.out.println(name + " has obtained the clandestine armour!!!");//message
        xp += enemyXp;
        System.out.println("You currently have: " + xp + " xp");
        itemBox[0] = "clandestine armour";
        checkItems();
        System.out.println("You have gained an equippable, look below on how to equip them");//instructions to items
        System.out.println("You can go into the inventory section in the main menu to set your items!!!");
        System.out.println("Try it now!");
        chosenAction = input.readLine();
        mainMenu();
        stopBattle = 1;
        health = maxHealth;
        attack = maxAttack;//sets all the variables
        mana = maxMana;
        checkLevel();
        mainMenu();
      }
      else if(enemyKilled == 4 && enemyAlreadyFought[1] == 0){//getting the next drop
        enemyAlreadyFought[1] = 1;
        System.out.println(name + " has defeated " + enemyName + "!!!");
        System.out.println(name + " has obtained the magenta stone!!!");//end display
        xp += enemyXp;
        System.out.println("You currently have: " + xp + " xp");//no instructions anymore (since I assume you are doing this 1-5)
        itemBox[1] = "magenta stone";
        checkItems();
        chosenAction = input.readLine();//sets variables again
        mainMenu();
        stopBattle = 1;
        health = maxHealth;
        attack = maxAttack;
        mana = maxMana;
        checkLevel();
        mainMenu();
      }
      else{//if there are no drops or not the final boss
        System.out.println(name + " has defeated " + enemyName + "!!!");
        System.out.println(name + " has gained " + enemyXp + "xp!!!");//display the end result
        xp += enemyXp;
        stopBattle = 1;
        health = maxHealth;
        attack = maxAttack;//sets variables
        mana = maxMana;
        checkLevel();
        mainMenu();
        System.out.println("You currently have: " + xp + " xp");
      }
    }
  }

  public static void checkLevel(){//checks your level
    if(xp >= 100 && level >= 1){
      if(level == 1){
        System.out.println("You are now level 2!!!");
        System.out.println("You have gained the heal spell for use in battle!!!");
        xp -= 100;
        maxHealth = 20 + itemHealthBonus;
        maxAttack = 4 + itemAttackBonus;//makes sure your item bonuses are not removed when you level up
        maxMana = 10 + itemManaBonus;
        level = 2;
        abilityBox[0] = "heal";//sets your ability
      }
      
      if(xp >= 150 && level >= 2){
        if(level == 2){
          System.out.println("You are now level 3!!!");
          System.out.println("You have gained the forcefield spell for use in battle!!!");
          xp -= 150;
          maxHealth = 30 + itemHealthBonus;//same general thing
          maxAttack = 6 + itemAttackBonus;
          maxMana = 15 + itemManaBonus;
          level = 3;
          abilityBox[1] = "forcefield";//same
        }
        
        if(xp >= 200 && level >= 3){
          if(level == 3){
          System.out.println("You are now level 4!!!");
          xp -= 200;
          maxHealth = 40 + itemHealthBonus;//same
          maxAttack = 8 + itemAttackBonus;
          maxMana = 20 + itemManaBonus;
          level = 4;
          }
          
          if(xp >= 250 && level == 4){
            System.out.println("You are now --{MAX LEVEL}--!!!(Level 5)");
            System.out.println("You have gained the mana blast spell for use in battle!!!");
            xp -= 250;
            maxHealth = 50 + itemHealthBonus;
            maxAttack = 10 + itemAttackBonus;//same
            maxMana = 25 + itemManaBonus;
            name = "--{" + name + "}--";//adds a cool tag to your name
            abilityBox[2] = "mana blast";
            level = 5;
          }
        }
      }
    }
    maxHealth = level*10 + itemHealthBonus;
    maxAttack = level*2 + itemAttackBonus;
    maxMana = level*5 + itemManaBonus;
    health = maxHealth;
    attack = maxAttack;//just some final checks just to make sure
    mana = maxMana;
  }
  
  public static void checkItems(){//checks your items and applies the bonuses
    if(chosenItem.equals("clandestine armour")){
      itemHealthBonus = 6;
      itemAttackBonus = 0;
      itemManaBonus = 0;//clears your other bonuses to prevent stacking cheats
    }
    else if(chosenItem.equals("magenta stone")){
      itemAttackBonus = 3;
      itemManaBonus = 5;
      itemHealthBonus = 0;
    }
    checkLevel();
  }

  public static void fightSequence()throws IOException{//the fight sequence
    System.out.println("!!!" + name + " has encountered " + enemyName + "!!!");
    while(stopBattle == 0){//loop
      chosenAction = "";
      if(turn == 1){
        System.out.println("--|What do you want to do???|--");
        System.out.println("Attack");
        System.out.println("Run Away");
        System.out.println(chosenAbility);//selection screen for your action
        
        chosenAction = input.readLine();
        
        if(chosenAction.equals("attack")){
          attack(attack);
          System.out.println(name + " has attacked " + enemyName + " for " + attack + " damage!");
          System.out.println(enemyName + " is at " + enemyHealth + "HP");
          turn = 2;//handles what to do if you attack
        }
        else if(chosenAction.equals("run away")){//handles what to do if you run away and moves you back to the main menu
          stopBattle = 1;
          fightSequence();
          mainMenu();
          System.out.println(name + " has escaped!!!");
        }
        else if(chosenAction.equals(chosenAbility)){//handles what to do if you chose an ability
          if(chosenAbility.equals("heal") && mana >= 3){//handles the heal ability
            mana -= level+1;
            health += maxHealth/2;
            if(health > maxHealth){
              health = maxHealth;
              turn = 2;
            }
            System.out.println(name + " has healed by: " + maxHealth/2 + " health and used " + (level+1) + " mana!!!");
            System.out.println(name + " has " + health + "/" + maxHealth + "HP");
            System.out.println(name + " has " + mana + "/" + maxMana + "MP");//reply on stats after the ability has been executed
            turn = 2;
          }
          else if(chosenAbility.equals("forcefield") && mana >= 7){//handles the forcefield ability
            mana -= level*2;
            defFactor = level;
            defEffectLasting = level-1;
            turn = 2;
            System.out.println(name + " is using a forcefield to only take " + "1/" + level + " damage for " + defEffectLasting + " turns!!!");
            System.out.println(name + " has used " + level*2 + " mana!!!");//same
            System.out.println(name + " has " + mana + "/" + maxMana + "MP");
          }
          else if(chosenAbility.equals("mana blast") && mana >= 7){//handles the mana blast ability
            mana -= 7;
            attack(30);
            turn = 2;
            System.out.println(name + " blasts " + enemyName + " with a mana blast that does 30 damage and has used 7 mana!!!");
            System.out.println(name + " has " + mana + "/" + maxMana + "MP");
            System.out.println(enemyName + " is at " + enemyHealth + "HP!!!");//same
          }
          else{//tells you that you can't preform that action if you don't have enough mana to do it
            System.out.println("You do not have sufficent mana to preform that action");
          }
        }
        else{//goes back if you didn't choose a valid choice
          System.out.println("That is not a valid choice");
          turn = 1;
        }
        
    }
      else if(turn == 2){//used if the enemy attacks
        if(defFactor >= 1 && defEffectLasting >= 1){
          if(defFactor > enemyAttack){//handles what to say while the player has the "forcefield" effect
            System.out.println("!!!" + enemyName + " attacks for 1 damage!!!");
          }
          else{
            System.out.println("!!!" + enemyName + " attacks for " + (enemyAttack/defFactor) + " damage!!!");
          }
        }
        else{
          System.out.println("!!!" + enemyName + " attacks for " + enemyAttack + " damage!!!");
        }
        defend(enemyAttack);
        System.out.println(name + " is at " + health + "/" + maxHealth + "HP");
        turn = 1;//does some variable changes
        checkWinLoss();
        if(defEffectLasting >= 1){
          defEffectLasting--;
          System.out.println(name + " has " + defEffectLasting +  " turns of the forcefield effect left!!!");
        }
      } 
  }
 }

  public static void fightEnemyHub()throws IOException{//let's you choose an enemy and sets their stats
    chosenAction = "";
    System.out.println("--|Welcome To The Enemy Map|--");
    System.out.println("Your enemies are:");
    System.out.println("Basic Malware");
    System.out.println("Clandestine Adware");
    System.out.println("Dangerous Trojan");
    System.out.println("-[CODE RED]-");
    System.out.println("~~<(CRYPT010CK3R)>~~");
    System.out.println("You can also go back");
    chosenAction = input.readLine();
    chosenAction = chosenAction.toLowerCase();
    if(chosenAction.equals("basic malware")){
      enemyHealth = 10;
      enemyAttack = 2;
      enemyName = "Basic Malware";//set's the enemy variables and commences the fight sequence
      enemyXp = 2500;
      enemyKilled = 1;
      stopBattle = 0; 
      fightSequence();
    } 
    else if(chosenAction.equals("clandestine adware")){
      enemyHealth = 40;
      enemyAttack = 3;
      enemyName = "Clandestine Adware";
      enemyXp = 75;
      enemyKilled = 2;
      stopBattle = 0;
      fightSequence();
    }
    else if(chosenAction.equals("dangerous trojan")){
      enemyHealth = 30;
      enemyAttack = 7;
      enemyName = "Dangerous Trojan";
      enemyXp = 200;
      enemyKilled = 3;
      stopBattle = 0;
      fightSequence();
    }
    else if(chosenAction.equals("code red")){
     enemyHealth = 75;
     enemyAttack = 10;
     enemyName = "Code Red";
     enemyXp = 400;
     enemyKilled = 4;
     stopBattle = 0;
     fightSequence();
    }
    else if(chosenAction.equals("crypt010ck3r")){
      enemyHealth = 130;
      enemyAttack = 10;
      enemyName = "CRYPT010CK3R";
      enemyXp = 0;
      enemyKilled = 5;
      stopBattle = 0;
      fightSequence();
    }
    else if(chosenAction.equals("back")){//let's you go back
      mainMenu();
    }
    else{
      System.out.println("That is not a valid option");
      System.out.println("If you are attempting to fight the boss or a miniboss, do not include the design outside of their names");
      System.out.println("If you want to go back, type \"back\" in the action input box");
      chosenAction = input.readLine();
      fightEnemyHub();
    }
 }
  //method that controls the main menu selection screen, inventory, and stat displays
  public static void mainMenu()throws IOException{//main hub
    chosenAction = "";
    System.out.println("--|Welcome To The Main Menu|--");
    System.out.println("Your actions are:");
    System.out.println("attack");
    System.out.println("inventory");     
    System.out.println("stats");
    chosenAction = input.readLine();
    chosenAction = chosenAction.toLowerCase();
    
    if(chosenAction.equals("attack")){
      fightEnemyHub();
    }
    else if(chosenAction.equals("inventory")){//let's you choose your stuff
      chosenAction = "";
      System.out.println("--[WHAT DO YOU WANT TO DO???]--");
      System.out.println("Items");
      System.out.println("Abilities");
      System.out.println("You can also go back");
      chosenAction = input.readLine();
      chosenAction = chosenAction.toLowerCase();
      
      if(chosenAction.equals("abilities")){//choose your abilities
        System.out.println("--[CHOOOSE YOUR ACTIVE ABILITY]--");
        System.out.println("Your selected ability currently is " + chosenAbility);
        System.out.println("Your available abilities are: ");
        System.out.println(abilityBox[0]);
        System.out.println(abilityBox[1]);
        System.out.println(abilityBox[2]);
        System.out.println("Choose an ability or go back with \"back\"");
        chosenAction = input.readLine();
        chosenAction = chosenAction.toLowerCase();
        
        if(chosenAction.equals(abilityBox[0]) || chosenAction.equals(abilityBox[1]) || chosenAction.equals(abilityBox[2])){
          chosenAbility = chosenAction;
          System.out.println("You ability is now" + " " + chosenAbility);
          mainMenu();
        }
        else if(chosenAction.equals("back")){
          System.out.println("Going back...");
          mainMenu();
        }
        else{//goes back if your ability isn't valid
          System.out.println("that is not a valid ability");
          chosenAction = input.readLine();//input box to pause game
          mainMenu();
        }
      }
      else if(chosenAction.equals("items")){//choose your items
        System.out.println("--[CHOOOSE YOUR ACTIVE ITEM]--");
        System.out.println("Your active item is: " + chosenItem);
        System.out.println("You currently have these items: ");
        System.out.println(itemBox[0]);
        System.out.println(itemBox[1]);
        chosenAction = "";
        System.out.println("Pick an item or go back with \"back\"");
        chosenAction = input.readLine();
        chosenAction = chosenAction.toLowerCase();
        
        if(chosenAction.equals(itemBox[0]) || chosenAction.equals(itemBox[1])){
          chosenItem = chosenAction;//makes sure what item you picked
          checkItems();
        }
        else if(chosenAction.equals("back")){
          mainMenu();
        }
        else{
          System.out.println("That is not a valid option");
          chosenAction = input.readLine();//same thing
          mainMenu();
        }
      }
      else if(chosenAction.equals("back")){//if you want to go back
        System.out.println("going back...");
        mainMenu();
      }
      else{
        System.out.println("That is not a valid option");
        chosenAction = input.readLine();//same thing
        mainMenu();
      }
 }
        else if(chosenAction.equals("stats")){//displays your stats
            System.out.println("--[YOUR STATS ARE]--");
            System.out.println("Your name is: " + name);
            System.out.println("Your level is: " + level);
            System.out.println("Your health level is: " + health);
            System.out.println("Your strength level is: " + attack);
            System.out.println("Your mana storage is: " + mana);
            System.out.println("Your item health bonus is: " + itemHealthBonus);
            System.out.println("Your item attack bonus is: " + itemAttackBonus);
            System.out.println("Your item manac bonus is: " + itemManaBonus);
            System.out.println("You currently have: " + xp + " xp");
            chosenAction = input.readLine();//uses an input box to pause the game
            mainMenu();
        }
        else{
            System.out.println("Please select a valid option");
            mainMenu();//if you be a jerk and select an invalid option
        }
    }

    public static void init()throws IOException{//intro and storyline
        System.out.println("Welcome to the netRealm");
        System.out.println("You have been chosen to be the fighter to face the dangers of this realm");
        System.out.println("Oh no!  I must have forgotten something while I was talking to you...");
        System.out.println("What is your name???");
        name = input.readLine();
        System.out.println("Oh!, so your name is " + name);
        System.out.println("Ok, " + name + " let's get you started!");
        chosenAction = input.readLine();
        chosenAction = "";
    }
}

