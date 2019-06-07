package BaconFinder;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Movie {
    private int id;
    private boolean visited = false;
    private Movie prevMovie;
    private Actor prevActor;
    private ArrayList<Actor> actors = new ArrayList<>();

    Movie(int id) {
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVisited() {
        this.visited = true;
    }

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

    public Movie getPrevMovie() {
        return prevMovie;
    }

    public void setPrevMovie(Movie prevMovie) {
        this.prevMovie = prevMovie;
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
