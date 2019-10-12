package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
)

public  class ServiceUser{
private int userID;

public void setUserID(int value) {
this.userID = value;
}
@Id
private int getUserID() {
return this.userID;
}
   private String email;

   public void setEmail(String value) {
    this.email = value;
}
   public String getEmail() {
    return this.email;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private String phoneNumber;

public void setPhoneNumber(String value) {
    this.phoneNumber = value;
}
public String getPhoneNumber() {
    return this.phoneNumber;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
private TutoringService321 tutoringService321;	

@ManyToOne(optional=false)	
public TutoringService321 getTutoringService321() {	
   return this.tutoringService321;	
}	

public void setTutoringService321(TutoringService321 tutoringService321) {	
   this.tutoringService321 = tutoringService321;	
}
   }
