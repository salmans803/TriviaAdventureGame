// Daniella-Naomi K. Aklamanu-Abotsi
// IS 247: ADVENTURE CODE (DRAFT)
// GROUP PROJECT: ADVENTURE CODE
// GAME NAME: SAVING THE PRINCESS

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SaveThePrincess game = new SaveThePrincess();
        game.startQuest();
    }
}

class SaveThePrincess {
    private Player player;

    public void startQuest() {
        Scanner scanner = new Scanner(System.in);

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
            scanner.close();
            return;
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
            scanner.close();
            return;
        }

        System.out.println("Great! Let us begin our journey!");

        // Entering the Cursed Forest
        CursedForest cursedForest = new CursedForest();
        cursedForest.exploreRoom();

        // Player chooses a direction
        System.out.print("Which direction would you like to go? (north/south/east/west): ");
        String direction = scanner.nextLine().trim().toLowerCase();

        // Trivia challenge
        if (cursedForest.attemptExit(direction, scanner, player)) {
            System.out.println("You have successfully exited the forest! Moving to the Tower of Mystery...");
            TowerOfMystery tower = new TowerOfMystery(2);
            tower.enterTower(player);
        } else {
            System.out.println("You failed to leave the forest. Please Try Again.");
        }

        scanner.close();
    }
}


    