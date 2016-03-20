package calculator;

import model.Item;
import model.User;

import java.util.Map;

/**
 * Created by Wouter on 3/8/2016.
 */
public class Pearson implements Calculator {

    @Override
    public double calculate(User firstUser, User secondUser) {
        int ratingCount = 0;
        double upperFirstValue = 0;
        double sumFirstUser = 0;
        double sumSecondUser = 0;
        double sumSquaredFirstUserItems = 0;
        double sumSquaredSecondUserItems = 0;
        for (Item movieRating : firstUser.getMovieRatings().values()) {
            if(secondUser.getMovieRatings().containsKey(movieRating.getId())){
                double secondMovieRating = secondUser.getMovieRatings().get(movieRating.getId()).getRating();
                upperFirstValue += movieRating.getRating() * secondMovieRating;
                sumFirstUser += movieRating.getRating();
                sumSecondUser += secondMovieRating;
                sumSquaredFirstUserItems += Math.pow(movieRating.getRating(), 2);
                sumSquaredSecondUserItems += Math.pow(secondMovieRating,2);
                ratingCount++;
            }
        }
        double sumFirstUserSquared = Math.pow(sumFirstUser,2);
        double sumSecondUserSquared = Math.pow(sumSecondUser,2);
        sumSquaredFirstUserItems = Math.sqrt((sumSquaredFirstUserItems - (sumFirstUserSquared / ratingCount)));
        sumSquaredSecondUserItems = Math.sqrt((sumSquaredSecondUserItems - (sumSecondUserSquared / ratingCount)));

        double upper = upperFirstValue - (sumFirstUser*sumSecondUser)/ratingCount;
        double lower = sumSquaredFirstUserItems * sumSquaredSecondUserItems;
        return upper/lower;
    }
}
