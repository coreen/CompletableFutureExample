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
		CompletableFuture<Void> chain = CompletableFuture
			// Supplier chain.
			.supplyAsync(() -> Action.cookPizza())
			// Function chain.
			.thenApply(Action::slicePizza)
			// Consumer chain.
			.thenAccept(Action::eat);
		chain.join();
		/*
		 * Note that there are 2 types of function and consumer chains:
		 * - function
		 *     - thenApply
		 *     - thenApplyAsync
		 * - consumer
		 *     - thenAccept
		 *     - thenAcceptAsync
		 * 
		 * The chains with *Async naming spin up a new thread for the
		 * chained action. This is the recommended method of the 2
		 * options to use on a regular basis.
		 */
	}
}
