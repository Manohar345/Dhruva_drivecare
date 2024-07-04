import java.util.Random;

class Player {
    private String name;
    private int health;
    private int strength;
    private int attack;

    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health -= amount;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class MagicalArena {

    private static final Random random = new Random();

    public static void main(String[] args) {
        Player playerA = new Player("Player A", 50, 5, 10);
        Player playerB = new Player("Player B", 100, 10, 5);

        System.out.println("The battle begins!");
        battle(playerA, playerB);
    }

    private static void battle(Player playerA, Player playerB) {
        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() < playerB.getHealth()) {
                takeTurn(playerA, playerB);
                if (playerB.isAlive()) {
                    takeTurn(playerB, playerA);
                }
            } else {
                takeTurn(playerB, playerA);
                if (playerA.isAlive()) {
                    takeTurn(playerA, playerB);
                }
            }
        }

        if (playerA.isAlive()) {
            System.out.println(playerA.getName() + " wins!");
        } else {
            System.out.println(playerB.getName() + " wins!");
        }
    }

    private static void takeTurn(Player attacker, Player defender) {
        System.out.println("\n" + attacker.getName() + " attacks " + defender.getName());

        int attackRoll = rollDie();
        int defenseRoll = rollDie();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defenseValue = defender.getStrength() * defenseRoll;

        int damageDone = Math.max(attackDamage - defenseValue, 0);
        defender.reduceHealth(damageDone);

        System.out.println(attacker.getName() + " rolled a " + attackRoll + " (Attack Damage: " + attackDamage + ")");
        System.out.println(defender.getName() + " rolled a " + defenseRoll + " (Defense Value: " + defenseValue + ")");
        System.out.println(defender.getName() + " takes " + damageDone + " damage and is now at " + defender.getHealth() + " health");
    }

    private static int rollDie() {
        return random.nextInt(6) + 1;
    }
}
