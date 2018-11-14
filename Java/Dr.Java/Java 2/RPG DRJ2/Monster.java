/** 
 * The monster class, all monster attribute is located here
 * 
 * @author Tenniel
 */
public class Monster {

    /** The name of the monster
     */
    private String name;
    /** The attack and defence of the monster
     */
    private int attack;
    private int defence;
    /** the life of the monster
     */
    private int life;
    /** the special (if you want to improve on this, here
     * is the place to do it)
     */
    /** The gold that the monster gives out
     * Im not entirely sure why RPG game always 
     * have money in a dead monster lol
     */
    private double gold;
    /** The experience that the monster gives
     */
    private int xp;

    /** The constructor to initialize all the variables
     * 
     * @param name name of the monster
     * @param attack_defence attack and defence of the monster
     * @param life the life of the monster
     * @param special read comment above
     * @param gold gold that the monster returns
     * @param xp XP that the monster returns
     */
    public Monster(String name, int attack, int defence, int life, double gold, int xp) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.life = life;
        this.gold = gold;
        this.xp = xp;
    }

    /** returns the life of the monster
     * 
     * @return life of the monster
     */
    public int getLife() {
        return life;
    }
    
    public String getName() {
        return name;
    }

    /** sets the life of the monster
     * 
     * @param life life that the monster should be at
     */
    public void setLife(int life) {
        this.life = life;
    }
    
    /** when fighting, the monster will lose life 
     * 
     * @param attack the amount of life that the monster should lose
     */
    public void loseLife(int attack){
        life -= attack;
    }

    /** Netbeans auto-generated this
     * 
     * @return the special of the Monster
     */

    

    /** returns the attack and defence of the monster
     * 
     * @return attack and defence of the monster
     */
    public int getDefence() {
        return defence;
    }
    
    public int getAttack() {
        return attack;
    }

    /** returns the name of the monster
     * 
     * @return name of the monster
     */

    /** returns the amount of gold that the monster should give back
     * 
     * @return the amount of gold that the monster should return
     */
    public double getGold() {
        return gold;
    }

    /** the amount of experience that the monster gives back
     * 
     * @return the amount of xp that the monster gives back
     */
    public int getXp() {
        return xp;
    }
    
    /** String representation of the monster
     * 
     * @return the string representation of the monster
     */
    public String toString(){
        String str = "";
        return str;
    }
    
}
