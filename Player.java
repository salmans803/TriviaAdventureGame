public class Player {
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
        return health<=0;
    }
}
