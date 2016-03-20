package model;

/**
 * Created by Wouter on 3/20/2016.
 */
public class Item {
    private int id;
    private double rating;

    public Item(int id, double rating) {
        this.id = id;
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", rating=" + rating +
                '}';
    }
}
