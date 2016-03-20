package model;

/**
 * Created by Wouter on 3/8/2016.
 */
public class Neighbour {
    private User user;
    private Double similarity;

    public Neighbour(User user, double similarity) {
        this.user = user;
        this.similarity = similarity;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Double getSimilarity() {
        return similarity;
    }
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    @Override
    public String toString() {
        return "Neighbour{" +
                "user=" + user.getId() +
                ", similarity=" + similarity +
                '}';
    }
}
