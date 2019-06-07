package BaconFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import static BaconFinder.Util.*;

public class Actor {

    public static HashMap<Integer, String> actorIDToNameMap = new HashMap<>();

    static {
        System.out.println("Loading actorIDToNameMap...");
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/filteredNames.tsv"))) {
            reader.readLine();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] tokens = currentLine.split("\\t");
                actorIDToNameMap.put(convertIDToInt(tokens[0]), tokens[1] + " " + tokens[2]);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        System.out.println("ActorIDToNameMap loading complete!");
    }

    private int id;
    private boolean visited = false;
    private Actor prevActor;
    private Movie prevMovie;
    private ArrayList<Movie> movieList = new ArrayList<>();

    Actor(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    void setVisited() {
        visited = true;
    }
    boolean wasVisited() {
        return visited;
    }

    public Actor getPrevActor() {
        return prevActor;
    }

    public void setPrevActor(Actor prevActor) {
        this.prevActor = prevActor;
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public Movie getPrevMovie() {
        return prevMovie;
    }

    public void setPrevMovie(Movie prevMovie) {
        this.prevMovie = prevMovie;
    }

    @Override
    public String toString() {
        return "Actor[" + id + ", " + actorIDToNameMap.getOrDefault(id, "Unknown Name") +"]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return id == o.hashCode();
    }
}