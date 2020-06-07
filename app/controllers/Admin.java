package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller {
   public static void index() {
      Logger.info("Rendering admin");
      List<Member> members = Member.findAll();
      List<Assessment> assessment = Assessment.findAll();
      List<Trainer> trainers = Trainer.findAll();
      render("admin.html", members, assessment, trainers);
   }

   public static void deleteMember(Long id) {
      Member member = Member.findById(id);
      Logger.info("Removing" + member.firstname + member.lastname);
      member.delete();
      redirect("/amdin.html");
   }

}