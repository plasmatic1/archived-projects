/** 
 * The weapon which the hero uses
 * 
 * @author Tenniel
 */
public class Weapon {
    
    /** The name of the weapon
     */
    private String name;
    /** The attack of the weapon
     */
    private int attack;
    /** how much the weapon is worth
     */
    private double worth;
    
    private String ability;
    /** The constructor which initialize all the variables in the game
     * 
     * @param name name of the weapon
     * @param attack the amount of attack that the weapon gives
     * @param worth how much the weapon is worth
     */
    public Weapon(String name, int attack, double worth, String ability) {
        this.name = name;
        this.attack = attack;
        this.worth = worth;
        this.ability = ability;
    }

    /** For uselsss weapons
     * 
     * @param name name of the weapon
     * @param worth how much it is worth
     */
    public Weapon(String name, int attack, double worth) {
        this.name = name;
        this.worth = worth;
        //lol
        this.attack = attack;
    }

    /** returns the name of the weapon
     * 
     * @return name of the weapon
     */
    public String getName() {
        return name;
    }

    /** returns the attack of the weapon
     * 
     * @return attack of the weapon
     */
    public int getAttack() {
        return attack;
    }

    /** set the attack of the weapon
     * 
     * @param attack attack of the weapon
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /** returns how much the weapon is worth
     * 
     * @return how much the weapon is worth
     */
    public double getWorth() {
        return worth;
    }
    
    public String getAbility() {
        return ability;
    }

    /** set the amount the weapon should be worth
     * 
     * @param worth how much the weapon is worth
     */
    public void setWorth(double worth) {
        this.worth = worth;
    }
    
    public void setAll(String name, int attack, double worth, String ability){
      this.name = name;
      this.attack = attack;
      this.worth = worth;
      this.ability = ability;
    }
    
    /** string representation of the weapon
     * 
     * @return string representation of the weapon
     */
    public String toString(){
        String str = "\nWeapon Name: " + name + "\nWeapon Attack: " + attack + "\nWeapon Worth: " + worth + " gold\nWeapon Ability: " + ability;
        return str;
    }
}
