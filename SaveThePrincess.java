// Karen Wu, Salman Syed Ahmed, Asia Scholbe, Lelaye Nwandu, and Daniella Abotsi
// IS 247: PROGRAMMING PROJECT 1
// GROUP PROJECT: ADVENTURE CODE
// GAME NAME: SAVING THE PRINCESS

import java.util.Scanner;

// Main class
public class SaveThePrincess {
    private Player player;
    private Scanner scanner;
    private CursedForest cursedForest;
    private TowerOfMystery tower;
    private CreepyDungeon dungeon; 

    // Constructor initializes game elements
    public SaveThePrincess() {
        scanner = new Scanner(System.in);
        cursedForest = new CursedForest();
        tower = new TowerOfMystery();
        dungeon = new CreepyDungeon();  // Now a top-level class
    }

    // Method to start the game
    public void startQuest() {
        while (true) {  // Main game loop
            System.out.print("GOOD MORROW, BRAVE KNIGHT! What is thy name? [Please enter your name here]: ");
            String name = scanner.nextLine();
            player = new Player(name);

            System.out.println("\nWelcome to SAVING THE PRINCESS, Knight " + name + "! Your quest is to save the princess from the dragon!");

            // Background Story
            System.out.println("----------------------------------------------------------------");
            System.out.println("Long ago, the evil Sorcerer named Aamon, whose true form is a dragon, kidnapped Princess Cornelia!");
            System.out.println("Should you succeed, the king will grant you great riches and the honor of marrying his daughter!");
            System.out.println("But should you fail, darkness will consume you.");
            System.out.println("----------------------------------------------------------------");

            System.out.print("Do you wish to continue? [yes/no]: ");
            String gameProceed = scanner.nextLine().trim().toLowerCase();

            if (!gameProceed.equals("yes")) {
                System.out.println("Farewell, " + name + ". Maybe another time!");
                break; // Exit the game loop
            }

            System.out.println("Great! Let us begin our magical journey to save the princess!\n");

            // Game rules
            System.out.println("----------------------------------------------------------------");
            System.out.println("To save Princess Cornelia, you must answer trivia questions.");
            System.out.println("Correct answers damage the dragon; wrong answers hurt you.");
            System.out.println("You will obtain items throughout your journey.");
            System.out.println("----------------------------------------------------------------");

            System.out.print("Are you ready to begin? [yes/no]: ");
            String questProceed = scanner.nextLine().trim().toLowerCase();

            if (!questProceed.equals("yes")) {
                System.out.println("Farewell, " + player.getName() + ". Maybe another time!");
                break; // Exit the game loop
            }

            System.out.println("Great! Let us begin our journey!");

            // Entering the Cursed Forest
            cursedForest.exploreRoom();

            // Player chooses a direction
            System.out.print("Which direction would you like to go? (north/south/east/west): ");
            String direction = scanner.nextLine().trim().toLowerCase();

            // Trivia challenge in the forest
            if (cursedForest.attemptExit(direction, scanner)) {
                System.out.println("You have successfully exited the forest! Moving to the Tower of Mystery...");
            } else {
                System.out.println("You failed to leave the forest. Please Try Again.");
                System.out.println("Restarting the game...");
                continue; // Restart the current iteration of the main loop
            }

            // Enter the Tower of Mystery
            tower.exploreTower();
            boolean gameOver = false;

            // Loop for floors (Descending through floors)
            for (int floor = 4; floor > 0; floor--) {
                // If the player gets a wrong answer, restart the game
                if (!tower.attemptDescend(floor, scanner)) {
                    System.out.println("Restarting the game...\n");
                    gameOver = true; // Set gameOver flag to true
                    break; // Break the current floor loop to restart the game
                }
            }

            if (gameOver) {
                continue; // Restart the main game loop if gameOver is true
            }

            tower.askToPickUpKey(scanner);

            // If the player tries to enter the dungeon without the key, restart the game
            if (!tower.enterDungeon(scanner)) {
                System.out.println("Restarting the game...\n");
                continue; // Restart the main game loop
            }
            
            // Enter the Creepy Dungeon
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You enter the dark, creepy dungeon. Your sword glows in your hand. The reflection of the princess past the enemies shining back at her.");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            // Fight through five enemies
            boolean fightSuccess = true;
            for (int enemy = 1; enemy <= 5; enemy++) {
                if (!dungeon.fightEnemy(enemy, scanner)) {
                    System.out.println("Restarting the game...\n");
                    fightSuccess = false;
                    break; // Break out of the enemy loop to restart the game
                }
            }
            if (!fightSuccess) {
                continue;
            }

            // Interact with the axe (Last part)
            boolean axePicked = dungeon.interactWithAxe(scanner);
            if (!axePicked) {
                continue;  // Restart the game loop if axe was not picked up
            }
            
            // If everything is successful and the game ends
            System.out.println("Thanks for playing SAVING THE PRINCESS");
            break; // Exit the main game loop (end the game)
        }

        scanner.close(); // Close the scanner when the game ends
    }

    public static void main(String[] args) {
        SaveThePrincess game = new SaveThePrincess();
        game.startQuest(); // Call startQuest to begin the game
    }
}

class Player {
    private String name; // Player's name
    private int position; // Player's location
    private String[] inventory; // fixed size inventory
    private int inventorySize;
    private int health;
    private int wrongAnswers; // Count of incorrect answers
    private boolean hasShield;  // tracks if the player has the mystical shield

    private static final int MAX_INVENTORY_SIZE = 4;

    //Constructor to initialize knight info
    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.inventory = new String[MAX_INVENTORY_SIZE]; //Initialize inventory array
        this.wrongAnswers = 0; //Starting with 0 wrong answers
        this.health = 100; //Default health points
        this.hasShield = false;
    }

    // Retrieve the knight's name
    public String getName() {
        return name;
    }

    // Retrieve the knight's current health
    public int getHealth() {
        return health;
    }

    // Retrieve the knight's current position
    public int getPosition() {
        return position;
    }

    public void moveForward() {
        position++;
    }

    public void moveBackward() {
        if (position > 0)
            position--;
    }
    
    public boolean hasShield() {
        return hasShield;
    }

    public void setShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    // A method to remove the shield once used.
    public void useShield() {
        if(hasShield) {
            System.out.println("Your mystical shield absorbs the blow!");
            hasShield = false;
        
    }
}

    // Checking if the player answered the question correctly
    public boolean answerQuestion(String userAnswer, Question question) {
        return question.checkAnswer(userAnswer);
    }

    public void addWeapon(String weapon) {
        if (inventorySize < MAX_INVENTORY_SIZE) {
            inventory[inventorySize] = weapon;
            inventorySize++;
        } else {
            System.out.println("Inventory is full!");
        }
    }

    // Increasing count of wrong answers given by the player
    public void increaseWrongAnswer() {
        wrongAnswers++;
    }

    // Retrieving the number of wrong answers given
    public int getWrongAnswers() {
        return wrongAnswers;
    }

    // Reducing the knight's health when taking damage
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + amount + " damage. Remaining health: " + health);
    }

    // Check to see if the knight is defeated (health at or below 0)
    public boolean isDefeated() {
        return health <= 0;
    }
}

// A simple class representing an object (avoid using the name "Object" as it conflicts with java.lang.Object)
class GameObject {
    private String name;
    private String purpose;

    public GameObject() {}

    public GameObject(String name, String purpose) {
        this.name = name;
        this.purpose = purpose;
    }

    public String getObjectName() {
        return name;
    }

    public String getObjectPurpose() {
        return purpose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Object name = " + name + " , purpose = " + purpose;
    }
}

// Cursed Forest class
class CursedForest {
    private String name;
    private String description;
    private String item;
    private boolean hasSword;

    // Constructor initializes the forest details
    public CursedForest() {
        name = "The Cursed Forest";
        description = "Tall, twisted trees surround you, their branches reaching like claws.";
        item = "An ancient amulet";
        hasSword = false;
    }

    // Method to explore the room
    public void exploreRoom() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You have entered " + name + ".");
        System.out.println(description);
        System.out.println("You found: " + item);
        System.out.println("----------------------------------------------------------------");

    }

    // Method to attempt exiting the room by answering questions
    public boolean attemptExit(String direction, Scanner scanner) {
        String question = "";
        String correctAnswer = "";
        String foundItem = "";

        // Assigning questions based on direction
        if (direction.equals("north")) {
            question = "What is 3 + 4?";
            correctAnswer = "7";
            foundItem = "a mystical shield";
        } else if (direction.equals("south")) {
            question = "What is the capital of France?";
            correctAnswer = "Paris";
            foundItem = "a magic scroll";
        } else if (direction.equals("east")) {
            question = "What is 5 * 6?";
            correctAnswer = "30";
            foundItem = "a sword";
        } else if (direction.equals("west")) {
            question = "What color is the sky on a clear day?";
            correctAnswer = "Blue";
            foundItem = "a golden key";
        } else {
            System.out.println("You cannot go that way.");
            return false;
        }

        // Asking the player the question
        System.out.println("To move " + direction + ", answer this question: ");
        System.out.println(question);
        String answer = scanner.nextLine().trim();

        // Checking if the answer is correct
        if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct! You move " + direction + ".");
            System.out.println("You found " + foundItem + "!");
            
            if (direction.equals("north")) {
                // Instead of awarding the shield, inform the player that it's not acceptable.
                System.out.println("But alas, a shield alone won't help you defeat your enemies.");
                System.out.println("You must find a sword to continue. Try a different direction.");
                return false;
                }
            
            if (direction.equals("east")) {
                pickUpSword(scanner);
            }
            if (direction.equals("west")) {
                pickUpSword(scanner);
                fightOgre(scanner);
            }
            return true;
        } else {
            System.out.println("Wrong answer! You cannot pass.");
            return false;
        }
    }

    // Method to allow player to pick up the sword
    public void pickUpSword(Scanner scanner) {
        System.out.println("You see a sword lying on the ground. Do you want to pick it up? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            hasSword = true;
            System.out.println("You picked up the sword! You feel stronger.");
        } else {
            System.out.println("You leave the sword where it is.");
        }
    }

    // Method to fight an ogre if the player has a sword
    public void fightOgre(Scanner scanner) {
        if (!hasSword) {
            System.out.println("You need a sword to fight the ogre!");
            return;
        }

        System.out.println("An ogre appears! Answer these questions to defeat it.");

        // Array of questions for the ogre fight
        String[][] questions = {
            {"What is 8 + 2?", "10"},
            {"What is the color of grass?", "Green"},
            {"How many legs does a spider have?", "8"}
        };

        // Asking the player the ogre's questions
        for (String[] q : questions) {
            System.out.println(q[0]);
            String answer = scanner.nextLine().trim();
            if (!answer.equalsIgnoreCase(q[1])) {
                System.out.println("The ogre overpowers you! You are defeated.");
                System.out.println("Restarting the game...");
                return;
            }
        }

        System.out.println("You defeated the ogre with your sword! You can now continue exploring.");
    }
}

// Tower of Mystery class
class TowerOfMystery {
    private String name;
    private String description;
    private boolean hasKey;

    // Constructor to tell the user about the tower
    public TowerOfMystery() {
        name = "The Tower of Mystery";
        description = "A spiraling tower filled with eerie whispers and ancient secrets. Your goal is to get all the questions right and find the key to get to the creepy dungeon and save the princess.";
        hasKey = false;
    }

    // Method to explore the tower by going level by level down to the creepy dungeon
    public void exploreTower() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You have entered " + name + ".");
        System.out.println(description);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    public boolean attemptDescend(int floor, Scanner scanner) {
        String question = "";
        String correctAnswer = "";

        // Assigning questions based on floor
        switch (floor) {
            case 4:
                question = "What is the square root of 64?";
                correctAnswer = "8";
                break;
            case 3:
                question = "Who wrote 'Romeo and Juliet'?";
                correctAnswer = "Shakespeare";
                break;
            case 2:
                question = "What is the chemical symbol for gold? HINT: 1ST & 21ST LETTER OF ALPHABET";
                correctAnswer = "Au";
                break;
            case 1:
                question = "Which planet is known as the Red Planet? HINT: STARTS WITH AN M";
                correctAnswer = "Mars";
                break;
            default:
                System.out.println("You cannot go that way.");
                return false; // No further question, exit
        }

        // Asking the player the question
        System.out.println("To descend to floor " + (floor - 1) + ", answer this question: ");
        System.out.println(question);
        String answer = scanner.nextLine().trim();

        // Check the answer
        if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct! You may descend to the next floor.");
            return true;
        } else {
            System.out.println("Wrong answer! You cannot descend further.");
            return false;  // Game over if the answer is incorrect
        }
    }

    // Method to allow player to pick up the key
    public void askToPickUpKey(Scanner scanner) {
        System.out.println("You found a mysterious key! Do you want to pick it up? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            hasKey = true;
            System.out.println("You feel hope blooming in your chest, the princess is near!");
        } else {
            System.out.println("You leave the key where it is.");
            System.out.println("Without the key, you cannot enter the dungeon to save the princess (DUH).");
        }
    }

    public boolean enterDungeon(Scanner scanner) {
        System.out.println("You stand before the dungeon door. Do you want to enter? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            if (hasKey) {
                System.out.println("You use the key to unlock the dungeon door and step into the darkness...");
                System.out.println("A new adventure begins!");
                return true;
            } else {
                System.out.println("The dungeon is locked. You need a key to enter.");
                return false;
            }
        } else {
            System.out.println("You decide to stay outside for now.");
            return false;
        }
    }
}

//Final part of the text adventure game set in a creepy dungeon
class CreepyDungeon {
    private boolean hasSword;
    private boolean hasAxe;

    // Constructor initializes the player's inventory
    public CreepyDungeon() {
        hasSword = true; // Player starts with the sword from the Cursed Forest
        hasAxe = false;
    }

    // Method to fight an enemy using trivia
    public boolean fightEnemy(int enemyNumber, Scanner scanner) {
        String question = "";
        String correctAnswer = "";
        int enemyHP = 10;
        int damage = 10;

        // Assigning questions to each enemy
        switch (enemyNumber) {
            case 1:
                question = "What is the capital of the Germany?";
                correctAnswer = "Berlin";
                break;
            case 2:
                question = "How many legs does an ant have?";
                correctAnswer = "6";
                break;
            case 3:
                question = "What is the largest planet in our solar system?";
                correctAnswer = "Jupiter";
                break;
            case 4:
                question = "Who painted the Mona Lisa?";
                correctAnswer = "Da Vinci";
                break;
            case 5:
                question = "What is the boiling point of water in Celsius? HINT: 50 + 50";
                correctAnswer = "100";
                break;
            default:
                System.out.println("No enemy to fight.");
                return false;
        }

        while (enemyHP > 0) {
            System.out.println("Evil Enemies with 10HP each stand before you! Answer the following question to damage it:");
            System.out.println(question);
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(correctAnswer)) {
                enemyHP -= damage;
                System.out.println("Correct! The enemy loses " + damage + " HP. Remaining enemy HP: " + Math.max(enemyHP, 0));
            } else {
                System.out.println("Wrong answer! The enemy overpowers you. Restarting the game...");
                return false;
            }
        }
        
        if (enemyNumber == 5) {
            System.out.println("You've defeated the final enemy, but your sword shatters into pieces!");
            hasSword = false;
        } else {
            System.out.println("1 Enemy defeated!");
        }
        return true;
    }
    // Method to interact with the axe and free the princess
    public boolean interactWithAxe(Scanner scanner) {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Without the sword, you find yourself in a predicament");
        System.out.println("OH! But suddenly you spot a massive sharp axe on the ground. Maybe you can use it.");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Do you want to pick it up? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            hasAxe = true;
            System.out.println("You pick up the axe. It feels sharp and powerful.");
            freePrincess(scanner);
            return true;
        } else {
            System.out.println("You ignore the axe. Without it, you cannot free the princess.");
            System.out.println("Restarting the game...\n");
            return false;
        }
    }

    // Method to actually free the princess
    public void freePrincess(Scanner scanner) {
        if (hasAxe) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("You use the axe to slay Aamon the dragon!");
            System.out.println("You also use the axe to slice through the ropes tying the princess.");
            System.out.println("She is free! You have completed the adventure!");
            System.out.println("--------------------------------------------------------------------");

        } else {
            System.out.println("You need something sharp to cut the ropes! Restarting the game...\n");
        }
    }
}
   