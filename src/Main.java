/**
 * This class launches the game using startGame which in turn
 * creates a player, an AI, a board, and a boneyard. The
 * player and AI are both passed the board and boneyard.
 * isGameOver will check for game over conditions
 * and if they are met, gameOver will then display the winner
 * and scores.
 */
public class Main {

    public static void main(String[] args) {
        startGame();
    }

    private static Computer computer;
    private static Player player;
    private static boolean gameIsOver;
    private static boolean lastPlayerComp;

    private static void startGame() {
        Boneyard boneYard = new Boneyard();
        Board playArea = new Board();
        computer = new Computer(boneYard, playArea);
        player = new Player(boneYard, playArea);
        gameIsOver = false;
        lastPlayerComp = false;

        System.out.println("Dominoes!");
        System.out.println(computer);

        while (true) {
            player.takeTurn();
            if (isGameOver(player, computer)) {
                break;
            }

            computer.takeTurn();
            if (isGameOver(player, computer)) {
                break;
            }
        }
        gameOver(player, computer);
    }
    private static boolean isGameOver(Player player, Computer computer) {
        return player.getTrayLength() == 0 || computer.getTrayLength() == 0 || (!player.getCanPlay() && !computer.getCanPlay());
    }
    private static void gameOver(Player player, Computer computer) {
        int playerScore = player.getScore();
        int compScore = computer.getScore();
        System.out.println("\n\nGameOver!!!");
        System.out.println("Player score: " + playerScore);
        System.out.println("Computer score: " + compScore);

        if (playerScore > compScore) {
            System.out.println("Player Wins!");
        } else if (compScore > playerScore) {
            System.out.println("Computer Wins!");
        } else {
            System.out.println("Scores are equal! The last player wins:");
            if (player instanceof Player) {
                System.out.println("Computer Wins!");
            } else {
                System.out.println("Player Wins!");
            }
        }
    }
}

