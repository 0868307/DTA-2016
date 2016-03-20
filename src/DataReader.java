
import model.Item;
import model.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wouter on 3/8/2016.
 */
public class DataReader {
    public DataReader() {
    }
    public static Map<Long, User> loadSmallDataset(){
        String file = "data/userItem.data";
        Map<Long,User> treeMap = new TreeMap();
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines().parallel().forEach(t -> {
                User user = lineToUser(t,"\\,");
                if (treeMap.containsKey(user.getId())) {
                    treeMap.get(user.getId()).addRatings(user.getMovieRatings());
                } else {
                    treeMap.put(user.getId(),user);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return treeMap;
    }
    public static Map<Long,User> loadLargeDataset(){
        String file = "data/u.data";
        Map<Long,User> treeMap = new TreeMap();
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines().parallel().forEach(t -> {
                User user = lineToUser(t,"\t");
                if (treeMap.containsKey(user.getId())) {
                    treeMap.get(user.getId()).addRatings(user.getMovieRatings());
                } else {
                    treeMap.put(user.getId(),user);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return treeMap;
    }
    public static User lineToUser(String line,String splittedBy){
        String[] objectProperties = line.split(splittedBy);
        Map<Integer, Item> movieMap = new TreeMap();
        movieMap.put(Integer.parseInt(objectProperties[1]),new Item(Integer.parseInt(objectProperties[1]),Double.parseDouble(objectProperties[2])));
        User user = new User(Long.parseLong(objectProperties[0]),movieMap);
        return user;
    }
}
