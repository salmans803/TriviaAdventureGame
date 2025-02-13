import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of SaveThePrincess and start the game
        SaveThePrincess game = new SaveThePrincess();
        game.startQuest();
    }
}

class SaveThePrincess {
    public void startQuest() {
        Scanner scanner = new Scanner(System.in);

        // Greet the knight and ask for the knight's name
        System.out.println("GOOD MORROW, BRAVE KNIGHT! What is thy name? [Please enter your name here]: ");
        String knightName = scanner.nextLine();
        
        System.out.println(" "); //Spacing
        System.out.println("Welcome to SAVING THE PRINCESS, Knight " + knightName + "! Your quest is to save the princess from the dragon!");


        // Background Story Information 
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Long ago, the evil warlord named Aamon, whose true form is a dragon, kidnapped Princess Cornelia and locked her away in his fortress. It has since been 3 years since the disappearance of the princess.");
        System.out.println("The kingdom has fallen into despair as many knights have tried yet failed to save her, awaiting a hero brave enough to embark on this perilous journey to rescue her.");         
        System.out.println("As the chosen knight, you will face dangerous trials and treacherous paths. Should you succeed, the king will grant you great riches and the honor of marrying his beautiful daughter!");
        System.out.println("But should you fail, darkness will consume you, and the princess's fate will be sealed forever.");
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" "); //Spacing
        
        // Ask if the knight wants to proceed with the game
        System.out.println(" "); //Spacing
        System.out.println("Do you wish to continue? [Please enter yes or no]: ");
        String gameProceed = scanner.nextLine().toLowerCase();

        if (!gameProceed.equals("yes")) {
            System.out.println(" "); //Spacing
            System.out.println("Farewell, " + knightName + ". Maybe another time!");
            return;
        }
        
        System.out.println("Great! Let us begin our magical journey to save the princess!");
        System.out.println(" "); //Spacing

        
        //Directions to the game
        System.out.println("Lets go over the rules shall we?");
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("In order to save princess Cornelia, you need to answer a series of random trivia questions! You will be given a question with 3 potential answers, if you answer the question correctly, the dragon will take some damage.");
        System.out.println("If answerered wrongly, you the knight will take some damage. Taking too much damage can lead to your death, so be very careful when answering these questions!");
        System.out.println("As a brave knight, you'll need your sword of course! You'll also be given 2 magical apples which will heal you if you've taken to much damage!");
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
          // Ask the knight if their ready for the quest
        System.out.println(" "); //Spacing
        System.out.println("Are you ready to begin your magical journey? [Please enter yes or no]: ");
        String questProceed = scanner.nextLine().toLowerCase();

        if (!questProceed.equals("yes")) {
            System.out.println(" "); //Spacing
            System.out.println("Farewell, " + knightName + ". Maybe another time!");
            return;
        }
        
        System.out.println("Great! Let us begin our magical journey to save the princess!");
        System.out.println(" "); //Spacing


    }
}


    
