public class Student{
  private int studentNumber;
  private int grade;
  
  public Student(int studentID){
    studentNumber = studentID;
    grade = 0;
  }
  
  public void setGrade(int gradeSet){
    grade = gradeSet;
  }
  
  public int getGrade(){
    return grade;
  }
  
  public int getStudentNumber(){
    return studentNumber;
  }
}