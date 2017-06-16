package example;

import java.util.concurrent.CompletableFuture;
import model.Action;
import model.Pizza;

/**
 * Combining multiple dependent CompletableFutures together.
 * 
 * @author Coreen Yuen
 */
public class Multiple {
	public static void main(String[] args) {
		/*
		 * .thenCombineAsync() takes 2 successful CompletionStages:
		 *    - setTable
		 *    - preparedPizza
		 * and combines the results of both
		 */
		// 1. Set the table.
		CompletableFuture<Integer> setTable = CompletableFuture.supplyAsync(() -> setTable());
		// 2. Cook the pizza.
		CompletableFuture<Pizza> cookPizza = CompletableFuture.supplyAsync(() -> Action.cookPizza());
		// 3. Combine the results of both to serve customer.
		CompletableFuture<Void> chain = setTable
			.thenCombineAsync(
					cookPizza,
					(tableNumber, pizza) -> {
						// 4. Serve the customer.
						System.out.println("serving table #" + tableNumber +
								" with pizza: " + pizza.toString());
						return pizza;
					})
			// 5. Eat it!
			.thenAcceptAsync(Action::eat);
		chain.join();
		/*
		 * Just like with the function/consumer chain types, there
		 * exist a non-*Async version as well that executes the
		 * action within the same thread. Use the *Async version to
		 * take full advantage of thread parallelism.
		 */
	}
	
	/**
	 * Simulates the time it takes to set a table.
	 * 
	 * @return table number
	 */
	private static int setTable() {
		try {
			Thread.sleep(3000L); // 3 sec
			return (int) Math.random() * 100;
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
