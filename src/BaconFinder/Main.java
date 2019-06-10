package BaconFinder;

public class Main {
    private static final int KEVIN_BACON = 102;
    private static final int HILARY_DUFF = 240381;
    public static void main(String[] args) {
        ActorGraph.load();
        BaconPath foo = new BaconPath(2, 9);
        System.out.println(foo);
    }
}
