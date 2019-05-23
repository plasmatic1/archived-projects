
public class csMain {

    public static void main(String[] args) {
        Store Wal_Mart = new Store("Cheap stuff", 5.0, 1000);
        Store Best_Buy = new Store();
        Store Zellers = new Store("Old things", 5.5, 1000);
        
        //init the values for customers
        
        Customer cus = new Customer();
        Customer cus2 = new Customer("Bill Gates", 36000000000.0);
        Customer cus3 = new Customer("Hobo Down the Street", 2);
        Customer cus4 = new Customer("Average Joe", 400);
        Customer cus5 = new Customer("Student", 100);
        Customer cus6 = new Customer();
        Customer cus7 = new Customer();
        
        //simulate customers going into store and buying stuff
        System.out.println("---|During Simulation|---");
        Wal_Mart.setCustomer1(cus);
        Wal_Mart.sell_Stock(5);
        Wal_Mart.setCustomer1(cus3);
        Wal_Mart.sell_Stock(0);
        Wal_Mart.setCustomer1(cus4);
        Wal_Mart.sell_Stock(10);
        Best_Buy.setCustomer1(cus2);
        Best_Buy.sell_Stock(700000);
        Best_Buy.setCustomer1(cus5);
        Best_Buy.sell_Stock(10);
        Best_Buy.setCustomer1(cus6);
        Best_Buy.sell_Stock(1);
        Zellers.setCustomer1(cus2);
        Zellers.sell_Stock(300);
        Zellers.setCustomer1(cus7);
        Zellers.sell_Stock(10);
        Zellers.setCustomer1(cus);
        Zellers.sell_Stock(3);
        
        //print out the store info after simulation
        System.out.println("---|Store Info After Simulation|---");
        System.out.println(Zellers.toString());
        System.out.println(Best_Buy.toString());
        System.out.println(Wal_Mart.toString());
        
        //print out the customer info after simulation
        System.out.println("---|Customer Info After Simulation|---");
        System.out.println(cus.toString());
        System.out.println(cus2.toString());
        System.out.println(cus3.toString());
        System.out.println(cus4.toString());
        System.out.println(cus5.toString());
        System.out.println(cus6.toString());
        System.out.println(cus7.toString());
        
    }

}
