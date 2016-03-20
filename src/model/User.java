package model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wouter on 3/1/2016.
 */
public class User {
    private long id;
    private Map<Integer,Item> movieRatings = new TreeMap<>(); // A map containing all the ratings of a user
    public User(long id, Map<Integer, Item> movieRatings) {
        this.id = id;
        this.movieRatings = movieRatings;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // add a single rating to the existing map
    public void addRating(int movie,double rating){movieRatings.put(movie,new Item(movie,rating));}
    // add a multiple ratings to the existing map
    public void addRatings(Map<Integer,Item> movieRatings){
        if(movieRatings != null){
            this.movieRatings.putAll(movieRatings);
        }
    }
    public Map<Integer, Item> getMovieRatings() {
        return movieRatings;
    }
    public void setMovieRatings(Map<Integer, Item> movieRatings) {
        this.movieRatings = movieRatings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", movieRatings=" + movieRatings +
                '}';
    }
}
