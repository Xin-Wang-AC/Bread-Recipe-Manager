/**
 * File name: RecipeManager.java
 * Author: Xin Wang
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: This class manages recipes, including loading recipes from a file, 
 *          printing shopping lists, and generating shopping list files.
 */
package assn3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * The RecipeManager class manages a collection of recipes, providing functionality 
 * to load recipes from a file, print shopping lists based on ordered recipes, 
 * and generate shopping list files for saving.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.io.BufferedWriter
 * @see java.io.File
 * @see java.io.FileNotFoundException
 * @see java.io.FileWriter
 * @see java.io.IOException
 * @see java.util.ArrayList
 * @see java.util.HashMap
 * @see java.util.Iterator
 * @see java.util.Map.Entry
 * @see java.util.Scanner
 * @since Eclipse IDE 4.31.0
 */
public class RecipeManager {
    
    /** The list of recipes ordered by the user. */
    private ArrayList<Recipe> recipeOrder;
    
    /** The menu of available recipes for display. */
    private ArrayList<String> menu;
    
    /** The content of the current shopping list. */
    private ArrayList<String> shoppingListContent;
    
    /**
     * Constructs a new RecipeManager with empty lists for recipes, menu, and shopping list content.
     */
    public RecipeManager() {
        recipeOrder = new ArrayList<>();
        menu = new ArrayList<>();
        shoppingListContent = new ArrayList<>();
    }
    
    /**
     * Loads recipes from a specified file path into a numbered list.
     * 
     * @param recipeListPath The path to the file containing recipe data.
     * @return A HashMap where keys are recipe numbers and values are Recipe objects.
     * @throws FileNotFoundException if the specified file is not found.
     */
    public HashMap<Integer, Recipe> loadRecipeList(String recipeListPath) throws FileNotFoundException {
        File recipeList = new File(recipeListPath);
        Recipe recipeInReading = null;
        String menuName;
        String ingredient;
        double quantity;
        HashMap<Integer, Recipe> numberedRecipeList = new HashMap<>();
        
        try (Scanner scanner = new Scanner(recipeList)) {
            int recipeNumberInReading = 1;
            
            while (scanner.hasNextLine()) {
                String scannedLine = scanner.nextLine();
                
                if (scannedLine.startsWith("Recipe")) {
                    recipeInReading = new Recipe();
                    String recipeName = scannedLine.substring(7);
                    recipeInReading.setRecipeName(recipeName);
                    recipeOrder.add(recipeInReading);
                    menuName = recipeNumberInReading + ". " + recipeName;
                    menu.add(menuName);
                }
                else if (!scannedLine.isEmpty()){
                    String[] ingredientDetails = scannedLine.split(" ");
                    ingredient = ingredientDetails[0];
                    quantity = Double.parseDouble(ingredientDetails[1]);
                    recipeInReading.addIngredient(ingredient, quantity);
                }
                else {
                    numberedRecipeList.put(recipeNumberInReading, recipeInReading);
                    recipeNumberInReading++;
                    recipeInReading = null;
                }
            }
            
            if (recipeInReading != null) {
                numberedRecipeList.put(recipeNumberInReading, recipeInReading);
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        
        return numberedRecipeList;
    }
    
    /**
     * Prints the current shopping list based on the ordered recipes.
     * This method clears the existing shopping list content, calculates the total ingredients needed,
     * and prints the list to the console.
     */
    public void printShoppingList() {
        shoppingListContent.clear();
        
        try {
            Iterator<Recipe> recipeOrderQuantityIterator = recipeOrder.iterator();
            
            while (recipeOrderQuantityIterator.hasNext()) {
                Recipe recipeQuantityInWriting = recipeOrderQuantityIterator.next();
                if (recipeQuantityInWriting.getOrderedQuantity() != 0) {
                    shoppingListContent.add(recipeQuantityInWriting.getOrderedQuantity() + " " + recipeQuantityInWriting.getRecipeName() + " loaf/loaves.");
                }
            }
            
            shoppingListContent.add("");
            shoppingListContent.add("You will need a total of: ");
            
            Iterator<Recipe> recipeOrderIngredientsIterator = recipeOrder.iterator();
            HashMap<String, Double> totalIngredients = new HashMap<>();
            
            while (recipeOrderIngredientsIterator.hasNext()) {
                Recipe recipeIngredientsInWriting = recipeOrderIngredientsIterator.next();
                int recipeOrderedQuantity = recipeIngredientsInWriting.getOrderedQuantity();
                if (recipeOrderedQuantity != 0) {
                    Iterator<HashMap.Entry<String, Double>> recipeIngredientsIterator = recipeIngredientsInWriting.getIngredient().entrySet().iterator();
                    while (recipeIngredientsIterator.hasNext()) {
                        HashMap.Entry<String, Double> IngredientsInWriting = recipeIngredientsIterator.next();
                        String ingredientInWriting = IngredientsInWriting.getKey();
                        double quantityInWriting = IngredientsInWriting.getValue();
                        double totalQuantityInWriting = quantityInWriting * recipeOrderedQuantity;
                        Iterator<HashMap.Entry<String, Double>> totalIngredientsIterator = totalIngredients.entrySet().iterator();
                        boolean ingredientFound = false;
                        String ingredientKey = null;
                        while (totalIngredientsIterator.hasNext()) {
                            HashMap.Entry<String, Double> totalIngredientsInSearching = totalIngredientsIterator.next();
                            if (ingredientInWriting.equals(totalIngredientsInSearching.getKey())) {
                                ingredientFound = true;
                                ingredientKey = totalIngredientsInSearching.getKey();
                            }
                        }
                        if (ingredientFound) {
                            double updatedTotalQuantityInWriting = totalQuantityInWriting + totalIngredients.get(ingredientKey);
                            totalIngredients.put(ingredientKey, updatedTotalQuantityInWriting);
                        } else {
                            totalIngredients.put(ingredientInWriting, totalQuantityInWriting);
                        }
                    }
                }
            }
            
            Iterator<HashMap.Entry<String, Double>> totalIngredientsIterator = totalIngredients.entrySet().iterator();
            while (totalIngredientsIterator.hasNext()) {
                HashMap.Entry<String, Double> totalIngredientsInWriting = totalIngredientsIterator.next();
                String totalIngredientsName = totalIngredientsInWriting.getKey();
                double totalIngredientsQuantity = totalIngredientsInWriting.getValue();
                if (totalIngredientsQuantity != 0.0) {
                    if (!totalIngredientsName.equals("eggs")) {
                        shoppingListContent.add(Double.toString(totalIngredientsQuantity) + " grams of " + totalIngredientsName);
                    } else {
                        shoppingListContent.add(Double.toString(totalIngredientsQuantity) + " egg(s)");
                    }
                }
            }

        } catch (SecurityException e) {
            System.err.println("Error writing the file: " + e.getMessage());
        }
        
        Iterator<String> shoppingListContentIterator = shoppingListContent.iterator();
        while (shoppingListContentIterator.hasNext()) {
            String contentLine = shoppingListContentIterator.next();
            System.out.println(contentLine);
        }
    }
    
    /**
     * Generates a shopping list file if the user decides to save it.
     * 
     * @param scanner Scanner object for user input to decide whether to save the list.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void generateShoppingListFile(Scanner scanner) throws IOException {
        System.out.print("Do you want to save this list (Y/n)? ");
        String userDecision = scanner.nextLine();
        
        if (userDecision.equals("Y")) {
            File shoppingList = new File("shoppinglist.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(shoppingList))) {
                shoppingList.createNewFile();
                int writingLineNumber = 0;
                Iterator<String> shoppingListContentIterator = shoppingListContent.iterator();
                while (shoppingListContentIterator.hasNext()) {
                    String contentLineInWriting = shoppingListContentIterator.next();
                    writer.write(contentLineInWriting);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing the file: " + e.getMessage());
            }
        }
    }
    
    /**
     * Retrieves the list of ordered recipes.
     * 
     * @return An ArrayList containing all ordered recipes.
     */
    public ArrayList<Recipe> getRecipeOrder(){
        return recipeOrder;
    }
    
    /**
     * Retrieves the menu of available recipes.
     * 
     * @return An ArrayList containing the names of available recipes.
     */
    public ArrayList<String> getMenu() {
        return menu;
    }
}
