import java.io.*;

public class StudentMain{
  
  public static String action;
  public static int average;
  
  public static void main(String [] args)throws IOException{
    int dfg;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    Student student10477 = new Student(10477);
    Student student10478 = new Student(10478);
    Student student10479 = new Student(10479);
    Student student10480 = new Student(10480);
    Student student10481 = new Student(10481);
    Student student10482 = new Student(10482);
    Student student10483 = new Student(10483);
    Student student10484 = new Student(10484);
  
    student10477.setGrade(70);
    student10478.setGrade(85);
    student10479.setGrade(90);
    student10480.setGrade(88);
    student10481.setGrade(75);
    student10482.setGrade(73);
    student10483.setGrade(89);
    student10484.setGrade(100);
    
    for(int i = 5; i < 100; i--){
      ln("Would you like to do...");
      ln(" - Get the average of the students");
      ln(" - Get the grade of all students");
      ln("please use \"average\" or \"get grade\"");
      action = input.readLine();
      
         if(action.equals("average")){
           average = student10477.getGrade() + student10478.getGrade() + student10479.getGrade() + student10480.getGrade() + student10481.getGrade() + student10482.getGrade() + student10483.getGrade() + student10484.getGrade();
           average = average / 8;
           System.out.println("The grade average of the students is: " + average);
           action = input.readLine();
           action = "";
         }
         else if(action.equals("get grade")){
           ln("Grade of " + student10477.getStudentNumber() + " is " + student10477.getGrade());
           ln("Grade of " + student10478.getStudentNumber() + " is " + student10478.getGrade());
           ln("Grade of " + student10479.getStudentNumber() + " is " + student10479.getGrade());
           ln("Grade of " + student10480.getStudentNumber() + " is " + student10480.getGrade());
           ln("Grade of " + student10481.getStudentNumber() + " is " + student10481.getGrade());
           ln("Grade of " + student10482.getStudentNumber() + " is " + student10482.getGrade());
           ln("Grade of " + student10483.getStudentNumber() + " is " + student10483.getGrade());
           ln("Grade of " + student10484.getStudentNumber() + " is " + student10484.getGrade());
           action = input.readLine();
           action = "";
         }
         else{
           ln("That is not a valid option");
           action = input.readLine();
           action = "";
         }
    }
  }
  
  public static void ln(String inp){
    System.out.println(inp);
  }
}