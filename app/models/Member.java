package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * The Member class stores details about the member
 */
@Entity
public class Member extends Model {
   public String firstname;
   public String lastname;
   public String email;
   public String password;
   public String address;
   public double height;
   public double startingWeight;
   public double bmi;
   public String bmiresult;
   public String memberIbw;

   @OneToMany(cascade = CascadeType.ALL)
   // tracking and managing each members assessments
   public List<Assessment> assessments = new ArrayList<Assessment>();

   /**
    * Constructor for objects of class Member
    * @param firstname Firstname of the member
    * @param lastname Lastname of the member
    * @param email Email of the member
    * @param password Password of the member
    * @param address Address where the member resides
    * @param height Height of the member
    * @param startingWeight Starting weight of member at time of registration
    */
   public Member(String firstname, String lastname, String email, String password, String address, Double height, Double startingWeight) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.password = password;
      this.address = address;
      this.height = height;
      this.startingWeight = startingWeight;
   }

   /**
    * Returns the BMI for the member based on the calculation:
    */
   public void calculateMemberBMI() {
      if (assessments.size() != 0) {
         Assessment assessment = assessments.get(assessments.size() - 1);
         //update the bmi collected with member height ad weight
         bmi = assessment.weight / (height * height);
         this.bmi = toTwoDecimalPlaces(bmi);
      } else { // If no assessment completed yet, use starting weight as bmi score
         Logger.info("Calculating member bmi " + bmi);
         // computing bmi and storing it in each member object
         this.bmi = toTwoDecimalPlaces(startingWeight / (height * height));
      }
   }

   /**
    * Returns the category the BMI belongs to, based on the following values:
    */
   public void calculateBmiResult() {
      String result = "";
      if (bmi < 16.0f) { // if bmi is less than 16 severely underweight is passed to result
         result = "SEVERELY UNDERWEIGHT";
      } else if ((bmi >= 16.0) && (bmi < 18.5)) {
         result = "UNDERWEIGHT";
      } else if ((bmi >= 18.5) && (bmi < 25.0)) {
         result = "NORMAL";
      } else if ((bmi >= 25.0) && (bmi < 30.0)) {
         result = "OVERWEIGHT";
      } else if ((bmi >= 30.0) && (bmi < 35.0)) {
         result = "OVERWEIGHT";
      } else if (bmi >= 35.0) {
         result = "SEVERELY OBESE";
      }
      //System.out.println("Member BMI is " + bmi + " and that means you are " + result);
      this.bmiresult = result; // computing bmi result and storing them in each member object
   }

   /**
    * Calculating the ideal body weight on the Devine Formula:
    * Men: Ideal Body Weight (kg) = 50 kg + 2.3 kg per inch over 5 feet.
    * Women: Ideal Body Weight (kg) = 45.5 kg + 2.3 kg per inch over 5 feet.
    */
   //public void isIdealBodyWeight() {
      /* creating instance of DecimalFormat #.00 is to
       * have 2 digits after decimal point in our output string
       */
      /*DecimalFormat df = new DecimalFormat("#.00");
      Member member = Accounts.getLoggedInMember();
      double inches = (member.height * 39.77); //converting height parameter from metric to imperial
      double fiveFeet = 60;      //5ft = 60inches
      double idealBodyWeight;
      if ((inches) < fiveFeet) {
         throw new IllegalArgumentException("height in feet must be >= 5");
      }
      idealBodyWeight = toTwoDecimalPlaces(((48 + (inches-fiveFeet)) * 2.3));
      //converting ibw to two digits after decimal point and passing it to memberIbw
      this.memberIbw = df.format(idealBodyWeight);
   }*/

   public void isIdealBodyWeight() {
   /* creating instance of DecimalFormat #.00 is to
    * have 2 digits after decimal point in our output string
    */
      DecimalFormat df = new DecimalFormat("#.00");
      double idealWeight;
      Assessment assessment = assessments.get(assessments.size() - 1);
      idealWeight = 22 * (height * height);
      this.memberIbw = df.format(idealWeight);
   }

   public static Member findByEmail(String email) {
      return find("email", email).first();
   }

   public boolean checkPassword(String password) {
      return this.password.equals(password);
   }

   private float toTwoDecimalPlaces(double num) {
      return ((int) ((num * 100) / 100.0));
   }

   //-------
   //getters
   //-------

   public String getFirstname() { return firstname; }

   public String getLastname() { return lastname; }

   public String getEmail() { return email; }

   public String getPassword() { return password; }

   public String getAddress() { return address; }

   public double getHeight() { return height; }

   public double getStartingWeight() { return startingWeight; }

   public double getBmi() { return bmi; }

   public String getBmiresult() { return bmiresult; }

   public String getMemberIbw() { return memberIbw; }

   //-------
   //setters
   //-------

   public void setFirstname(String firstname) { this.firstname = firstname; }

   public void setLastname(String lastname) { this.lastname = lastname; }

   public void setEmail(String email) { this.email = email; }

   public void setHeight(double height) { this.height = height; }

   public void setAddress(String address) { this.address = address; }

   public void setPassword(String password) { this.password = password; }

   public void setBmi(double bmi) { this.bmi = bmi; }

   public void setStartingWeight(double startingWeight) { this.startingWeight = startingWeight; }

   public void setMemberIbw(String memberIbw) { this.memberIbw = memberIbw; }

   public void setBmiresult(String bmiresult) { this.bmiresult = bmiresult; }

}