
public class Store {
   
    private String type;            //the type of store
    private double price;           //the price of the stock
    private int stock;              //stock of items;
    private Customer customer1;     //Customer of the store
   
    /* default Store constructor
     * by default the store will be Computer Store, 
     * has 50 stock, worth $100 each
     */
    public Store(){
        type = "Computer Store";
        price = 100.0;
        stock = 50;
        customer1 = null;
    }
    
    /* Second Constructor for Store for items that allow the 
     * user to input the items in
     */
    public Store(String input_type, double input_price, int input_stock){
        type = input_type;
        price = input_price;
        stock = input_stock;
        customer1 = null;
    }
    
    public String getType (){
        return type; //returns the type of store
    }
    
    public double getprice(){
        return price; //returns the price the store has marked on their stock
    }
    
    public int getStock(){
        return stock; //returns the amount of stock that the store has
    }
    
    public Customer getCustomer1() {
        return customer1; //returns the customer in the store
    }
    
    public void setCustomer1(Customer customer1) {
        this.customer1 = customer1; // set the customer in the store
    }
    
    /* TO DO: method to sell stock
     * if there is enough stock and the customer has enough money, sell item
     */
    public void sell_Stock(int howMany){
      Double cusMoney;
      cusMoney = customer1.getMoney();
      
      if(stock >= howMany && cusMoney >= (price*howMany)){
        stock -= howMany;
        cusMoney -=(price*howMany);
        
        customer1.setMoney(cusMoney);
        System.out.println("The transaction between " + "\"" + customer1.getName() + "\"" + " and store \"" + type + "\" had succeeded!!");
      }
      else{
        System.out.println("The Transaction Failed between \"" + customer1.getName() + "\" and store \"" + type + "\"");
      }
    }
    
    public String toString(){
      String str;
      str = "Name: " + type + "\nPrice of Items: " + price + "\nStock Left: " + stock;
      return str;
    }
    
    /* example of toString DONE
     * Store Type: Old things DONE
     * Price of items: 5.5 DONE
     * stock: 687 DONE
     */
}
