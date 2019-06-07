package BaconFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import static BaconFinder.Util.*;

public class Actor implements BaconNode {

    public static HashMap<Integer, String> actorIDToNameMap = new HashMap<>();

    static {
        System.out.println("Loading actorIDToNameMap...");
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/simplified.name.basics.tsv"))) {
            reader.readLine();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] tokens = currentLine.split("\\t");
                actorIDToNameMap.put(convertIDToInt(tokens[0]), tokens[1]);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        System.out.println("ActorIDToNameMap loading complete!");
    }

    private int id;
    private boolean visited = false;
    private Movie prevMovie;
    private ArrayList<Movie> movieList = new ArrayList<>();

    Actor(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setVisited() {
        visited = true;
    }

    @Override
    public boolean wasVisited() {
        return visited;
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