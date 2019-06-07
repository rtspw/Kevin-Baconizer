package BaconFinder;

import java.util.LinkedList;

public class Main {

    private static final int KEVIN_BACON = 102;
    private static final int HILARY_DUFF = 240381;


    public static void main(String[] args) {
        ActorGraph.loadMovieToAndFromActorMaps();
        Actor finalActor = ActorGraph.findPath(HILARY_DUFF, KEVIN_BACON);
        if (finalActor == null) {
            System.out.println("Path not found.");
        } else {
            Actor currentActor = finalActor;
            Movie currentMovie = currentActor.getPrevMovie();
            while (currentMovie != null) {
                System.out.println(currentActor);
                System.out.println(currentMovie);
                currentActor = currentMovie.getPrevActor();
                currentMovie = currentActor.getPrevMovie();
            }
        }
    }
}
