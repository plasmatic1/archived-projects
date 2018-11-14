
public class Customer {

    private String name;     //the name of the customer
    private double money;    //the money that the customer has
    
    public Customer (){
        name = "mindless coustomer";
        money = 100;
    }
    //TO DO: build the default Customer constructor DONE
    //by default the name of the Customer is "mindless Customer" DONE
    //default money is $100 DONE
    
    public Customer(String name, double money){
        this.name = name;
        this.money = money;
    }
    
    /* Building the toString DONE
     * should show up as: DONE
     * Name: nameOfCustomer  (these are not set) DONE
     * Money: moneyOfCustomer  (these are not set) DONE
     */
    public String toString(){
        String str;
        str = "Name: " + name + "\nMoney: " + money;
        return str;
    }
    
    public double getMoney(){
      return money;
    }
    
    public void setMoney(Double money){
      this.money = money;
    }
    
    public String getName(){
      return name;
    }
}
