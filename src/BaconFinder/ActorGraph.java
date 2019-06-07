package BaconFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static BaconFinder.Util.*;

public class ActorGraph {

    public static HashMap<Integer, Movie> movieIDToNode = new HashMap<>();
    public static HashMap<Integer, Actor> actorIDToNode = new HashMap<>();

    private static final String PRINCIPALS_FILE = "./data/simplified.title.principals.tsv";
    //private static final String PRINCIPALS_FILE = "./data/fake.tsv";

    public static void load() {
        System.out.println("Loading movie to actor maps...");
        try (BufferedReader reader = new BufferedReader(new FileReader(PRINCIPALS_FILE))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] tokens = currentLine.split("\\t");
                int currentMovieID = convertIDToInt(tokens[0]);
                int currentActorID = convertIDToInt(tokens[1]);
                Movie movie = movieIDToNode.getOrDefault(currentMovieID, new Movie(currentMovieID));
                Actor actor = actorIDToNode.getOrDefault(currentActorID, new Actor(currentActorID));
                movie.addActor(actor);
                actor.addMovie(movie);
                if (!movieIDToNode.containsKey(currentMovieID)) movieIDToNode.put(currentMovieID, movie);
                if (!actorIDToNode.containsKey(currentActorID)) actorIDToNode.put(currentActorID, actor);
            }

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Movie to actor maps loading Complete!");
    }

}
