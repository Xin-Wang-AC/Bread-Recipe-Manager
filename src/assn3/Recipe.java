/**
 * File name: Recipe.java
 * Author: Xin Wang, 041137648
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: The Recipe class serves as a data container for bread recipe information.
 * Class list: MenuOption.java, Recipe.java, RecipeManager.java, RecipeManagerTest.java, Assignment3.java
 */
package assn3;

import java.util.HashMap;
import java.util.Map;

/**
 * This class encapsulates the data of a specific bread recipe.
 * It contains three(3) attributes representing a recipe
 * including recipeName, ingredients, and orderedQuantity.
 * There are several getters and setters one pair per attribute.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.HashMap
 * @see java.util.Map
 * @since JDK 17.0.10
 */
public class Recipe {
    
    /**
     * The name of the bread recipe.
     */
    private String recipeName;
    
    /**
     * A map containing ingredients as keys and their required quantities as values.
     */
    private Map<String, Double> ingredients;
    
    /**
     * The quantity of this bread that has been ordered.
     */
    private int orderedQuantity;
    
    /**
     * This is the default constructor for Recipe.
     * 
     * It initializes a new instance of Recipe with an empty name,
     * an empty ingredients map, and zero ordered quantity.
     */
    public Recipe() {
        recipeName = "";
        ingredients = new HashMap<>();
        orderedQuantity = 0;
    }
    
    /**
     * This is the overloaded constructor to initialize a recipe with its name and ingredients.
     * 
     * @param recipeName The name of the recipe.
     * @param ingredients The ingredients of the recipe.
     */
	public Recipe(String recipeName, Map<String, Double> ingredients) {
		
		this(recipeName, ingredients, 0);
		
	}
	
	/**
     * This is the overloaded constructor to initialize a recipe with its name, ingredients and the quantity that has been ordered.
     * 
     * @param recipeName The name of the recipe.
     * @param ingredients The ingredients of the recipe.
     * @param orderedQuantity The quantity of the recipe that has been ordered.
     */
	public Recipe(String recipeName, Map<String, Double> ingredients, int orderedQuantity) {
		
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.orderedQuantity = orderedQuantity;
		
	}
    
    
    /**
     * This method as a getter returns the ingredients.
     * 
     * @return The ingredients to get.
     */
    public Map<String, Double> getIngredients() {
        return ingredients;
    }
    
    /**
     * This method as a setter sets the ingredients.
     * 
     * @param ingredients The ingredients to set.
     */
    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }
    
    /**
     * This method as a getter returns the recipe name.
     * 
     * @return The recipe name to get.
     */
    public String getRecipeName() {
        return recipeName;
    }
    
    /**
     * This method as a setter sets the recipe name.
     * 
     * @param recipeName The recipe name to set.
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    
    /**
     * This method as a getter returns the ordered quantity.
     * 
     * @return The ordered quantity to get.
     */
    public int getOrderedQuantity() {
        return orderedQuantity;
    }
    
    /**
     * This method as a setter sets the ordered quantity.
     * 
     * @param orderedQuantity The ordered quantity to set.
     */
    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
    
}