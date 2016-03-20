import calculator.Cosine;
import calculator.Euclidean;
import calculator.Pearson;
import model.Item;
import model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Wouter on 3/8/2016.
 */
public class Main {
    public static void main(String[] args) {
        Map<Long,User> map = DataReader.loadSmallDataset();
        System.out.println(map);
        System.out.println("==================================");
        System.out.println("report template opdracht 5");
        System.out.println("Pearson : " + NearestNeighbours.getNearestNeighbours(map.get(7L),map.values(),0.35,3,new Pearson()));
        System.out.println("Euclidean : " + NearestNeighbours.getNearestNeighbours(map.get(7L),map.values(),0.35,3,new Euclidean()));
        System.out.println("Cosine : " + NearestNeighbours.getNearestNeighbours(map.get(7L),map.values(),0.35,3,new Cosine()));
        System.out.println("==================================");
        System.out.println("report template opdracht 6");
        System.out.println(new Pearson().calculate(map.get(3L),map.get(4L)));
        System.out.println("==================================");
        System.out.println("report template opdracht 7");
        System.out.println("user 7 Item 101 : " +NearestNeighbours.getPredictedRating(map.get(7L), 101, map.values(), 0, 3, new Pearson()));
        System.out.println("user 7 Item 103 : " +NearestNeighbours.getPredictedRating(map.get(7L), 103, map.values(), 0, 3, new Pearson()));
        System.out.println("user 7 Item 106 : " +NearestNeighbours.getPredictedRating(map.get(7L), 106, map.values(), 0, 3, new Pearson()));

        System.out.println("user 4 Item 101 : " +NearestNeighbours.getPredictedRating(map.get(4L), 101, map.values(), 0, 3, new Pearson()));
        System.out.println("==================================");
        System.out.println("report template opdracht 8");
        System.out.println("insert rating item 106 as 2.8");
        map.get(7L).addRating(106, 2.8);
        System.out.println("Predicted rating for item 101: " + NearestNeighbours.getPredictedRating(map.get(7L), 101, map.values(), 0, 0, new Pearson()));
        System.out.println("Predicted rating for item 103: " + NearestNeighbours.getPredictedRating(map.get(7L), 103, map.values(), 0, 0, new Pearson()));
        System.out.println("change rating item 106 to 5");
        map.get(7L).addRating(106, 5);
        System.out.println("Predicted rating for item 101: " + NearestNeighbours.getPredictedRating(map.get(7L), 101, map.values(), 0, 0, new Pearson()));
        System.out.println("Predicted rating for item 103: " + NearestNeighbours.getPredictedRating(map.get(7L), 103, map.values(), 0, 0, new Pearson()));


        System.out.println("==================================");
        map = DataReader.loadLargeDataset();
        System.out.println("report template opdracht 11");
        List<Item> itemlist =NearestNeighbours.movieSuggestion(map.get(186L), map.values(), 8, 0.35, 25, new Pearson());
        for (int suggestionNumber = 0; suggestionNumber < itemlist.size(); suggestionNumber++) {
            System.out.println("Recommendation  "+(suggestionNumber+1)+" : "+ itemlist.get(suggestionNumber).getId() + " with predicted rating: "+ itemlist.get(suggestionNumber).getRating());
        }
        System.out.println("==================================");
        System.out.println("report template opdracht 12");
        itemlist =NearestNeighbours.movieSuggestionWithMinimumRated(map.get(186L), map.values(), 8, 3, 0.35, 25, new Pearson());
        for (int suggestionNumber = 0; suggestionNumber < itemlist.size(); suggestionNumber++) {
            System.out.println("Recommendation  "+(suggestionNumber+1)+" : "+ itemlist.get(suggestionNumber).getId() + " with predicted rating: "+ itemlist.get(suggestionNumber).getRating());
        }

        System.out.println("==================================");


    }
}
