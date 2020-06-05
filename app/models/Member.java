package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
    public double idealWeight;

    @OneToMany(cascade = CascadeType.ALL)
    // tracking and managing each members assessments
    public List<Assessment> assessments = new ArrayList<Assessment>();


    public Member(String firstname, String lastname, String email, String password, String address, Double height, Double startingWeight) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.height = height;
        this.startingWeight = startingWeight;

    }

    /*public Member(float bmi){
        this.bmi = bmi;
    }*/


    public void calculateMemberBMI() {
        if(assessments.size()!=0) {
            Assessment assessment = assessments.get(assessments.size()-1);
            //update the bmi collected with member height ad weight
            bmi = assessment.weight / (height * height);
            this.bmi  = toTwoDecimalPlaces(bmi);
        }
        else { // If no assessment completed yet, use starting weight as bmi score
            Logger.info("Calculating member bmi " + bmi);
            // computing bmi and storing it in each member object
            this.bmi = toTwoDecimalPlaces(startingWeight / (height * height));
        }
    }
    public void isIdealBodyWeight() {
        /* creating instance of DecimalFormat #.00 is to
         * have 2 digits after decimal point in our output string
         * https://beginnersbook.com/2015/05/java-double-to-string/
         */
        DecimalFormat df = new DecimalFormat("#.00");
        Assessment assessment = assessments.get(assessments.size() - 1);
        idealWeight = 22 * (height * height);
        this.memberIbw = df.format(idealWeight);

    }



    public void calculateBmiResult() {
        //Member member = Accounts.getLoggedInMember();
        String result = "";
        if (bmi < 16.0f) {
            result = "SEVERELY UNDERWEIGHT";
        } else if ((bmi >= 16.0f) && (bmi < 18.5f)) {
            result = "UNDERWEIGHT";
        } else if ((bmi >= 18.5f) && (bmi < 25.0f)) {
            result = "NORMAL";
        } else if ((bmi >= 25.0f) && (bmi < 30.0f)) {
            result = "OVERWEIGHT";
        } else if ((bmi >= 30.0f) && (bmi < 35.0f)) {
            result = "OVERWEIGHT";
        } else if (bmi >= 35.0f) {
            result = "SEVERELY OBESE";
        }
        System.out.println("Member BMI is " + bmi + " and that means you are " + result);
        this.bmiresult = result; // computing bmi result and storing them in each member object
    }




    //public void isIdealBodyWeight() {
        /* creating instance of DecimalFormat #.00 is to
         * have 2 digits after decimal point in our output string
         * https://beginnersbook.com/2015/05/java-double-to-string/
        */
        /*DecimalFormat df = new DecimalFormat("#.00");
        Member member = Accounts.getLoggedInMember();
        double inches = (member.height * 39.77);
        double fiveFeet = 60;      //5ft = 60inches
        double idealBodyWeight;
        if ((inches) < fiveFeet) {
            throw new IllegalArgumentException("height in feet must be >= 5");
        }
        idealBodyWeight = toTwoDecimalPlaces(((48 + (fiveFeet-inches))* 2.3));
        //converting ibw to two digits after decimal point and passing it to memberIbw
        this.memberIbw =  df.format(idealBodyWeight) ;
    }*/

    /**
     * Men: Ideal Body Weight (kg) = 50 kg + 2.3 kg per inch over 5 feet.
     *
     * Women: Ideal Body Weight (kg) = 45.5 kg + 2.3 kg per inch over 5 feet.
     */

    /*private static double calculateIdealBodyWeight(int heightInFeet, int additionalInches) {
        if (heightInFeet < 5) {
            throw new IllegalArgumentException("height in feet must be >= 5");
        }
        int inches = (heightInFeet - MIN_HEIGHT_FEET) * INCH_PER_FEET + additionalInches;
        return MIN_WEIGHT_POUND + inches * POUND_PER_INCH;
    }*/


    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }


    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    private float toTwoDecimalPlaces(double num) {
        return (float) ((int) (num * 100) / 100.0);
    }
}