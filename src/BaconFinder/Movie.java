package BaconFinder;

import java.util.ArrayList;
import java.util.stream.Collectors;
import BaconDB.BaconConnection;

public class Movie implements BaconNode {
    private BaconConnection connection = BaconConnection.getInstance();
    private int id;
    private boolean visited = false;
    private Actor prevActor;
    private ArrayList<Actor> actors = new ArrayList<>();

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
    public String getName() { return connection.getMovieNameFromID(id); }

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
        return "Movie[" + id + ", " + getName() + "]";
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
