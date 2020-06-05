package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;


public class MemberCtrl extends Controller {
   public float bmi;

   public static void index(Long id) {
      Member member = Member.findById(id);
      Logger.info("Member id = " + id);
      List<Assessment> assessments = member.assessments;
      member.calculateBmiResult();
      member.isIdealBodyWeight();
      member.calculateMemberBMI();
      render("member.html", member, assessments);

   }

   public static void deleteMember(Long id) {
      Member member = Member.findById(id);
      Logger.info("Removing" + member.firstname);
      member.delete();
      Trainer trainer = Accounts.getLoggedInTrainer();
      trainer.save();
      redirect("/trainerdashboard");
   }

   /*public void addComment(Long id, String comment)
   {
       Logger.info("Trainer comment posting ");
       //Assessment assessment = Assessment.findById(id);
       Assessment assessment = new Assessment(comment);
       assessment.save();
       Trainer trainer = Accounts.getLoggedInTrainer();
       Member member = Member.findById(id);
       List<Assessment> assessments = member.assessments;
       //render("/member.html", trainer, member, assessments);
       redirect("/member");
   }*/
   public static void addComment(String comment, Long id) {
      Logger.info("Adding a Comment" + comment);
      Assessment assessment = Assessment.findById(id);
      assessment.comment = comment;
      assessment.save();
      redirect("/trainerdashboard");
   }


    /*public static void addComment(String feedback) {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(feedback);
        member.assessments.add(assessment);
        member.save();
        redirect ("/member");
    }*/

}