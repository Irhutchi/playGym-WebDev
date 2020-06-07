package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Assessment extends Model {
   public double weight;
   public double chest;
   public double thigh;
   public double upperArm;
   public double waist;
   public double hips;
   public String comment;
   /**
    * Constructor for objects of Assessment Model
    * @param weight Weight of the member in Kilograms
    * @param chest Chest measurement of the member in cm
    * @param thigh Thigh measurement of the member in cm
    * @param upperArm Password of the member in cm
    * @param waist Waist measurement in cm
    * @param hips Hip measurement in cm
    */
   public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
      this.weight = weight;
      this.chest = chest;
      this.thigh = thigh;
      this.upperArm = upperArm;
      this.waist = waist;
      this.hips = hips;
   }

   public Assessment(String comment) {
      this.comment = comment;
   }


   //-------
   //getters
   //-------
   public double getWeight() {
      return weight;
   }

   public double getChest() { return chest; }

   public double getThigh() { return thigh; }

   public double getUpperArm() { return upperArm; }

   public void setUpperArm(double upperArm) { this.upperArm = upperArm; }

   public double getWaist() { return waist; }

   public double getHips() { return hips; }

   public String getComment() { return comment; }

   //-------
   //setters
   //-------
   public void setWeight(double weight) {
      this.weight = weight;
   }

   public void setChest(double chest) { this.chest = chest; }

   public void setThigh(double thigh) { this.thigh = thigh; }

   public void setWaist(double waist) { this.waist = waist; }

   public void setHips(double hips) { this.hips = hips; }

   public void setComment(String comment) { this.comment = comment; }

}



