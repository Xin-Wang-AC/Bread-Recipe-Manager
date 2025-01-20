/**
 * File name: Assignment3.java
 * Author: Xin Wang
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: This program manages a collection of recipes, allows users to 
 *          view available recipes, order the recipe they want, preview and print a shopping list, through a menu-driven interface.
 */
package assn3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class provides a menu-driven interface for managing recipes, 
 * allowing users to view recipes, order them, and generate shopping lists.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see RecipeManager
 * @see Recipe
 * @see java.util.ArrayList
 * @see java.util.HashMap
 * @see java.util.InputMismatchException
 * @see java.util.Iterator
 * @see java.util.Scanner
 * @since Eclipse IDE 4.31.0
 */
public class Assignment3 {
    
	/**
     * The main method serves as the entry point for the program. It initializes 
     * the RecipeManager, loads recipes, and manages the program flow based on user input.
     * 
     * @param args Command line arguments (not used in this program).
     * @throws IOException if an I/O error occurs while reading the recipe list file or writing the shopping list file.
     */
    public static void main(String[] args) throws IOException {
    	
    	RecipeManager recipeManager = new RecipeManager();
    	int userChoice;
    	boolean quit = false;
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.print("Please enter where the recipe list is: ");
    	String recipeListPath = scanner.nextLine();
    	HashMap<Integer, Recipe> completeNumberedRecipeList = recipeManager.loadRecipeList(recipeListPath);
    	
    	String menuOptions = "Please select one of the following options:\n"
    			+ "1. Show available recipes.\n"
    			+ "2. Create Shopping List.\n"
    			+ "3. Print Shopping List.\n"
    			+ "4. Quit Program.\n"
    			+ "0. to reprint this menu.";
    	String menuPrompt = "Please enter your choice: ";
    	
    	System.out.println("Welcome to Xin Wang recipe manager.");
    	
    	do {
    			System.out.println(menuOptions);
    			System.out.print(menuPrompt);
    			try {
    				userChoice = scanner.nextInt();
    				scanner.nextLine();
    				switch(userChoice) {
    				
    					case 1:
    						displayMenu(recipeManager);
    						break;
    					case 2:
    						orderRecipe(recipeManager, scanner, completeNumberedRecipeList);
    						break;
    					case 3:
    						generateCurrentShoppingList(recipeManager, scanner);
    						break;
    					case 4:
    						quit = true;
    						break;
    					case 0:
    						break;
    					default:
    						throw new InputMismatchException("Please only type digits.\n"
    											+ "Valid input are digits from 0 to 4.");
    				} 
    			} catch (InputMismatchException e) {
    				System.err.println("Please only type digits.\n"
							+ "Valid input are digits from 0 to 4.");
    				scanner.nextLine();
    			}
    			
    	
    	} while (!quit);

    }
   
    /**
     * Displays the current menu of available recipes.
     * 
     * @param recipeManager The RecipeManager instance containing the menu to display.
     */
    private static void displayMenu(RecipeManager recipeManager) {
    		
    	ArrayList<String> currentMenu = recipeManager.getMenu();
    	Iterator<String> menuIterator = currentMenu.iterator();
    	System.out.println("Available Recipes: ");
    	
    	while(menuIterator.hasNext()) {
    		
    		String currentRecipe = menuIterator.next();
    		System.out.println(currentRecipe);
    		
    	}
    	
    }
    
    /**
     * Allows the user to order a recipe by selecting its number and specifying the quantity.
     * 
     * @param recipeManager The RecipeManager instance to manage the order.
     * @param scanner Scanner object for user input.
     * @param numberedRecipeListInUse The map of recipes where keys are recipe numbers.
     */
    private static void orderRecipe(RecipeManager recipeManager, Scanner scanner, HashMap<Integer, Recipe> numberedRecipeListInUse) {
    	
    	int totalRecipeNumber = recipeManager.getRecipeOrder().size();
    	int recipeNumber = 0;
    	int orderingQuantity = 0;
    	int currentQuantity;
    	
    	boolean validRecipeNumber = false;
    	while (!validRecipeNumber) {
    		try {
    			System.out.print("Which bread would you like? ");
    			recipeNumber = scanner.nextInt();
    			scanner.nextLine();
    			if (recipeNumber < 0 || recipeNumber > totalRecipeNumber) {
    				throw new IllegalArgumentException("Please only type digits.\nValid input are digits from 0 to " + totalRecipeNumber);
    			}
    			validRecipeNumber = true;
    		} catch (InputMismatchException e) {
    			System.err.println("Please only type digits.\nValid input are digits from 0 to " + totalRecipeNumber);
    			scanner.nextLine();
    		} catch (IllegalArgumentException e) {
    			System.err.println(e.getMessage());
    		}
    	}
    	
    	boolean validOrderingQuantity = false;
    	while (!validOrderingQuantity) {
    		try {
    			System.out.print("How much of this bread would you like? ");
    	    	orderingQuantity = scanner.nextInt();
    	    	scanner.nextLine();
    	    	validOrderingQuantity = true;
    		} catch (InputMismatchException e) {
    			System.err.println("Please only type digits.");
    			scanner.nextLine();
    		}
    	}
    	
    	String orderedRecipeName = numberedRecipeListInUse.get(recipeNumber).getRecipeName();
    	currentQuantity = recipeManager.getRecipeOrder().get(recipeNumber - 1).getOrderedQuantity() + orderingQuantity;
    	
    	if (currentQuantity < 0) {
    		System.out.println("The number in total of bread can not be negative.");
    	}
    	else {
    		recipeManager.getRecipeOrder().get(recipeNumber - 1).setOrderedQuantity(currentQuantity);
    	}
    		
    }
    
    /**
     * Generates and prints the current shopping list, then asks if the user wants to save it.
     * 
     * @param recipeManager The RecipeManager instance to generate the shopping list.
     * @param scanner Scanner object for user input to decide whether to save the list.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    private static void generateCurrentShoppingList (RecipeManager recipeManager, Scanner scanner) throws IOException {
    	
    	recipeManager.printShoppingList();
    	recipeManager.generateShoppingListFile(scanner);
    	
    }
    
}
