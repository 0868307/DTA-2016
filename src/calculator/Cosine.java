package calculator;

import model.Item;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Wouter on 3/8/2016.
 */
public class Cosine implements Calculator {
    @Override
    public double calculate(User firstUser, User secondUser) {
        double productCombi = DotProduct(firstUser,secondUser);
        double productA = DotProduct(firstUser,firstUser);
        double productB = DotProduct(secondUser,secondUser);
        return productCombi/(Math.sqrt(productA)*Math.sqrt(productB));
    }

    public double DotProduct(User first,User second){
        double sum = 0;
        for (Item movieRating : first.getMovieRatings().values()) {
            if(second.getMovieRatings().containsKey(movieRating.getId())){
                double secondMovieRating = second.getMovieRatings().get(movieRating.getId()).getRating();
                sum += movieRating.getRating()*secondMovieRating;
            }
        }
        return sum;
    }
}
