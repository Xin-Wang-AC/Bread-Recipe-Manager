/**
 * File name: RecipeManagerTest.java
 * Author: Xin Wang, 041137648
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: The RecipeManagerTest class serves as the entry point in the development environment,
 *          managing recipes using the RecipeManager class. 
 *          It allows users to view available recipes, create shopping lists, and print or save shopping lists.
 * Class list: MenuOption.java, Recipe.java, RecipeManager.java, RecipeManagerTest.java, Assignment3.java
 */
package assn3;

import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * The RecipeManagerTest class contains the main method, which serves as the entry point in the development environment.
 * It manages recipes using the RecipeManager class and provides functionalities to view recipes,
 * create shopping lists, and print or save shopping lists.
 *
 * @author Xin Wang
 * @version 1.0
 * @see java.io.IOException
 * @see java.lang.IllegalStateException
 * @see java.util.ArrayList
 * @see java.util.HashMap
 * @see java.util.InputMismatchException
 * @see java.util.Iterator
 * @see java.util.List
 * @see java.util.Map
 * @see java.util.NoSuchElementException
 * @see java.util.Scanner
 * @see java.util.Set
 * @see Recipe
 * @see RecipeManager
 * @see MenuOption
 * @since JDK 17.0.10
 */
public class RecipeManagerTest {
	
	/**
     * An array of MenuOption enum values representing all possible menu choices.
     */
	private final static MenuOption[] choices = MenuOption.values();
	
	/**
     * The main method serves as the entry point for the RecipeManagerTest class in the development environment.
     * It initializes the RecipeManager, loads recipes, and manages user interactions.
     *
     * @param args Command-line arguments.
     */
	public static void main(String[] args) {
		
		
		RecipeManager manager = new RecipeManager();
		
		manager.loadRecipes();
		
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Welcome to Xin Wang recipe manager.%n");
		
		MenuOption request = getRequest(input);
		
		while (request != MenuOption.QUIT_PROGRAM) {
			
			switch (request) {
			
			case SHOW_RECIPES:
				showRecipes(manager);
				break;
			case CREATE_SHOPPING_LIST:
				createShoppingList(manager, input);
				break;
			case PRINT_SHOPPING_LIST:
				printShoppingList(manager, input);
				break;
			case QUIT_PROGRAM:
				System.exit(0);
				break;
			case REPRINT_THIS_MENU:
				break;
			}
			
			request = getRequest(input);
			
		}

	}
	
	/**
     * The getRequest method displays the menu containing options and prompts the user to select an option.
     * It validates user input to ensure it is within the acceptable range (0-4).
     * 
     * @param input The Scanner object used to read user input.
     * @return The MenuOption The enum value corresponding to the user's selection.
     */
	private static MenuOption getRequest(Scanner input) {
		
		int request = 4;
		
		System.out.printf("Please select one of the following options: %n%s%n%s%n%s%n%s%n%s%n", 
			"1. Show available recipes.",
			"2. Create Shopping List.",
			"3. Print Shopping List.",
			"4. Quit Program.",
			"0. to reprint this menu.");
		
		boolean invalidRequest = true;
		
		do {
		
			try {
				
				System.out.printf("Please enter your choice: ");
				request = input.nextInt();
				
				if ((request < 0) || (request > 4)) {
					System.err.println("Valid input are digits from 0 to 4.");
					input.nextLine();
				} else {
					invalidRequest = false;
				}
			}
			catch (InputMismatchException e) {
				System.err.println("Please only type digits.");
				input.nextLine();
			}
		
		} while (invalidRequest);
		
		return choices[request];
		
	}
	
	/**
     * The showRecipes method displays all available recipes along with their numbers.
     * The Recipes are numbered from 1 to n, where n is the total number of recipes.
     * 
     * @param manager The RecipeManager object containing the recipes.
     */
	private static void showRecipes(RecipeManager manager) {
		
		List<Recipe> recipes = manager.getRecipes();
		Iterator<Recipe> recipesIterator = recipes.iterator();
		Recipe recipe;
		String recipeName;
		int recipeNumber = 1;
		
		System.out.printf("Available Recipes: %n");
		
    	while (recipesIterator.hasNext()) {
    		
    		recipe = recipesIterator.next();
    		recipeName = recipe.getRecipeName();
    		System.out.printf("%d. %s%n", recipeNumber, recipeName);
    		recipeNumber++;
    		
    	}
		
	}
	
	/**
     * The createShoppingList method allows the user to order a specific type of bread by selecting a recipe number and quantity.
     * It validates user input to ensure the recipe number is within range and the quantity is positive.
     * It prevents negative orders by checking if the total quantity would be negative.
     * 
     * @param manager The RecipeManager object containing the recipes.
     * @param input The Scanner object used to read user input.
     */
	private static void createShoppingList(RecipeManager manager, Scanner input) {
		
		List<Recipe> recipes = manager.getRecipes();
		
		int recipeNumber = 0;
		int orderingQuantity = 0;
		boolean invalidNumber = true;
		boolean invalidQuantity = true;
		int recipesSize = manager.getRecipesSize();
		boolean ordered = false;
		
		do {
			
			try {
				System.out.printf("Which bread would you like? ");
				recipeNumber = input.nextInt();
				
				if ((recipeNumber < 1) || (recipeNumber > recipesSize)) {
					System.err.println("Please type digits from 1 to " + recipesSize + ".");
					input.nextLine();
				} else {
					invalidNumber = false;
				}
			}
			catch (InputMismatchException e) {
				System.err.println("Please only type digits.");
				System.err.println("Valid input are digits from 1 to "+ recipesSize + ".");
				input.nextLine();
			}
			
		} while (invalidNumber);
		
		do {
			
			do {
				
				try {
					System.out.printf("How much of this bread would you like? ");
					orderingQuantity = input.nextInt();
					invalidQuantity = false;
				}
				catch (InputMismatchException e) {
					System.err.println("Please only type digits.");
					input.nextLine();
				}
				
			} while (invalidQuantity);
			
			ordered = orderRecipe(recipes, recipeNumber, orderingQuantity);
				
			if (!ordered) {
				System.err.println("The total quantity of bread cannot be negative.");
				input.nextLine();
			}
			
		} while (!ordered);
		
	}
	
	/**
     * The orderRecipe method updates the ordered quantity for a specific recipe.
     * It ensures that the total quantity after the update is not negative.
     * 
     * @param recipes The list of Recipe objects.
     * @param recipeNumber The number of the recipe to order.
     * @param orderingQuantity The quantity to add to the current order.
     * 
     * @return true if the order was successful, false if it would result in a negative quantity.
     */
	private static boolean orderRecipe(List<Recipe> recipes, int recipeNumber, int orderingQuantity) {
		
		boolean ordered = false;
		
		Recipe orderedRecipe = recipes.get(recipeNumber - 1);
		
		int orderedQuantity = orderedRecipe.getOrderedQuantity();
		int totalQuantity = orderedQuantity + orderingQuantity;
		
		if (totalQuantity >= 0) {
			
			orderedRecipe.setOrderedQuantity(totalQuantity);
			ordered = true;
			
		}
		
		return ordered;
	}
	
	/**
     * The printShoppingList method prints the shopping list and asks the user if they want to save it to a file.
     * If the user enters "Y", the shopping list will be saved.
     * 
     * @param manager The RecipeManager object used to save the shopping list.
     * @param input The Scanner object used to read user input.
     */
	private static void printShoppingList(RecipeManager manager, Scanner input) {
		
		List<String> shoppingList = generateShoppingList(manager);
		Iterator<String> shoppingListIterator = shoppingList.iterator();
		String content;
		String userDecision;
		
		while (shoppingListIterator.hasNext()) {
			
			content = shoppingListIterator.next();
			System.out.printf(content);
			
		}
		
		System.out.printf("Do you want to save this list (Y/n)? ");
			
			userDecision = input.next();
			
			if (userDecision.equals("Y")) {
				
				manager.saveShoppingList(shoppingList);
				
			}
			
	}
	
	/**
     * The generateShoppingList method generates a shopping list based on the ordered recipes.
     * The shopping list includes the names and quantities of ordered recipes, as well as the total quantities of ingredients needed.
     * It only shows recipes and ingredients with non-zero quantities.
     * 
     * @param manager The RecipeManager object containing the recipes.
     * @return A List of Strings representing the shopping list.
     */
	private static List<String> generateShoppingList(RecipeManager manager) {
		
		List<Recipe> recipes = manager.getRecipes();
		Iterator<Recipe> recipesIterator = recipes.iterator();
		Recipe recipe;
		String recipeName;
		int orderedQuantity;
		
		Map<String, Double> ingredients;
		Map<String, Double> totalIngredients = new HashMap<>();
		totalIngredients.put("butter", 0.0);
		totalIngredients.put("eggs", 0.0);
		totalIngredients.put("flour", 0.0);
		totalIngredients.put("sugar", 0.0);
		totalIngredients.put("yeast", 0.0);
		
		Set<String> keySet;
		double ingredientAmount;
		double subtotalAmount;
		double totalAmount;
		
		List<String> shoppingList = new ArrayList<>();
		
		while (recipesIterator.hasNext()) {
    		
    		recipe = recipesIterator.next();
    		recipeName = recipe.getRecipeName();
    		orderedQuantity = recipe.getOrderedQuantity();
    		
    		if (orderedQuantity > 0) {
    			
    			shoppingList.add(String.format("%d %s loaf/loaves.%n", orderedQuantity, recipeName));
    				
    			ingredients = recipe.getIngredients();
    			keySet = ingredients.keySet();
    			
    			for (String key : keySet) {
    				
    				ingredientAmount = ingredients.get(key);
    				
    				if (ingredientAmount != 0) {
    					
    					totalAmount = totalIngredients.get(key);
    					
    					subtotalAmount = ingredientAmount * orderedQuantity;
    					
    					totalAmount += subtotalAmount;
    					
    					totalIngredients.put(key, totalAmount);
    					
    				}
    				
    			}
    			
    		}
    		
    	}
		
		shoppingList.add(String.format("%nYou will need a total of: %n"));
		
		keySet = totalIngredients.keySet();
		
		for (String key : keySet) {
			
			totalAmount = totalIngredients.get(key);
			
			if (totalAmount != 0) {
				
				if (key.equals("eggs")) {
					
					shoppingList.add(String.format("%.0f egg(s)%n", totalAmount));
					
				} else {
					
					shoppingList.add(String.format("%.1f grams of %s%n", totalAmount, key));
					
				}
				
			}
			
		}
		
		return shoppingList;
		
	}

}