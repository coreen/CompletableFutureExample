package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Long-running blocking actions for use in examples.
 * 
 * @author Coreen Yuen
 */
public class Action {
	
	public static final int DEFAULT_SLICES = 8;

	/**
	 * Simulates time to cook a pizza.
	 * 
	 * @return pizza
	 */
	public static Pizza cookPizza() {
		// 1. Create the Pizza interacting with.
		Map<String, Integer> toppings = new HashMap<>();
		toppings.put("ham", 7);
		toppings.put("pineapple", 11);
		Pizza pizza = new Pizza(toppings);
		// 2. Cook it!
		try {
			Thread.sleep(3000L); // 3 sec
			pizza.setCookedLevel(CookedLevel.PERFECT);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		return pizza;
	}

	/**
	 * Simulates time to slice a pizza.
	 * 
	 * @param pizza
	 * @return pizza
	 */
	public static Pizza slicePizza(Pizza pizza) {
		try {
			Thread.sleep(2000L); // 2 sec
			pizza.setSlices(DEFAULT_SLICES);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		return pizza;
	}
	
	/**
	 * Simulates time to eat a pizza.
	 * 
	 * @param pizza
	 */
	public static void eat(Pizza pizza) {
		try {
			Thread.sleep(5000L); // 5 sec
			pizza.setEaten(true);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
