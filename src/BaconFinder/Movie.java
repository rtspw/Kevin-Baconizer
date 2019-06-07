package BaconFinder;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Movie implements BaconNode {
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
