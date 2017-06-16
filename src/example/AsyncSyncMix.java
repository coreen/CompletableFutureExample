package example;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import model.Action;
import model.Pizza;

/**
 * Composes multiple independent steps together.
 * 
 * @author Coreen Yuen
 */
public class AsyncSyncMix {
	public static void main(String[] args) {
		/*
		 * .thenComposeAsync() chains the successful results of
		 * `cookPizza` into a separate nested synchronous function
		 */
		// 1. Cook the pizza.
		CompletableFuture<Pizza> cookPizza = CompletableFuture.supplyAsync(() -> Action.cookPizza());
		// 2. Action definition for additional pineapple topping.
		Function<Pizza, CompletableFuture<Pizza>> addPineappleTopping = (pizza) ->
			CompletableFuture.supplyAsync(() -> pizza.addTopping("pineapple", 1));
		/*
		 * Note here that .addTopping() is a synchronous function.
		 */
		// 3. Execute that action.
		CompletableFuture<Pizza> preparedPizza = cookPizza
			.thenComposeAsync((pizza) -> {
				CompletableFuture<Pizza> morePineapplesPizza = addPineappleTopping.apply(pizza);
				// 4. Additional ham topping in a nested fashion.
				return morePineapplesPizza.supplyAsync(() -> pizza.addTopping("chicken", 1));
				/*
				 * Note here that the .thenComposeAsync() treats all the topping
				 * additions as a single CompletionStage.
				 */
			});
		// 5. Eat it!
		preparedPizza.thenAcceptAsync(Action::eat).join();
	}
}
