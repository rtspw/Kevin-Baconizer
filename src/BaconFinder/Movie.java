package BaconFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import static BaconFinder.Util.*;

public class Movie implements BaconNode {
    private int id;
    private boolean visited = false;
    private Actor prevActor;
    private ArrayList<Actor> actors = new ArrayList<>();

    public static HashMap<Integer, String> movieIDToNameMap = new HashMap<>();

    private static final String TITLE_FILE = "./data/simplified.title.basics.tsv";

    public static void load() {
        System.out.println("Loading movieIDToTitleMap...");
        try (BufferedReader reader = new BufferedReader(new FileReader(TITLE_FILE))) {
            reader.readLine();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] tokens = currentLine.split("\\t");
                movieIDToNameMap.put(convertIDToInt(tokens[0]), tokens[1]);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Movie ID to Title Map loading complete!");
    }

    Movie(int id) {
        this.setID(id);
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
        this.visited = true;
    }

    @Override
    public boolean wasVisited() {
        return visited;
    }

    @Override
    public String getName() { return movieIDToNameMap.getOrDefault(id, "Unknown name"); }

    @Override
    public NodeType getType() {
        return NodeType.MOVIE;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void clearActors() {
        actors.clear();
    }

    public Actor getPrevActor() {
        return prevActor;
    }

    public void setPrevActor(Actor prevActor) {
        this.prevActor = prevActor;
    }

    @Override
    public String toString() {
        String actorList = String.join(
                ", ",
                actors.stream().map(Object::toString).collect(Collectors.joining(", "))
        );
        return "Movie[" + id + "," + actorList + "]";
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
