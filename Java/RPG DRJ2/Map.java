public class Map {

    /** The name of the location
     */
    private String name;
    /** The store that is located at this location
     */
    private Store buildings;
    /** The monster that inhabit this location
     */
    private Monster monster;

    /** The constructor to initialize all the variables
     * 
     * @param name name of the location
     * @param buildings store in the location
     * @param monster monster in the location
     */
    public Map(String name, Store buildings, Monster monster) {
        this.name = name;
        this.buildings = buildings;
        this.monster = monster;
    }

    /** returns the store in the location
     * 
     * @return the store
     */
    public Store getBuildings() {
        return buildings;
    }

    /** returns the monster in the location
     * 
     * @return monster that live here
     */
    public Monster getMonster() {
        return monster;
    }

    /** Returns the name of the place
     * 
     * @return name of the location
     */
    public String getName() {
        return name;
    }
    
    /** String representation of the location
     * 
     * @return String representation of the location
     */
    public String toString(){
        String str = "Name: " + name + "\nMonsters: " + monster.getName() + "\nStore:" + buildings.toString();
        //TODO
        return str;
    }
    
}
