package BaconFinder;

import java.util.LinkedList;

public class BaconPath {

    private LinkedList<Movie> BFSQueue = new LinkedList<>();
    private LinkedList<BaconNode> path = new LinkedList<>();
    private int startActorID;
    private int endActorID;
    private boolean pathExists = false;

    public BaconPath(int startActorID, int endActorID) {
        this.startActorID = startActorID;
        this.endActorID = endActorID;
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

    public String getPathText() {
        if (!pathExists) return "No Path Found.";
        StringBuilder pathText = new StringBuilder();
        for (BaconNode node : path) {
            if (node.getType() == NodeType.ACTOR) {
                pathText.append("\"");
                pathText.append(node.getName());
                pathText.append("\"");
                pathText.append("\nacted in the movie\n");
            } else {
                pathText.append("\"");
                pathText.append(node.getName());
                pathText.append("\"");
                pathText.append("\nwhich had the actor\n");
            }
        }
        pathText.append("\"");
        pathText.append(new Actor(startActorID).getName());
        pathText.append("\"");
        return pathText.toString();
    }

    private void tracebackPath(Actor endActor) {
        Actor tracebackActor = endActor;
        Movie tracebackMovie = tracebackActor.getPrevMovie();
        while (tracebackMovie != null) {
            System.out.println(tracebackActor);
            System.out.println(tracebackMovie);
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

    @Override
    public String toString() {
        return getPathText();
    }
}
