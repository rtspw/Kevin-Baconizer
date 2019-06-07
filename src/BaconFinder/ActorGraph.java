package BaconFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

import static BaconFinder.Util.*;

public class ActorGraph {

    private static HashMap<Integer, Movie> movieIDToNode = new HashMap<>();
    private static HashMap<Integer, Actor> actorIDToNode = new HashMap<>();

    public static void loadMovieToAndFromActorMaps() {
        System.out.println("Loading movie to actor maps...");
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/sortedHyperFiltered.tsv"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] tokens = currentLine.split("\\t");
                int currentMovieID = convertIDToInt(tokens[0]);
                int currentActorID = convertIDToInt(tokens[1]);

                Actor actor = actorIDToNode.getOrDefault(currentActorID, new Actor(currentActorID));
                Movie movie = movieIDToNode.getOrDefault(currentMovieID, new Movie(currentMovieID));
                movie.addActor(actor);
                actor.addMovie(movie);

                if (!movieIDToNode.containsKey(currentMovieID)) {
                    movieIDToNode.put(currentMovieID, movie);
                }

                if (!actorIDToNode.containsKey(currentActorID)) {
                    actorIDToNode.put(currentActorID, actor);
                }

            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        System.out.println("Movie to actor maps loading Complete!");
    }

    public static Actor findPath(int startActorID, int endActorID) {
        if (!actorIDToNode.containsKey(startActorID)) return null;
        if (!actorIDToNode.containsKey(endActorID)) return null;
        Actor start = actorIDToNode.get(startActorID);
        start.setVisited();

        LinkedList<Movie> BFSQueue = new LinkedList<>();
        for (Movie movie : start.getMovieList()) {
            movie.setPrevActor(start);
            BFSQueue.add(movie);
        }

        while (!BFSQueue.isEmpty()) {
            //System.out.println(BFSQueue);
            Movie movie = BFSQueue.remove();
            //System.out.println("Looking at " + movie);
            if (movie.wasVisited()) continue;
            movie.setVisited();
            for (Actor actor : movie.getActors()) {
                //System.out.println("Checking " + actor + " who acted in " + actor.getMovieList());
                if (actor.wasVisited()) continue;
                actor.setVisited();
                actor.setPrevMovie(movie);
                if (actor.getID() == endActorID) {
                    return actorIDToNode.get(endActorID);
                }
                for (Movie m : actor.getMovieList()) {
                    if (!m.wasVisited()) {
                        m.setPrevActor(actor);
                        BFSQueue.add(m);
                    }
                }
            }
        }
        return null;
    }
}
