package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Trainer extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;


    //@OneToMany(cascade = CascadeType.ALL)
    // tracking and managing each member profile
    //public List<Member> members = new ArrayList<Member>();

    public Trainer(String firstname, String lastname,String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

    }

    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}