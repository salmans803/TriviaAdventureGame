import java.util.Scanner; // Importing Scanner for user input

// CreepyDungeon Class
public class CreepyDungeon {
    private String name = "The Creepy Dungeon";
    private String description = "A dark and eerie place filled with shadows and strange noises.";
    private boolean princessRescued = false;
    private Enemy finalBoss;

    public CreepyDungeon() {
        finalBoss = new Enemy("Dark Overlord", 100);
    }

    public void enterDungeon(Player player, Scanner scanner) {
        System.out.println("\n🏰 You have entered " + name + ".");
        System.out.println(description);
        System.out.println("Deep inside, you find the imprisoned princess!");
        System.out.println("But before you can free her, the " + finalBoss.getName() + " appears!");

        while (!finalBoss.isDefeated() && !player.isDefeated()) {
            System.out.println("Do you want to attack or use a heavy strike? [attack/heavy]: ");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("heavy")) {
                System.out.println("🔥 You unleash a HEAVY STRIKE!");
                finalBoss.takeDamage(50);
            } else {
                System.out.println("⚔️ You attack the " + finalBoss.getName() + "!");
                finalBoss.takeDamage(30);
            }

            if (!finalBoss.isDefeated()) {
                System.out.println("The " + finalBoss.getName() + " attacks back!");
                player.takeDamage(20);
            }
        }

        if (player.isDefeated()) {
            System.out.println("💀 You were defeated... The kingdom is doomed!");
        } else {
            System.out.println("🎉 You have defeated the " + finalBoss.getName() + " and rescued the princess!");
            princessRescued = true;
        }
    }
}
