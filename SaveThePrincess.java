// Karen Wu, Salman Syed Ahmed, Asia Scholbe, Lelaye Nwandu, and Daniella Abotsi
// IS 247: PROGRAMMING PROJECT 1
// GROUP PROJECT: ADVENTURE CODE
// GAME NAME: SAVING THE PRINCESS

import java.util.Scanner;

// Main class
class SaveThePrincess {
    private Player player;
    private Scanner scanner;
    private CursedForest cursedForest;
    private TowerOfMystery tower;

    // Constructor initializes game elements
    public SaveThePrincess() {
        scanner = new Scanner(System.in);
        cursedForest = new CursedForest();
        this.tower = new TowerOfMystery();
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

            // Trivia challenge
            if (cursedForest.attemptExit(direction, scanner)) {
                System.out.println("You have successfully exited the forest! Moving to the Tower of Mystery...");
            } else {
                System.out.println("You failed to leave the forest. Please Try Again.");
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

            // If everything is successful and the game ends
            System.out.println("Congratulations! You saved the princess!");
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
    private String name; //Player's name
    private int position; //Player's location name
    private String[] inventory; //fixed size inventory
    private int inventorySize;
    private int health;
    private int wrongAnswers; //Count of incorrect answers

    private static final int MAX_INVENTORY_SIZE = 4;

    //Constructor to initialize knight info
    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.inventory = new String[MAX_INVENTORY_SIZE]; //Initialize inventory array
        this.wrongAnswers = 0; //Starting with 0 wrong answers
        this.health = 100; //Default health points
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

    //Checking if the player answered the question correctly
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

    //Increasing count of wrong answers given by the player
    public void increaseWrongAnswer() {
        wrongAnswers++;
    }

    //Retriving the no. of wrong answers given
    public int getWrongAnswers() {
        return wrongAnswers;
    }

    //Attacking the enemy using the last added item from the inventory
    
    //Reducing the knight's health when taken damage
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + amount + " damage. Remaining health: " + health);
    }

    //Check to see if the knight is defeated (is health below 0)
    public boolean isDefeated() {
        return health <= 0;
    }
}

class Object {
    private String name;
    private String purpose;

    public Object() {}

    public Object(String name, String purpose) {
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
        System.out.println("You have entered " + name + ".");
        System.out.println(description);
        System.out.println("You found: " + item);
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

            if (direction.equals("east")) {
                pickUpSword(scanner);
            }
            if (direction.equals("west")) {
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
                System.out.println("The ogre defeated you! Try again next time.");
                return;
            }
        }

        System.out.println("You defeated the ogre with your sword! You can now continue exploring.");
    }
}

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
        System.out.println("You have entered " + name + ".");
        System.out.println(description);
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
            System.out.println("Without the key, you cannot enter the dungeon to save princess (DUH). Restarting the game...");
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

