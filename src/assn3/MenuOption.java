/**
 * File name: Assignment3.java
 * Author: Xin Wang, 041137648
 * Course: CST8132 - OOP
 * Assignment: Assignment 3
 * Date: 1 December, 2024
 * Professor: Sandra Iroakazi
 * Purpose: The MenuOption enum defines the available menu options in the driven class. 
 * 			It provides options for viewing recipes, creating and printing shopping lists
 * 			It also provides other options for reprinting this menu and quitting the program.
 * Class list: MenuOption.java, Recipe.java, RecipeManager.java, RecipeManagerTest.java, Assignment3.java
 */
package assn3;

/**
 * The MenuOption enum represents the available menu options in the driven class.
 * Each option corresponds to a specific action the user can select.
 */
public enum MenuOption {
    
    /**
     * Option to reprint the menu.
     */
    REPRINT_THIS_MENU,

    /**
     * Option to display the list of available recipes.
     */
    SHOW_RECIPES,

    /**
     * Option to create a shopping list based on selected recipes.
     */
    CREATE_SHOPPING_LIST,

    /**
     * Option to print the generated shopping list.
     */
    PRINT_SHOPPING_LIST,

    /**
     * Option to exit the program.
     */
    QUIT_PROGRAM;
}