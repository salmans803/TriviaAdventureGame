import java.util.Scanner;

// A simple text adventure game set in a cursed forest
public class CursedForest {
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
    
    // Main method to run the game
    public static void main(String[] args) {
        CursedForest forest = new CursedForest();
        forest.exploreRoom();
        Scanner scanner = new Scanner(System.in);
        
        // List of possible directions
        String[] directions = {"north", "east", "south", "west"};
        
        for (String direction : directions) {
            System.out.println("Choose a direction to exit: " + direction);
            String input = scanner.nextLine().trim().toLowerCase();
            
            // Ensure valid input direction
            while (!input.equals(direction)) {
                System.out.println("Invalid direction. Try again: " + direction);
                input = scanner.nextLine().trim().toLowerCase();
            }
            
            forest.attemptExit(direction, scanner);
        }
    }
}
