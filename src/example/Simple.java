package example;

import java.util.concurrent.CompletableFuture;
import model.Action;

/**
 * Simple CompletableFuture usage. 3 possible parameters to chain:
 * - supplier => Function(Void, T)
 * - function => Function(T, O)
 * - consumer => Function(O, Void)
 * 
 * @author Coreen Yuen
 */
public class Simple {
	public static void main(String[] args) {
		CompletableFuture
			// Supplier chain.
			.supplyAsync(() -> Action.cookPizza(pizza))
			// Function chain.
			.thenApply(Action::slicePizza)
			// Consumer chain.
			.thenAccept(Action::eat);
	}
}
