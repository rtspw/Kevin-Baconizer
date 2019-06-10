package BaconFinder;

import java.util.ArrayList;
import BaconDB.BaconConnection;

public class Actor implements BaconNode {

    private BaconConnection connection = BaconConnection.getInstance();
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

    @Override
    public String getName() { return connection.getActorNameFromID(id); }

    @Override
    public NodeType getType() {
        return NodeType.ACTOR;
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
        return "Actor[" + id + ", " + getName() +"]";
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