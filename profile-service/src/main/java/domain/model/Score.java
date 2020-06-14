package domain.model;
public class Score {
	private String usernameID;
	private double score;
	public Score() {}
	public Score(String usernameID, double score) {
		this.usernameID = usernameID;
		this.score = score;
	}
	public String getUsernameID() {
		return usernameID;
	}
	public double getScore() {
		return score;
	}
}
