package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
   public static void signup() {
      render("signup.html");
   }

   public static void login() {
      render("login.html");
   }

   public static void register(String firstname, String lastname, String email, String password, String address, double height, double startingWeight) {
      Logger.info("Registering new user " + email);
      Member member = new Member(firstname, lastname, email, password, address, height, startingWeight);
      member.save();
      redirect("/");
   }

   public static void authenticate(String email, String password) {
      Logger.info("Attempting to authenticate with " + email + ":" + password);
      Trainer trainer = Trainer.findByEmail(email);
      Member member = Member.findByEmail(email); // find Member by email in DB
      //if member is found and password matched authentication successful
      if ((member != null) && (member.checkPassword(password) == true)) {
         Logger.info("Authentication successful");
         session.put("logged_in_Memberid", member.id);
         redirect("/dashboard");
         //if trainer is found and password matches authentication successful
      } else if ((trainer != null) && (trainer.checkPassword(password) == true)) {
         Logger.info("Authentication successful");
         session.put("logged_in_Trainerid", trainer.id);
         redirect("/trainerdashboard");
      } else {
         Logger.info("Authentication failed");
         redirect("/login");
      }
   }

   public static Member getLoggedInMember() {
      Member member = null;
      if (session.contains("logged_in_Memberid")) {
         String memberId = session.get("logged_in_Memberid");
         member = Member.findById(Long.parseLong(memberId));
      } else {
         login();
      }
      return member;
   }

   public static Trainer getLoggedInTrainer() {
      Trainer trainer = null;
      if (session.contains("logged_in_Trainerid")) {
         String trainerId = session.get("logged_in_Trainerid");
         trainer = Trainer.findById(Long.parseLong(trainerId));
      } else {
         login();
      }
      return trainer;
   }

   public static void logout() {
      session.clear();    //session object is deleted once user logs out.,
      redirect("/");
   }


}