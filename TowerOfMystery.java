import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * TowerOfMystery Class
 * Represents a location where the player must complete challenges.
 * If the player succeeds, they receive an Axe as a reward.
 */
public class TowerOfMystery {
    private String name;
    private int difficulty;
    private List<Question> questions;
    private List<Enemy> enemies;
    private String reward;

    public TowerOfMystery(int difficulty) {
        this.name = "Tower of Mystery";
        this.difficulty = difficulty;
        this.questions = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.reward = "Axe"; 

        generateQuestions();  
        generateEnemies();    
    }

    private void generateQuestions() {
        questions.add(new Question("What is the capital of France?", "Paris"));
        questions.add(new Question("Solve: 5 + 7 * 2", "19"));
        questions.add(new Question("Which planet is closest to the Sun?", "Mercury"));
    }

    private void generateEnemies() {
        enemies.add(new Enemy("Dark Sorcerer", 40));
        enemies.add(new Enemy("Shadow Beast", 50));
    }

    public void enterTower(Player player) {
        System.out.println("\n🏰 You have entered the " + name + "...");
        System.out.println("To escape, you must answer questions correctly or defeat enemies.");
        
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 2; i++) { 
            if (rand.nextBoolean()) {  
                Question q = questions.get(rand.nextInt(questions.size()));
                System.out.println("\n🧠 Trivia Challenge: " + q.getText());
                System.out.print("Your Answer: ");
                String answer = scanner.nextLine();

                if (!q.checkAnswer(answer)) {
                    System.out.println("❌ Incorrect! You must fight an enemy.");
                    player.attack(enemies.get(rand.nextInt(enemies.size())));
                } else {
                    System.out.println("✅ Correct! You move forward.");
                }
            } else {  
                player.attack(enemies.get(rand.nextInt(enemies.size())));
            }
        }

        System.out.println("🎉 Congratulations! You completed the Tower of Mystery and received an " + reward + "!");
        player.addWeapon(new Axe());
    }
}
