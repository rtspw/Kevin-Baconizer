package BaconFinder;

import java.util.LinkedList;

public class BaconPath {

    private LinkedList<Movie> BFSQueue = new LinkedList<>();
    private LinkedList<BaconNode> path = new LinkedList<>();
    boolean pathExists = false;

    public BaconPath(int startActorID, int endActorID) {
        Actor endActor = findPath(startActorID, endActorID);
        if (!(endActor == null)) {
            pathExists = true;
            tracebackPath(endActor);
        }
    }

    public boolean pathExists() {
        return pathExists;
    }

    public LinkedList<BaconNode> getPath() {
        return path;
    }

    private void tracebackPath(Actor endActor) {
        Actor tracebackActor = endActor;
        Movie tracebackMovie = tracebackActor.getPrevMovie();
        while (tracebackMovie != null) {
            path.add(tracebackActor);
            path.add(tracebackMovie);
            tracebackActor = tracebackMovie.getPrevActor();
            tracebackMovie = tracebackActor.getPrevMovie();
        }
    }

    private Actor findPath(int startActorID, int endActorID) {
        if (!ActorGraph.actorIDToNode.containsKey(startActorID)) return null;
        if (!ActorGraph.actorIDToNode.containsKey(endActorID)) return null;

        Actor startActor = ActorGraph.actorIDToNode.get(startActorID);
        addMoviesOfActorToQueue(startActor);
        startActor.setVisited();

        while (!BFSQueue.isEmpty()) {
            Movie currentMovie = BFSQueue.remove();
            if (currentMovie.wasVisited()) continue;
            currentMovie.setVisited();
            for (Actor currentActor : currentMovie.getActors()) {
                if (currentActor.wasVisited()) continue;
                currentActor.setVisited();
                currentActor.setPrevMovie(currentMovie);
                if (currentActor.getID() == endActorID) {
                    return ActorGraph.actorIDToNode.get(endActorID);
                }
                addMoviesOfActorToQueue(currentActor);
            }
        }
        return null;
    }

    private void addMoviesOfActorToQueue(Actor actor) {
        for (Movie movie : actor.getMovieList()) {
            if (movie.wasVisited()) continue;
            movie.setPrevActor(actor);
            BFSQueue.add(movie);
        }
    }
}
