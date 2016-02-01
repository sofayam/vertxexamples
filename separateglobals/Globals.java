package separateglobals;

public class Globals {

    static int global = 0;

    public static int inc(int incval) {
	global += incval;
	return global;
    }

}
