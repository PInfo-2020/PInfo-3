package domain.model;

import java.io.Serializable;
import java.util.Objects;

public class ProfileID implements Serializable {
	
	public ProfileID() {}
	private String usernameID;
	
	private Double score;
	
	private String username;
	
	public ProfileID(String usernameID, String username, Double score ) {
		super();
		this.usernameID = usernameID;
		this.score = score;
		this.username = username;
	}


	public String getUsernameID() {
		return usernameID;
	}

	public String getUsername() {
		return username;
	}

	private static final long serialVersionUID = 1L;
	

	@Override
	public boolean equals(Object object) {
		if (object instanceof ProfileID) {
			ProfileID profileID = (ProfileID) object;
			if ((this.usernameID == profileID.getUsernameID()) && (this.username == profileID.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(usernameID, username);
	}



}