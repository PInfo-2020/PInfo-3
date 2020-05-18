package domain.model;


public class Score {
    //To communicat with Luka and Recipe-service
    //He will update the column score in my profile table 

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

    public void setUsernameID(String usernameID) {
        this.usernameID = usernameID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }





}