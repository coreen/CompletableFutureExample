package example;

import java.util.concurrent.*;
import model.Action;
import model.Pizza;

public class PreCompletableFuture {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Pizza> future = executor.submit(new Callable<Pizza>() {
	    	public Pizza call() {
            	return Action.cookPizza();
	    	}
	    });
		 
		try {
			// blocking operation
			Pizza food = future.get();
			System.out.println("food: " + food);
		} catch (final InterruptedException | ExecutionException e) {
			System.out.println("not cooked");
		}
	}
}
