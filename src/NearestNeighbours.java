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
    public static List<Neighbour> getNearestNeighbours(User user, Collection<User> users,double minimalSimilarity,int limit, Calculator calculator){
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

    public static double getPredictedRating(User user,int movie,Collection<User> users,double minimalSimilarity,int limit, Calculator calculator){
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
    public static List<Item> movieSuggestion(User user,Collection<User> users,int amountofMovies,double minimalSimilarity,int limit, Calculator calculator){
        List<Neighbour> neighbours = getNearestNeighbours(user,users,minimalSimilarity,limit,calculator);
        List<Item> movieList = new ArrayList<>();

        Set<Integer> movies = predictableMovies(user,neighbours);
        for (Integer movie : movies) {
            double predicted = NearestNeighbours.getPredictedRating(user, movie, users, 0.35, 25, new Pearson());
            movieList.add(new Item(movie,predicted));
        }
        Collections.sort(movieList, (Item i2, Item i1) -> i1.getRating().compareTo(i2.getRating()));
        return movieList.subList(0,amountofMovies);
    }
    private static Set<Integer> predictableMovies(User user,List<Neighbour> neighbours){
        Set<Integer> movies = new HashSet<>();
        for (Neighbour neighbour : neighbours) {
            movies.addAll(neighbour.getUser().getMovieRatings().keySet());
        }
        movies.removeAll(user.getMovieRatings().keySet());
        return movies;
    }
}
