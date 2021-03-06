import java.io.*;

/** The store where the hero buys stuff from
 * only  has one item to sell (cuz im too lazy to add anymore)
 * 
 * @author Tenniel
 */
public class Store {
    
    //the price that the store has
    /** The price that the store is selling the item as
     */
    private double price;
    //stock of items;
    /** The weapon that the store is selling
     */
    private Weapon stock;
    //Customer of the store
    /** The customer in the store
     */
    private Hero customer1;

    /** The first constructor for the store
     */
    public Store(){
        price = 100.0;
    }
    
    /*
     * Second Constructor for Store for items that allow the 
     * user to input the items in
     */
    /** The constructor that initialize all the variables
     * 
     * @param input_price price of the weapon
     * @param input_stock the weapon
     * @param customer the customer
     */
    public Store(Weapon input_stock, Hero customer){
        price = input_stock.getWorth();
        stock = input_stock;
        customer1 = customer;
    }
    
    /*
     * @return the price the store has marked on their stock
     */
    /** returns the price of the weapon
     * 
     * @return price of the weapon
     */
    public double getprice(){
        return price;
    }
    
    public void equipWeapon(Weapon wpn){
      customer1.equipWeapon(wpn);
    }
    
    /*
     * @returns the amount of stock that the store has
     */
    /** returns the weapon 
     * 
     * @return weapon to be returned
     */
    public Weapon getStock(){
        return stock;
    }
    
    /*
     * @returns the customer in the store
     */
    /** returns customer that is in the store
     * 
     * @return customer
     */
    public Hero getCustomer1() {
        return customer1;
    }
    
    /*
     * set the Customer in the store
     */
    /** the customer that is coming into the store
     * 
     * @param customer1 the customer
     */
    public void setCustomer1(Hero customer1) {
        this.customer1 = customer1;
    }
    
    /** the store menu which allows the user to 
     * purchase weapons
     * 
     * @exception IOException if user enteres something completely wrong, 
     *     throw this
     */
    public void storeMenu() throws IOException{
        
        while(true){
        
            System.out.println("Our store sells : " + stock.getName());
            System.out.println("for : " + price);
            System.out.println("");
            System.out.println("Would you like to purchase" + stock.getName() + "(y/n) \n");
        
            //************ DO NOT TOUCH *******************//
            // Hook up br to standard input.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
            String decide;
            decide = br.readLine();
            //*********** ENTER MAIN PROGRAM HERE **************//
        
            if (decide.equals("y")){
                boolean bought;
                bought = sell_Stock();
                if (bought == true){
             //TODO equip the weapon to the hero
                }
                break;
            } else if(decide.equals("n")){
                System.out.println("You have bought nothing");
                break;
            } else {
                System.out.println("Please re-enter your selection");
            }
        }
    }
    
    /*
     * sell stock, if there is enough stock, sell item
     * and if there is enough money from the customer 
     */
    /** sell weapon
     * 
     * @return sell the weapon
     */
    public boolean sell_Stock(){
        if (customer1.getGold() >= price){
            customer1.loseGold(price);
            System.out.println("you have bought the " + stock.getName());
            customer1.equipWeapon(stock);
            return true;
        }else {
            System.out.println("Cannot buy the " + stock.getName());
            return false;
        }
    }
    /*
     * toString of the object
     */
    /** string representation of the store
     * 
     * @return string representation of the store
     */
    public String toString(){
        String str = "This Store Sells: " + stock.getName() + "At: " + stock.getWorth();
        //TODO
        return str;
    }
}
