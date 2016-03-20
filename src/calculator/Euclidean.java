package calculator;

import model.Item;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Wouter on 3/8/2016.
 */
public class Euclidean implements Calculator {
    @Override
    public double calculate(User firstUser, User secondUser) {
        double sum =0;
        for (Item movieRating : firstUser.getMovieRatings().values()) {
            if(secondUser.getMovieRatings().containsKey(movieRating.getId())){
                double secondMovieRating = secondUser.getMovieRatings().get(movieRating.getId()).getRating();
                sum += Math.pow(movieRating.getRating()-secondMovieRating,2);
            }
        }
        return Math.sqrt(sum);
    }
}
