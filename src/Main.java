import calculator.Calculator;
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
    public static final NearestNeighbours NEAREST_NEIGBOURS = new NearestNeighbours();
    public static final Calculator PEARSON = new Pearson();
    public static final Calculator EUCLIDIAN = new Euclidean();
    public static final Calculator COSINE = new Cosine();

    public static void main(String[] args) {
        Map<Long,User> map = DataReader.loadSmallDataset();
        System.out.println(map);
        System.out.println("==================================");
        System.out.println("report template opdracht 5");
        System.out.println("Pearson : " + NEAREST_NEIGBOURS.getNearestNeighbours(map.get(7L), map.values(), 0.35, 3, PEARSON));
        System.out.println("Euclidean : " + NEAREST_NEIGBOURS.getNearestNeighbours(map.get(7L), map.values(), 0.35, 3, EUCLIDIAN));
        System.out.println("Cosine : " + NEAREST_NEIGBOURS.getNearestNeighbours(map.get(7L), map.values(), 0.35, 3, COSINE));
        System.out.println("==================================");
        System.out.println("report template opdracht 6");
        System.out.println(new Pearson().calculate(map.get(3L),map.get(4L)));
        System.out.println("==================================");
        System.out.println("report template opdracht 7");
        System.out.println("user 7 Item 101 : " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 101, map.values(), 0, 3, PEARSON));
        System.out.println("user 7 Item 103 : " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 103, map.values(), 0, 3, PEARSON));
        System.out.println("user 7 Item 106 : " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 106, map.values(), 0, 3, PEARSON));

        System.out.println("user 4 Item 101 : " + NEAREST_NEIGBOURS.getPredictedRating(map.get(4L), 101, map.values(), 0, 3, PEARSON));
        System.out.println("==================================");
        System.out.println("report template opdracht 8");
        System.out.println("insert rating item 106 as 2.8");
        map.get(7L).addRating(106, 2.8);
        System.out.println("Predicted rating for item 101: " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 101, map.values(), 0, 0, PEARSON));
        System.out.println("Predicted rating for item 103: " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 103, map.values(), 0, 0, PEARSON));
        System.out.println("change rating item 106 to 5");
        map.get(7L).addRating(106, 5);
        System.out.println("Predicted rating for item 101: " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 101, map.values(), 0, 0, PEARSON));
        System.out.println("Predicted rating for item 103: " + NEAREST_NEIGBOURS.getPredictedRating(map.get(7L), 103, map.values(), 0, 0, PEARSON));


        System.out.println("==================================");
        map = DataReader.loadLargeDataset();
        System.out.println("report template opdracht 11");
        List<Item> itemlist = NEAREST_NEIGBOURS.movieSuggestion(map.get(186L), map.values(), 8, 0.35, 25, PEARSON);
        for (int suggestionNumber = 0; suggestionNumber < itemlist.size(); suggestionNumber++) {
            System.out.println("Recommendation  "+(suggestionNumber+1)+" : "+ itemlist.get(suggestionNumber).getId() + " with predicted rating: "+ itemlist.get(suggestionNumber).getRating());
        }
        System.out.println("==================================");
        System.out.println("report template opdracht 12");
        itemlist = NEAREST_NEIGBOURS.movieSuggestionWithMinimumRated(map.get(186L), map.values(), 8, 3, 0.35, 25, PEARSON);
        for (int suggestionNumber = 0; suggestionNumber < itemlist.size(); suggestionNumber++) {
            System.out.println("Recommendation  "+(suggestionNumber+1)+" : "+ itemlist.get(suggestionNumber).getId() + " with predicted rating: "+ itemlist.get(suggestionNumber).getRating());
        }

        System.out.println("==================================");


    }
}
