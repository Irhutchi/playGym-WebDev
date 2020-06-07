package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
/**
 * The Trainer class stores details about the member
 */
@Entity
public class Trainer extends Model {
   public String firstname;
   public String lastname;
   public String email;
   public String password;

   /**
    * Constructor for objects of class Trainer
    * @param firstname Firstname of the trainer
    * @param lastname Lastname of the trainer
    * @param email Email of the trainer
    * @param password Password of the trainer
    */
   public Trainer(String firstname, String lastname, String email, String password) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.password = password;
   }

   public static Trainer findByEmail(String email) {
      return find("email", email).first();
   }

   public boolean checkPassword(String password) {
      return this.password.equals(password);
   }


   //-------
   //getters
   //-------

   public String getFirstname() { return firstname; }

   public String getLastname() { return lastname; }

   public String getEmail() { return email; }

   public String getPassword() { return password; }

   //-------
   //setters
   //-------
   public void setFirstname(String firstname) { this.firstname = firstname; }

   public void setEmail(String email) { this.email = email; }

   public void setLastname(String lastname) { this.lastname = lastname; }

   public void setPassword(String password) { this.password = password; }


}