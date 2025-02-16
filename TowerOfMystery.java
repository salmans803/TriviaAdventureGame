import java.util.Scanner;

// A simple text adventure game set in the Tower of Mystery
public class TowerOfMystery {
    private String name;
    private String description;
    private boolean hasKey;
    
    //Constructor to tell the user about the tower
    public TowerOfMystery() {
        name = "The Tower of Mystery";
        description = "A spiraling tower filled with eerie whispers and ancient secrets. Your goal is to get all the questions right and find the key to get to the creepy dungeon and save the princess.";
        hasKey = false;
    }
    
    //Method to explore the tower by going level by level down to the creepy dungeon
    public void exploreTower() {
        System.out.println("You have entered " + name + ".");
        System.out.println(description);
    }
    
    
    public boolean attemptDescend(int floor, Scanner scanner) {
        String question = "";
        String correctAnswer = "";
        
        //Assigning basic questions based on floor
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
                question = "What is the chemical symbol for gold? HINT : 1ST & 21ST LETTER OF ALPHABET";
                correctAnswer = "Au";
                break;
            case 1:
                question = "Which planet is known as the Red Planet? HINT: STARTS WITH AN M";
                correctAnswer = "Mars";
                break;
            default:
                System.out.println("You cannot go that way.");
                return false;
        }
        
        //Asking the player the question
        System.out.println("To descend to floor " + (floor - 1) + ", answer this question: ");
        System.out.println(question);
        String answer = scanner.nextLine().trim();
        
        //Checking if the answer is correct
        if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct! You descend to floor " + (floor - 1) + ".");
            if (floor == 1) {
                askToPickUpKey(scanner);
            }
            return true;
        } else {
            System.out.println("Wrong answer! Restarting the game...");
            main(new String[]{}); // Restart the game if the player gets the answer wrong
            return false;
        }
    }
    
    // Method to allow player to pick up the key
    public void askToPickUpKey(Scanner scanner) {
        System.out.println("You found a mysterious key! Do you want to pick it up? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes")) {
            hasKey = true;
            System.out.println("You picked up the key! You feel hope blooming in your chest, the princess is near!");
        } else {
            System.out.println("You leave the key where it is.");
            System.out.println("Without the key, you cannot enter the dungeon to save princess (DUH). Restarting the game...");
            main(new String[]{}); // Restart the game if the player doesn't pick up the key
        }
    }
    
    // Method to enter the dungeon
    public void enterDungeon(Scanner scanner) {
        System.out.println("You stand before the dungeon door. Do you want to enter? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes")) {
            if (hasKey) {
                System.out.println("You use the key to unlock the dungeon door and step into the darkness...");
                System.out.println("A new adventure begins!");
            } else {
                System.out.println("The dungeon is locked. You need a key to enter.");
                System.out.println("Restarting the game...");
                main(new String[]{}); //Restart the game if the player tries to enter without the key
            }
        } else {
            System.out.println("You decide to stay outside for now. Restarting the game...");
            main(new String[]{}); //Restart the game if the player chooses not to enter
        }
    }
    
    //Main method to run the game
    public static void main(String[] args) {
        TowerOfMystery tower = new TowerOfMystery();
        tower.exploreTower();
        Scanner scanner = new Scanner(System.in);
        
        //Descend through the floors
        for (int floor = 4; floor > 0; floor--) {
            if (!tower.attemptDescend(floor, scanner)) {
                return;
            }
        }
        
        //Attempt to enter the dungeon
        tower.enterDungeon(scanner);
    }
}
