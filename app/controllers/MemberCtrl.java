package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import java.util.List;


public class MemberCtrl extends Controller
{
    public float bmi;

    public static void index(Long id)
    {
        Member member = Member.findById(id);
        Logger.info ("Member id = " + id);
        List<Assessment> assessments = member.assessments;
        member.calculateBmiResult();
        member.isIdealBodyWeight();
        member.calculateMemberBMI();
        render("member.html", member, assessments);

    }
    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        Logger.info ("Removing" + member.firstname);
        member.delete();
        Trainer trainer = Accounts.getLoggedInTrainer();
        trainer.save();
        redirect("/trainerdashboard");
    }



    public void addComment(Long id, String feedback)
    {
        Logger.info("Trainer comment posting ");
        Assessment assessment = Assessment.findById(id);
        assessment.feedback = feedback;
        assessment.save();
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        render("/member.html", trainer, member, assessments);
    }
}