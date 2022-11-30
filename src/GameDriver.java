import java.util.ArrayList;

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

    private ArrayList<Player> winnersArrayList; // DELETE. Use player status to keep track of whether player is Black Jack or busted


    // Control the whole game
    public void initGame(ArrayList<Player> players, int deckCount) {

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
            System.out.println(playersList.get(i) + ": " + playersList.get(i).getStatus());
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

        //gameDriver.showPoint();
        //gameDriver.dealStartingCards();

        // print deck
        //gameDriver.deck.printDeck();
    }


}
