import java.util.ArrayList;
import java.util.Scanner;

public class GameDriver {
    private int numOfPlayers;

    // the first element of playersList is for Dealer, so adding players from index 1
    private ArrayList<Player> playersList;

    // index to control the turn of players and initialize turnIndex to 1
    private int turnIndex;

    private Deck deck;

    // create an object for dealer with empty name and phone number
    private Player dealer = new Player("", "", true);

    // a boolean to keep track whether the game is finished or ongoing
    private boolean isGameFinished;


    // Control the whole game
    public void initGame(ArrayList<Player> players, int deckCount) {
        // turn index starts with 0
        turnIndex = 0;
        playersList = players;

        initDeck();
        shuffleDeck();
        // add dealer object to the end of the players list
        playersList.add(dealer);

        dealStartingCards();
        checkPlayersBlackjack();
        System.out.println("Dealer has Blackjack: " + dealer.isPlayerBlackjack());
        System.out.println();
        //for (Player player:playersList){
        //  System.out.println(player.getName()+"  "+player.getStatus());
        //}
        showResult();

    }

    // return current turn index
    private int getTurnIndex() {
        return turnIndex;
    }

    // return Player Object at turn index
    private Player getPlayerAtTurnIndex() {
        return playersList.get(getTurnIndex());
    }

    // return name of player at turn index
    private String getNamePlayerAtTurnIndex() {
        return getPlayerAtTurnIndex().getName();
    }

    // return phone number of player at turn index
    private String getPhoneNumberPlayerAtTurnIndex() {
        return getPlayerAtTurnIndex().getPhoneNumber();
    }

    /* ALGORITHM: After checking Blackjack and dealer is not Blackjack, traverse and get players' move

    1. Notify name of the player at turn index, show current total and ask for move (stand/ draw).

    2. Get input phone number and move ( LATER: get from user's input in Android Studio).

    3. Compare input phone number and one at turn index.

    4. If correct player, check if move is valid or not.

    5. Otherwise, repeat step 1 until get correct phone number at turn index.

     */

    // Step 1
    private void introMove() {
        System.out.println("It's " + getNamePlayerAtTurnIndex() + "'s turn.");
        System.out.println("Total: " + getPlayerAtTurnIndex().getTotalPointPlayer());
        System.out.println();
        System.out.println("Stand/Draw: ");

        //  GETTING PHONE NUMBER IN ANDROID STUDIO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //System.out.println(" ");
    }

    // Step 2- Overall



    // Step 3
    // compare the phoneNumber at turn index and the given phoneNumber
    private boolean comparePhoneNumbers(String inputPhoneNumber) {
        return inputPhoneNumber == getPhoneNumberPlayerAtTurnIndex();
    }

    private void notifyIfIncorrectPlayerTurn(String incorrectPlayerName){}

    // Step 4
    private boolean isMoveValid(String givenMove) {
        return (givenMove.equals("Stand") || givenMove.equals("Draw")) ? true : false;
    }
    private String getValidMove() {
        Scanner scanner = new Scanner(System.in);
        String move = scanner.next();
        while (!isMoveValid(move)) {
            System.out.println("Move is invalid! Input valid move(Stand/Draw): ");
            move = scanner.next();
        }
        return move;
    }



    // if dealer has Blackjack
    private void ifDealerBlackjack(Player player) {
        if (player.isPlayerBlackjack()) {
            player.setStatusTie();
        } else {
            player.setStatusLose();

        }
    }

    // if dealer doesn't have Blackjack
    private void ifDealerNotBlackjack(Player player) {
        if (player.isPlayerBlackjack()) {
            player.setStatusWin();
        }
    }

    private boolean checkDealerBlackjack() {
        return dealer.isPlayerBlackjack();
    }

    // check Blackjack, return boolean
    public void checkPlayersBlackjack() {
        boolean isDealerBlackjack = checkDealerBlackjack();

        // if dealer has Blackjack
        if (isDealerBlackjack) {
            for (int i = 0; i < playersList.size() - 1; i++) {
                ifDealerBlackjack(playersList.get(i));
            }
        } else {
            for (int i = 0; i < playersList.size() - 1; i++) {
                ifDealerNotBlackjack(playersList.get(i));

            }
        }
    }


    public void showResult() {
        for (int i = 0; i < playersList.size() - 1; i++) {
            System.out.println(playersList.get(i).getName() + ": " + playersList.get(i).getStatus());
        }
    }

    // Shuffle the deck of cards
    private void shuffleDeck() {
        deck.shuffleDeck();
        System.out.println();

    }


    // For the case the deck is not empty initially
    private void initDeck() {
        deck = new Deck(false);
    }

    private void dealCardForPlayer(Player player) {
        deck.dealCard(player);
    }

    // deal 2 cards for players and dealer in the players list without checking status of player. Then print hand and show total point of all players
    public void dealStartingCards() {
        // deal cards for players
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            System.out.println("Dealing cards for: " + currentPlayer.getName());
            dealCardForPlayer(currentPlayer);
            dealCardForPlayer(currentPlayer);

            System.out.println(currentPlayer.getName() + "'s hand: ");
            currentPlayer.printHand();
            System.out.println("Total: " + currentPlayer.getTotalPointPlayer());
            System.out.println();
        }

        // deal cards for dealer
        Player dealer = playersList.get(playersList.size() - 1);
        dealCardForPlayer(dealer);
        dealCardForPlayer(dealer);

        System.out.printf("Dealer: " + dealer.getTotalPointPlayer());
        System.out.println();
    }

    // show name and total point of all players
    private void showPoint() {
        System.out.println("Player\t\t\t\t\t" + "Points");
        for (int i = 0; i < playersList.size() - 1; i++) {
            System.out.println(playersList.get(i).getName() + "\t\t\t\t\t" + playersList.get(i).getTotalPointPlayer());
        }
    }

    public static void main(String[] args) {
        Player player1 = new Player("A", "1", false);
        Player player2 = new Player("B", "2", false);
        Player player3 = new Player("C", "3", false);
        ArrayList<Player> playerArrayList = new ArrayList<>();
        playerArrayList.add(player1);
        playerArrayList.add(player2);
        playerArrayList.add(player3);

        GameDriver gameDriver = new GameDriver();
        gameDriver.initGame(playerArrayList, 1);
        System.out.println("Fuck you!!!!!");


        //gameDriver.showPoint();
        //gameDriver.dealStartingCards();

        // print deck
        //gameDriver.deck.printDeck();
    }


}
