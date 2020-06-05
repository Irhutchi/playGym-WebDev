package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{


  public static void index()
  {
    Logger.info("Rendering Member Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    member.calculateBmiResult();
    member.isIdealBodyWeight();
    member.calculateMemberBMI();
    render("dashboard.html", member, assessments);
  }

  public static void trainerIndex() {
    Logger.info("Rendering trainer dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = Member.findAll();
    for (Member member : members) { // for each statement retrieves each member from the member collection
      member.isIdealBodyWeight();
      member.calculateBmiResult();
      member.calculateMemberBMI();
    }
    render ("trainerdashboard.html", trainer, members);
  }

  public static void addAssessment (double weight, double chest, double thigh, double upperArm, double waist, double hips) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh,upperArm,waist,hips);
    member.assessments.add(assessment);
    //Member member = Member.findById(id);
    member.save();
    redirect ("/dashboard");
  }


  public static void deleteAssessment (Long id, Long assessmentid) {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    Logger.info("Removing" + assessment.weight + assessment.chest + assessment.thigh
            + assessment.upperArm + assessment.waist + assessment.hips);
    //Removing member assessment from member assessments collection & delete from the database
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    render("member.html", member);
  }

  /*public static void addMember(String firstname, String lastname, String email, String password,String address, double height, double startingWeight)
  {

    Member member = new Member(firstname, lastname, email, password, address, height, startingWeight);
    member.save();
    Logger.info("Adding Assessment");
    redirect("/dashboard");
  }*/






}
