package BaconFinder;

public interface BaconNode {
    int getID();
    void setID(int id);
    void setVisited();
    boolean wasVisited();
    String getName();
    NodeType getType();
}
