/**
 * File name: Recipe.java
 * Author: Xin Wang
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: This class represents a recipe with its name, ingredients, and the quantity ordered.
 */
package assn3;

import java.util.HashMap;

/**
 * The Recipe class encapsulates all the information related to a cooking recipe.
 * It includes the recipe's name, its ingredients, and the quantity ordered for preparation.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.HashMap
 * @since Eclipse IDE 4.31.0
 */
public class Recipe {
	
	/** The name of the recipe. */
	private String recipeName;
	
	/** A map to store ingredients and their respective quantities. */
	private HashMap<String, Double> ingredients;
	
	/** The quantity of this recipe ordered by the user. */
	private int orderedQuantity;
	
	/**
	 * Constructs an empty Recipe object with default values.
	 * - The recipe name is set to an empty string.
	 * - Ingredients map is initialized as an empty HashMap.
	 * - Ordered quantity is set to zero.
	 */
	public Recipe() {
		recipeName = "";
		ingredients = new HashMap<>();
		orderedQuantity = 0;
	}
	
	/**
	 * Retrieves the map of ingredients for this recipe.
	 * 
	 * @return A HashMap where keys are ingredient names and values are their quantities.
	 */
	public HashMap<String, Double> getIngredient() {
		return ingredients;
	}
	
	/**
	 * Adds an ingredient to the recipe with its quantity.
	 * 
	 * @param ingredient The name of the ingredient to add.
	 * @param quantity The quantity of the ingredient.
	 */
	public void addIngredient(String ingredient, double quantity) {
		ingredients.put(ingredient, quantity);
	}
	
	/**
	 * Gets the name of the recipe.
	 * 
	 * @return The name of the recipe as a String.
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Sets the name of the recipe.
	 * 
	 * @param recipeName The new name to set for the recipe.
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * Gets the quantity of this recipe that has been ordered.
	 * 
	 * @return The number of times this recipe has been ordered.
	 */
	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	/**
	 * Sets the quantity of this recipe that has been ordered.
	 * 
	 * @param orderedQuantity The new quantity to set for this recipe's orders.
	 */
	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
}
