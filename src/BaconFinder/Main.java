package BaconFinder;

public class Main {
    private static final int KEVIN_BACON = 102;
    private static final int HILARY_DUFF = 240381;
    public static void main(String[] args) {
        Actor.load();
        Movie.load();
        ActorGraph.load();
        BaconPath foo = new BaconPath(KEVIN_BACON, HILARY_DUFF);
        if (foo.pathExists()) {
            System.out.println(foo);
        }
    }
}
