public class Color{

	public final static String black = "\u001B[30m";
	public final static String red = "\u001B[31m";
	public final static String green = "\u001B[32m";
	public final static String yellow = "\u001B[33m";
	public final static String blue = "\u001B[34m";
	public final static String magenta = "\u001B[35m";
	public final static String cyan = "\u001B[36m";
	public final static String white = "\u001B[37m";
	public final static String reset = "\u001B[0m";
	public final static String bold = "\u001B[1m";
	public final static String boldOff = "\u001B[21m";
	public final static String strike = "\u001B[9m";

	public String toString(){
		return black + "Black\n" + reset +
		red + "Red\n" + reset +
		green + "Green\n" + reset +
		yellow + "Yellow\n" + reset +
		blue + "Blue\n" + reset +
		magenta + "Magenta\n" + reset +
		cyan + "Cyan\n" + reset +
		white + "White\n" + reset +
		bold + "Bold\n" + reset +
		strike + "Strike" + reset;
	}
}