package example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class EitherOr {
	private static Random random = new Random();

	public static void main(String[] args) {
		// 1. Prepare possible side dish actions.
		CompletableFuture<String> prepSalad = CompletableFuture.supplyAsync(() -> prepSalad());
		CompletableFuture<String> heatSoup = CompletableFuture.supplyAsync(() -> heatSoup());
		// 2. Whichever one completes first gets served.
		prepSalad
			.acceptEither(heatSoup, (sideDish) -> {
				System.out.println("serving " + sideDish);
			})
			.join();
	}
	
	/**
	 * Simulates time to prepare a salad. Variable depending on ingredients.
	 * 
	 * @return salad
	 */
	private static String prepSalad() {
		try {
			Thread.sleep(random.nextInt(2000)); // between 0 sec and 2 sec
			return "salad";
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Simulates time to head pre-cooked soup. Fixed time.
	 * 
	 * @return soup
	 */
	private static String heatSoup() {
		try {
			Thread.sleep(1000L); // 1 sec
			return "soup";
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
