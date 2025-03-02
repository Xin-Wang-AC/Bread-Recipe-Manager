/**
 * File name: RecipeManager.java
 * Author: Xin Wang, 041137648
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: The RecipeManager class maintains the list of recipes and handles file operations.
 * Class list: MenuOption.java, Recipe.java, RecipeManager.java, RecipeManagerTest.java, Assignment3.java
 */
package assn3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.lang.SecurityException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * This class maintains the list of recipes and provides file I/O functionality.
 * It handles loading recipes from a text file and saving shopping lists.
 * It contains four(4) methods including loading recipes, saving shopping lists,
 * retrieving the recipes list and getting the size of the recipes list.
 *
 * @author Xin Wang
 * @version 1.0
 * @see java.io.FileNotFoundException
 * @see java.io.IOException
 * @see java.lang.IllegalStateException
 * @see java.lang.SecurityException
 * @see java.nio.file.Files
 * @see java.nio.file.Path
 * @see java.nio.file.Paths
 * @see java.util.ArrayList
 * @see java.util.Formatter
 * @see java.util.FormatterClosedException
 * @see java.util.HashMap
 * @see java.util.Iterator
 * @see java.util.List
 * @see java.util.Map
 * @see java.util.NoSuchElementException
 * @see java.util.Scanner
 * @see java.util.Set
 * @see Recipe
 * @since JDK 17.0.10
 */
public class RecipeManager {
	
	/**
	 * The list containing all recipes loaded from the file.
	 */
	private List<Recipe> recipes;
	
	/**
     * This is the default constructor for RecipeManager.
     * 
     * It initializes an empty ArrayList to store recipes.
     */
	public RecipeManager() {
        recipes = new ArrayList<>();
	}
	
	/**
	 * This method as a getter returns the list of all recipes.
	 * 
	 * @return The list of Recipe objects to get.
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}
	
	/**
	 * This method as a getter returns the number of all recipes.
	 * 
	 * @return The size of the recipes list to get.
	 */
	public int getRecipesSize() {
		return recipes.size();
	}
	
	/**
	 * This method loads recipes from "recipelist.txt" and stores them in the recipes list.
	 * Each recipe contains a name and five ingredients with quantities.
	 * This method uses relative paths rather than absolute paths.
	 * Any exceptions during file operations are caught and printed.
	 */
	public void loadRecipes() {
		
		/** 
		 * The name of the bread recipe.
		 */
		String recipeName;
		
		/** 
		 * The HashMap to store ingredients and their quantities.
		 */
		Map<String, Double> ingredients;
		
		try (Scanner input = new Scanner(Paths.get("recipelist.txt"))) {
			
			/** 
			 * Repeatedly reads recipes until the end of the file provided.
			 */
			while (input.hasNext()) {
				
				/** 
				 * Skips the recipe indicator.
				 */
				input.next();
				
				recipeName = input.nextLine().trim();
				
				/** 
				 * Creates a new HashMap to store ingredients and their quantities for each recipe.
				 */
				ingredients = new HashMap<>();
				for (int i = 1; i <= 5; i++) {
					ingredients.put(input.next(), input.nextDouble());
				}
				
				/** 
				 * Creates and configures a new Recipe object.
				 */
				Recipe recipe = new Recipe(recipeName, ingredients);
				recipes.add(recipe);
				
			}
			
		}
		catch (IOException | NoSuchElementException | IllegalStateException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method creates a shopping list text file named "shoppinglist.txt".
	 * This file contains all the ingredients the baker will need to purchase based on their orders.
	 * This method uses relative paths rather than absolute paths.
	 * Any exceptions during file operations are caught and printed.
	 * 
	 * @param shoppingList The list of strings recording recipe names and quantities as well as the ingredients.
	 */
	public void saveShoppingList(List<String> shoppingList) {
		
		try (Formatter output = new Formatter("shoppinglist.txt")) {
			
			Iterator<String> shoppingListIterator = shoppingList.iterator();
			String content;
			
			/** 
			 * Writes each item from the shopping list to the file
			 */
			while (shoppingListIterator.hasNext()) {
				
				content = shoppingListIterator.next();
				output.format("%s", content);
				
			}
			
		}
		catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
			e.printStackTrace();
		}
		
	}
	
}