import java.util.Scanner;

//Final part of the text adventure game set in a creepy dungeon
public class CreepyDungeon {
    private boolean hasSword;
    private boolean hasShard;

    //Constructor initializes the player's inventory
    public CreepyDungeon() {
        hasSword = true; //Player starts with the sword from Cursed Forest
        hasShard = false;
    }

    //Method to fight an enemy using trivia
    public boolean fightEnemy(int enemyNumber, Scanner scanner) {
        String question = "";
        String correctAnswer = "";

        //Assigning questions to each enemy
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

        //Asking the player the question but places an enemy before them first
        System.out.println("An evil enemy stands before you! To defeat it, answer this question:");
        System.out.println(question);
        String answer = scanner.nextLine().trim();

        //Checking if the answer is correct
        if (answer.equalsIgnoreCase(correctAnswer)) {
            if (enemyNumber == 5) {
                System.out.println("Correct! You slay the final enemy, but your sword shatters into pieces!");
                hasSword = false;
            } else {
                System.out.println("Correct! You slay the enemy with your sword.");
            }
            return true;
        } else {
            System.out.println("Wrong answer! The enemy overpowers you. Restarting the game...");
            main(new String[]{}); //Restart the game if the player gets the answer wrong
            return false;
        }
    }

    //Method to interact with the shard and free the princess
    public void interactWithShard(Scanner scanner) {
        System.out.println("Without the sword, you find yourself in a predicament");
        System.out.println("OH! But suddenly you spot a massive sharp shard on the ground. Maybe you can use it.");
        System.out.println("Do you want to pick it up? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            hasShard = true;
            System.out.println("You pick up the shard. It feels sharp and powerful.");
            freePrincess(scanner);
        } else {
            System.out.println("You ignore the shard. Without it, you cannot free the princess. Restarting the game...");
            main(new String[]{}); //Restart the game if the player doesn't pick up the shard
        }
    }

    //Method to actually free the princess
    public void freePrincess(Scanner scanner) {
        if (hasShard) {
            System.out.println("You use the shard to slice through the ropes tying the princess.");
            System.out.println("She is free! You have completed the adventure!");
        } else {
            System.out.println("You need something sharp to cut the ropes! Restarting the game...");
            main(new String[]{});
        }
    }

    //Main method to run  game
    public static void main(String[] args) {
        CreepyDungeon dungeon = new CreepyDungeon();
        Scanner scanner = new Scanner(System.in);

        System.out.println("You enter the dark, creepy dungeon. Your sword glows in your hand. The reflection of the princess past the enemies shining back at her.");

        //Fight through five enemies
        for (int enemy = 1; enemy <= 5; enemy++) {
            if (!dungeon.fightEnemy(enemy, scanner)) {
                return;
            }
        }

        //Interact with the shard (Last part)
        dungeon.interactWithShard(scanner);
    }
}
