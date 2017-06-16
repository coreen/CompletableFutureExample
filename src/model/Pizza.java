package model;

import java.util.Map;

/**
 * Pizza model for food example.
 * 
 * @author Coreen Yuen
 */
public class Pizza {
	// EX. {"pepperoni => 5, "olives" => 0, "green pepper" => 3}
	private Map<String, Integer> toppings;
	// state of preparedness
	private CookedLevel cooked;
	// whether or not the food has been consumed
	private boolean eaten;
	// number of slices cut into
	private int slices;
	
	/**
	 * Constructor.
	 * 
	 * @param toppings
	 */
	public Pizza(Map<String, Integer> toppings) {
		this.toppings = toppings;
	}
	
	/**
	 * Add a topping to existing pizza.
	 * 
	 * @param topping
	 * @param count
	 */
	public void addTopping(String topping, int count) {
		toppings.put(topping, count);
	}
	
	/**
	 * Set the preparedness level of the food.
	 * 
	 * @param cooked
	 */
	public void setCookedLevel(CookedLevel cooked) {
		this.cooked = cooked;
	}
	
	/**
	 * Set state of consumption.
	 * 
	 * @param eaten
	 */
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	
	/**
	 * Set number of slices cut into.
	 * 
	 * @param slices
	 */
	public void setSlices(int slices) {
		this.slices = slices;
	}
	
	/**
	 * Retrieves toppings of pizza.
	 * 
	 * @return toppings
	 */
	public Map<String, Integer> getToppings() {
		return toppings;
	}
	
	/**
	 * Retrieves preparedness level of food.
	 * 
	 * @return cooked
	 */
	public CookedLevel getCookedLevel() {
		return cooked;
	}
	
	/**
	 * Retrieves state of consumption.
	 * 
	 * @return eaten
	 */
	public boolean getEaten() {
		return eaten;
	}
	
	/**
	 * Retrieves number of slices cut into.
	 * 
	 * @return
	 */
	public int getSlices() {
		return slices;
	}
}
