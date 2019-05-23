/** 
 * The hero Class, where the hero attributes are stored
 * 
 * @author Tenniel
 */
public class Hero {

    /** The name of the hero
     */
    private String name;
    /** The strength of the hero
     */
    private int strength;
    //private int intelligence;
    /** The weapon that the hero carries
     */
    private Weapon weapon;
    /** The gold that the hero carries
     */
    private double gold;
    /** The level of the hero
     */
    private int level;
    /** The life of the hero
     * life = strength*2
     */
    private int health;
    /** The factor in which the hero levels up by
     */
    private int levelUp = 100;
    /** The experience of the hero
     */
    private int xp;
    private int totalStrength;

    /** The constructor of hero
     * Where we init all the attributes
     * 
     * @param name The name of the hero
     * @param strength The strength of the hero
     * @param intelligence The intelligence of the hero
     */
    public Hero(String name, int strength/*, int intelligence*/, int health) {
        this.name = name;
        this.strength = strength;
       //this.intelligence = intelligence;
        this.health = health;
        gold = 0;
        weapon = null;
        level = 1;
    }

    /** returns the name of the hero
     * 
     * @return name of the hero
     */
    public String getName() {
        return name;
    }
    
    /** returns the gold which the hero has
     * 
     * @return gold which the hero has
     */
    public double getGold() {
        return gold;
    }
    
    public void loseGold(double gold){
      this.gold -= gold;
    }

    /** Add gold to the hero
     * 
     * @param gold the amount of gold which is added to the hero
     */
    public void addGold(double gold) {
        this.gold += gold;
    }

    /** lose gold
     * this is for the store
     * 
     * @param gold the amount of gold which the hero should lose
     */
    
    public String getAbility(){
      return weapon.getAbility();
    }
    
    public void equipWeapon(Weapon inpWpn){
      weapon = inpWpn;
    }
    /** returns the intelligence of the hero
     * 
     * @return the intelligence of the hero
     */
   // public int getIntelligence() {
     //   return intelligence;
   // }

    /** add intelligence of the hero
     * 
     * @param intelligence the amount of intelligence to add
     
    public void addIntelligence(int intelligence) {
        this.intelligence = this.intelligence + intelligence;
    }*/

    /** returns the strength
     * 
     * @return the strength of the hero
     */
    public int getStrength() {
        return strength;
    }

    /** add strength to the hero
     * 
     * @param strength the strength to be added
     */
    public void addStrength(int strength) {
        this.strength = this.strength + strength;
    }

    /** returns the weapon of the hero
     * 
     * @return weapon of the hero
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /** equips the weapon to the hero
     * 
     * @param weapon the weapon to be equiped
     */

    /** returns the level of the hero
     * 
     * @return level of the hero
     */
    public int getLevel() {
        return level;
    }

    /** sets the level of the hero
     * 
     * @param level set the level parameter to this
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /** returns the life of the hero
     * 
     * @return life of the hero
     */
    public int getHealth() {
        return health;
    }

    /** set the life of the hero
     * 
     * @param life set the life of the hero
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    /** the amount of life the hero will lose
     * when the monster attacks him
     * 
     * @param life amount of life that the hero is suppose to lose
     */
    public void damage(int health){
        this.health += health;
    }

    /** The factor in which the hero should level up by
     * should be levelUp^level
     * 
     * @return the levelUp factor
     */
    public int getLevelUp() {
        return levelUp;
    }

    /** returns the experience of the Hero
     * 
     * @return experience of the Hero
     */
    public int getXp() {
        return xp;
    }

    /** Add experience to the Hero
     * 
     * @param xp Add experience to the Hero
     */
    public void addXp(int xp) {
        this.xp += xp;
    }

    /** The toString of the class
     * 
     * @return the string representation of Hero
     */
    public String toString() {
        String str;
        str = "Name: " + name + "\nHealth: " + health + "\nAttack:" + strength + "\nGold: " + gold + "\nLevel: " + level + weapon.toString();
        return str;
    }

}
