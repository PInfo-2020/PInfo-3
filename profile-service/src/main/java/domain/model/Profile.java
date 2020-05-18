package domain.model;


import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
// Maybe add this :@IdClass(Profile.class) and create the profileID class
@Table(name="profile")
public class Profile implements Serializable {
	
	public Profile() {}	
	
	public Profile(String usernameID, String username, double score) {
		super();
		this.usernameID = usernameID;
		this.username = username;
		this.score = score;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usernameID", unique=true)
	private String usernameID;
	
	@Column(name="username")
	private String username;
	
	@Column(name="score")
	private double score;

	public String getUsernameID() {
		return usernameID;
	}

	public void setUsernameID(String usernameID) {
		this.usernameID = usernameID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
	

}
