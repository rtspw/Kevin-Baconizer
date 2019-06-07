package BaconFinder;

public class Util {
    public static int convertIDToInt(String strID) {
        return Integer.parseInt(strID, 10);
    }
    public static String[] tokenizeTSV(String line) {
        return line.split("\\t");
    }
}
