public class RaceCar {
        
       private String carColour;      //colour of the car
       private int carSpeed;         //car speed
       public String carDescription; //car description
       private String currentDriver; //car currentDriver
     
       /* Constructor for the RaceCar.
        * @param carColorParameter color of the car 
        * @param carSpeedParameter speed of the car
        * The global variables will be set to the parameters
        */
       public RaceCar(String carColourParameter, int carSpeedParameter) {
               carColour = carColourParameter;
               carSpeed = carSpeedParameter;
               currentDriver = "no one";
               maxSpeed = carSpeedParameter * 2;
       }
       
       // set the colour of the car
       public void setColour(String newColour) {
               carColour = newColour;
       }
       
       // set the Driver of the car as a String
       public void setDriver(String newDriver) {
               currentDriver = newDriver;
       }
       
       // @return the carSpeed
       public int getCarSpeed() {
               return carSpeed;
       }
       
        // @return the car color
       public String getColour() {
               return carColour;
       }

       // @return the driver
       public String getDriver() {
              return currentDriver;
       }
       
       public String getInfo(){
        String info;
        info = "Driver is: " + currentDriver + ", " + "Colour is: " + carColour + ", and Max Speed is: " + carSpeed;
        return info;
       }

}
