import calculator.Calculator;
import calculator.Euclidean;
import calculator.Pearson;
import model.Item;
import model.Neighbour;
import model.User;

import java.util.*;

/**
 * Created by Wouter on 3/8/2016.
 */
public class NearestNeighbours {
    /**
     * returns up to limit of neigbours where the returned neighbours have the most similarities
     * @param user the user to get the neighbours of
     * @param users all users
     * @param minimalSimilarity minimal similarity otherwise it skips it
     * @param limit the amount of neighbours to return ( if limit == 0 return all neighbours)
     * @return
     */
    public List<Neighbour> getNearestNeighbours(User user, Collection<User> users,double minimalSimilarity,int limit, Calculator calculator){
        List<Neighbour> neighbours = new ArrayList<>();
        for (User neighbour : users) {
            if(user != neighbour){
                double similarity = calculator.calculate(user, neighbour);
                if(similarity >= minimalSimilarity){
                    neighbours.add(new Neighbour(neighbour,similarity ));
                }
            }
        }
        if(calculator instanceof Euclidean){
            Collections.sort(neighbours, (Neighbour n1, Neighbour n2) -> n1.getSimilarity().compareTo(n2.getSimilarity()));
        }else{
            Collections.sort(neighbours, (Neighbour n2, Neighbour n1) -> n1.getSimilarity().compareTo(n2.getSimilarity()));
        }
        if(limit == 0){
            return neighbours;
        }
        return neighbours.subList(0,limit);
    }

    /**
     * function to get a prediction for the rating of a user
     * @param user the user to predict the rating for
     * @param movie the movie of which the rating should be predicted
     * @param users all the users
     * @param minimalSimilarity minimum threshold
     * @param limit limit of the amount of values to use
     * @param calculator which algorithm is going to be used
     * @return
     */
    public double getPredictedRating(User user,int movie,Collection<User> users,double minimalSimilarity,int limit, Calculator calculator){
        List<Neighbour> neighbours = getNearestNeighbours(user,users,minimalSimilarity,limit,calculator);
        double combinedSimilarity = 0;
        double sumRatingSim = 0;
        for (Neighbour neighbour : neighbours) {
            if(neighbour.getUser().getMovieRatings().get(movie) == null) {
                continue;
            }
            double rating = neighbour.getUser().getMovieRatings().get(movie).getRating();
            if(rating > 0){
                combinedSimilarity += neighbour.getSimilarity();
                sumRatingSim += rating * neighbour.getSimilarity();
            }
        }
        return sumRatingSim/ combinedSimilarity;
    }

    /**
     * Gives a suggestion which movie is best suited to you to watch which you havent watched yet
     * @param user the user to give a suggestion
     * @param users all the users
     * @param amountofMovies how much movies should be suggested
     * @param minimalSimilarity minimum threshold
     * @param limit limit of the amount of values to use
     * @param calculator which algorithm is going to be used
     * @return
     */
    public List<Item> movieSuggestion(User user,Collection<User> users,int amountofMovies,double minimalSimilarity,int limit, Calculator calculator){
        List<Neighbour> neighbours = getNearestNeighbours(user,users,minimalSimilarity,limit,calculator);
        List<Item> movieList = new ArrayList<>();

        Set<Integer> movies = predictableMovies(user,neighbours);
        for (Integer movie : movies) {
            double predicted = getPredictedRating(user, movie, users, minimalSimilarity, limit, calculator);
            movieList.add(new Item(movie,predicted));
        }
        Collections.sort(movieList, (Item i2, Item i1) -> i1.getRating().compareTo(i2.getRating()));
        return movieList.subList(0,amountofMovies);
    }

    /**
     * Gives a suggestion which movie is best suited to you to watch which you havent watched yet
     * @param user the user to give a suggestion
     * @param users all the users
     * @param amountofMovies how much movies should be suggested
     * @param minimalSimilarity minimum threshold
     * @param limit limit of the amount of values to use
     * @param calculator which algorithm is going to be used
     * @param minimumRated how much the movie has to be rated before it will count
     * @return
     */
    public List<Item> movieSuggestionWithMinimumRated(User user,Collection<User> users,int amountofMovies, int minimumRated,double minimalSimilarity,int limit, Calculator calculator){
        List<Neighbour> neighbours = getNearestNeighbours(user,users,minimalSimilarity,limit,calculator);
        List<Item> movieList = new ArrayList<>();

        Set<Integer> movies = predictableMovies(user,neighbours);
        for (Integer movie : movies) {
            if(hasMinimumAmountRatings(movie,neighbours,minimumRated)){
                double predicted = getPredictedRating(user, movie, users, minimalSimilarity, limit, calculator);
                movieList.add(new Item(movie,predicted));
            }
        }
        Collections.sort(movieList, (Item i2, Item i1) -> i1.getRating().compareTo(i2.getRating()));
        return movieList.subList(0,amountofMovies);
    }

    /**
     * returns a set of movies the user has not seen and someone else has seen
     * @param user the user for which to find new movies
     * @param neighbours the nearest neigbours
     * @return
     */
    private Set<Integer> predictableMovies(User user,List<Neighbour> neighbours){
        Set<Integer> movies = new HashSet<>();
        for (Neighbour neighbour : neighbours) {
            movies.addAll(neighbour.getUser().getMovieRatings().keySet());
        }
        movies.removeAll(user.getMovieRatings().keySet());
        return movies;
    }

    /**
     * returns whether a movie has enough ratings or not
     * @param movie the movie
     * @param neighbours the nearest neighbours
     * @param minimumRated the amount of rating a movie has to have
     * @return
     */
    private boolean hasMinimumAmountRatings(int movie,List<Neighbour> neighbours,int minimumRated){
        int count = 0;
        for (Neighbour neighbour : neighbours) {
            Item item = neighbour.getUser().getMovieRatings().get(movie);
            if(item != null){
                count++;
            }
        }
        return count >= minimumRated;
    }
}
