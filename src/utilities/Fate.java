package utilities;

import java.util.Random;

public class Fate {

	private static Random rand = new Random();

	public static boolean breakDown() {
		return rand.nextBoolean();
	}

	public static boolean generateFixable() { return rand.nextInt(100) > 1;
	}
	private static float generateReduction() {
		return rand.nextFloat();
	}

	private static int generateTurns() {
		return rand.nextInt(5) + 1;
	}

	public static void setSeed(int seed) {
		rand.setSeed(seed);
	}

	public static Mishap generateMishap() {
		return new Mishap(generateFixable(), generateTurns(), generateReduction());
	}


}
