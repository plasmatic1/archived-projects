import java.io.*;

public class Horoscope{

  public static void main(String [] args) throws IOException{
    
    String month;
    int day;
    
    System.out.println("I am here to predict your horoscope");
    System.out.println("What is your birth month?");
    System.out.println("please write your month in words");
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    month = input.readLine();
    month = month.toLowerCase();
    
    System.out.println("What is your birth day?");
    day = Integer.parseInt(input.readLine());
    
      if(month.equals("jaunary")){
        if(day >= 20 && day <= 31){
          System.out.println("Your Horoscope is Aquarius");
          System.out.println("You are Forward thinking, communicative, people oriented, stubborn, generous, and dedicated.");
        }
        else if(day >= 1 && day < 20){
          System.out.println("Your Horoscope is Capricorn");
          System.out.println("You are Timeless, driven, calculating, ambitious");
        }
    }
      else if(month.equals("febuary")){
        if(day >= 1 && day < 18){
          System.out.println("Your Horoscope is Aquarius");
          System.out.println("You are Forward thinking, communicative, people oriented, stubborn, generous, and dedicated.");
        }
        else if(day >= 18 && day <= 29){
          System.out.println("Your Horoscope is Piceses");
          System.out.println("You are Likeable, energetic, passionate, sensitive");
        }
      }
      else if(month.equals("march")){
        if(day >= 1 && day < 20){
          System.out.println("Your Horoscope is Piceses");
          System.out.println("You are Likeable, energetic, passionate, sensitive");
        }
        else if(day >= 20 && day <= 31){
          System.out.println("Your Horoscope is Aries");
          System.out.println("You are Courageous, energetic, willful, commanding, leading. Often leads when following would be best course of action.");
        }
    }
      else if(month.equals("april")){
        if(day >= 1 && day < 20){
          System.out.println("Your Horoscope is Aries");
          System.out.println("You are Courageous, energetic, willful, commanding, leading. Often leads when following would be best course of action.");
        }
        else if(day >= 20 && day <= 30){
          System.out.println("Your Horoscope is Taurus");
          System.out.println("You are Pleasure seeking, loves control, dependable, grounded, provokes slowly, and highly sensual in nature");
        }
      }
      else if(month.equals("may")){
        if(day >= 1 && day < 21){
          System.out.println("Your Horoscope is Taurus");
          System.out.println("You are Pleasure seeking, loves control, dependable, grounded, provokes slowly, and highly sensual in nature");
        }
        else if(day >= 21 && day <= 31){
          System.out.println("Your Horoscope is Gemini");
          System.out.println("You are Cerebral, chatty, loves learning and education, charming, and adventurous");
        }
    }
      else if(month.equals("june")){
        if(day >= 1 && day < 21){
          System.out.println("Your Horoscope is Gemini");
          System.out.println("You are Cerebral, chatty, loves learning and education, charming, and adventurous");
        }
        else if(day >= 21 && day <= 30){
          System.out.println("Your Horoscope is Cancer");
          System.out.println("You are Emotional, group oriented, seeks security, family");
        }
      }
      else if(month.equals("july")){
        if(day >= 1 && day < 23){
          System.out.println("Your Horoscope is Cancer");
          System.out.println("You are Emotional, group oriented, seeks security, family");
        }
        else if(day >= 23 && day <= 31){
          System.out.println("Your Horoscope is Leo");
          System.out.println("You are Generous, organized, protective, beautiful.");
        }
    }
      else if(month.equals("august")){
        if(day >= 1 && day < 23){
          System.out.println("Your Horoscope is Leo");
          System.out.println("You are Generous, organized, protective, beautiful.");
        }
        else if(day >= 23 && day <= 31){
          System.out.println("Your Horoscope is Virgo");
          System.out.println("You are Particular, logical, practical, sense of duty, critical");
        }
      }
      else if(month.equals("september")){
        if(day >= 1 && day < 23){
          System.out.println("Your Horoscope is Virgo");
          System.out.println("You are Particular, logical, practical, sense of duty, critical");
        }
        else if(day >= 23 && day <= 30){
          System.out.println("Your Horoscope is Libra");
          System.out.println("Balanced, seeks beauty, sense of justice");
        }
    }
      else if(month.equals("october")){
        if(day >= 1 && day < 23){
          System.out.println("Your Horoscope is Libra");
          System.out.println("Balanced, seeks beauty, sense of justice");
        }
        else if(day >= 23 && day <= 31){
          System.out.println("Your Horoscope is Scorpio");
          System.out.println("You are Passionate, exacting, loves extremes, combative, reflective.");
        }
      }
      else if(month.equals("november")){
        if(day >= 1 && day < 22){
          System.out.println("Your Horoscope is Scorpio");
          System.out.println("You are Passionate, exacting, loves extremes, combative, reflective.");
        }
        else if(day >= 22 && day <= 30){
          System.out.println("Your Horoscope is Sagittarius");
          System.out.println("You are Happy, absent minded, creative, adventurous");
        }
    }
      else if(month.equals("december")){
        if(day >= 1 && day < 22){
          System.out.println("Your Horoscope is Sagittarius");
          System.out.println("You are Happy, absent minded, creative, adventurous");
        }
        else if(day >= 22 && day <= 31){
          System.out.println("Your Horoscope is Capricorn");
          System.out.println("You are Timeless, driven, calculating, ambitious");
        }
      }
        else{
          System.out.println("Invalid Date, please try again");
        }
      }
}

