import java.util.ArrayList;
import java.util.Scanner;

public class BJGameDriver {

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

        // Initialize the turn index to 1 as dealer takes the index 0
        turnIndex = 0;
        numOfPlayers = players.size();
        //nonBlackJackPlayersList = new ArrayList<>();

        // Initialize the game status to unfinished
        isGameFinished = false;
        while (isGameFinished == false) {

            // Initialize the players list
            initPlayers(players);

            // Initialize the deck of cards
            initDeck();

            // Shuffle the deck of cards
            shuffleDeck();

            // Deal starting cards for the players
            dealStartingCards();

            // After splitting the card, check whether the dealer has Black Jack, then traverse all the players
            // If any also has Black Jack, then set the status to even, otherwise to lose.
            checkBlackjack();

            // Otherwise, traverse the player list to ask whether any wants to draw like regular
            // Traverse the players list and put the player into the process move
            for (Player player : this.playersList) {
                String playerMove = getMove(player);
                processMove(player.getPhoneNumber(), playerMove);
            }
            //fdealerPlays();
            showFinalPoints();
            //initWinners();

        }
    }


    // Request the input of the player for the move option
    private String getMove(Player player) {
        System.out.println("It's " + player.getName() + "'s turn.");
        System.out.println("Current total point: " + player.getTotalPointPlayer());
        System.out.println("Enter move: ");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.next().toUpperCase();
        while (!move.equals("HIT") && !move.equals("STAND")) {
            System.out.println("Current total point: " + player.getTotalPointPlayer());
            System.out.println("Enter valid move(Hit/Stand): ");
            move = scanner.next().toUpperCase();
        }
        return move;
    }


    // Shuffle the deck of cards
    private void shuffleDeck() {
        deck.shuffleDeck();
    }


    // For the case the deck is not empty initially
    private void initDeck() {
        deck = new Deck(false);
    }

    // append the dealer to the end of the players list
    private void initPlayers(ArrayList<Player> playerArrayList) {
        playersList = playerArrayList;
        //this.winnersArrayList = new ArrayList<>();
        playersList.add(dealer);
    }

    // Check if the game is finished yet
    private boolean isGameFinished() {
        return isGameFinished;
    }


    // Check whether the dealer is busted
    private boolean isDealerBusted() {
        return dealer.isPlayerBusted();
    }

    // Show final points of all players and the dealer
    private void showFinalPoints() {

    }


    /* Determine the winners and return an arrayList of the winners for android dev use later
    public void initWinners() {

        // If the dealer is not busted, the winners are players having total point higher than the dealer and not busted
        if (!isDealerBusted()) {
            int dealerPoint = dealer.calculateTotalDealerHand();

            for (Player player : playersList) {

                // In case: Both the dealer and the player are not busted
                if (!player.isPlayerBusted()) {
                    int playerPoint = player.getTotalPointPlayer();

                    if (playerPoint > dealerPoint) {
                        player.setStatusWin();
                        winnersArrayList.add(player);
                    } else if (playerPoint == dealerPoint) {
                        player.setStatusTie();
                    } else {
                        player.setStatusLose();
                    }

                }

                // In case: If the player is busted, the dealer wins
                else {
                    player.setStatus(-1);
                }
                // Print out the player result
                System.out.println(player.getResult());
            }
        }

        // Otherwise, all are not busted win
        else {
            for (Player player : playersList) {

                // If both the player and the dealer are busted, set status of the player to even
                if (!player.isPlayerBusted()) {
                    player.setStatus(1);
                }

                // Otherwise, set status to win
                else {
                    player.setStatus(0);
                }

                // Print out the player result
                System.out.println(player.getResult());
            }
        }
        // Terminate the game
        isGameFinished = true;
    }*/

    // DELETED
    // Remove the first card of the deck
    private Card removeFirstCard() {
        return deck.removeAtIndex(0);
    }

    // deal a card for the player
    private void dealCardForPlayer(Player player) {
        deck.dealCard(player);
    }

    private void checkBlackjack() {

        // Show that the dealer has Black Jack
        if (dealer.isPlayerBlackjack()) {
            System.out.println("Dealer has Black Jack!!!!");
            System.out.println();
        }

        for (Player player : playersList) {

            // If the dealer has Black Jack
            if (dealer.isPlayerBlackjack()) {

                if (player.isPlayerBlackjack()) {
                    System.out.println("Player having phone number: " + player.getPhoneNumber() + " has Black Jack!!");
                    player.setStatusTie();
                } else {
                    player.setStatusLose();
                }
                System.out.println(player.getResult());

                // Terminate the game
                isGameFinished = true;
            }

            // Otherwise, if the player has Black Jack, then set the status to win, and continue with the remaining players
            // If the dealer does not have Blackjack, Add non-Blackjack players to the list
            // and use such list after that

            else {
                if (player.isPlayerBlackjack()) {
                    System.out.println("Player having phone number: " + player.getPhoneNumber() + " has Black Jack!!");
                    player.setStatusWin();
                }
                //nonBlackJackPlayersList.add(player);
            }

        }
    }

    // FUCK IT!!
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

    }

    // Check whether dealer has soft 17 or not, if he does, he would continue drawing
    // Dealer has soft 17 when there is any ace in the hand and the total equals to 17
    // private boolean dealerHasSoft17() {
    //return dealer.containAce()&&dealer.calculateTotalDealerHand()==17;


    // For the case: The dealer does not have Black Jack

    // Dealer must hit while his hand is 16 or under
    // And check whether he has soft 17 or not
    /*private void dealerPlays() {
        while (dealer.calculateTotalDealerHand() <= 16) {
            System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
            System.out.println("Dealer continues to hit.");

            dealCardForDealer();

            System.out.println();
        }

        // Check whether dealer has soft 17 or not, if he does, he would continue drawing
        // Dealer has soft 17 when there is any ace in the hand and the total equals to 17
        while (dealerHasSoft17()) {
            System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
            System.out.println("Dealer continues to hit.");
            dealCardForDealer();
            System.out.println();
        }

        System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
        System.out.println();
    }*/


    // Manage the move of the players
    private void processMove(String phoneNumber, String move) {

        // Process move for PLAYER first

        // Determine the current player based on the turn index
        Player currentPlayer = playersList.get(turnIndex);

        if (phoneNumber.equals(currentPlayer.getPhoneNumber())) {

            // Notify the current player name and total point
            System.out.println("It's " + currentPlayer.getName() + "s turn.");

            // Check if the player has Black Jack, if he does, set his status to wi
            int currentTotalPoint = currentPlayer.getTotalPointPlayer();
            System.out.println("Current TOTAL POINT: " + currentTotalPoint);
            System.out.println();


            // If the player wants to stand, then skip to the next player in the list
            if (move.toUpperCase().equals("STAND")) {
                turnIndex++;
                return;
            }

            while (move.toUpperCase().equals("HIT")) {

                // Check if the player is busted or not, if the player is busted, skip to the next player
                if (currentPlayer.isPlayerBusted()) {
                    System.out.println("You've been busted already!");
                    turnIndex++;
                    return;
                }

                // If the player chooses to hit and he/she is not busted yet, then deal card for him/her and process the move
                dealCardForPlayer(currentPlayer);
                System.out.println(currentPlayer.getName() + "'s card hand: ");
                currentPlayer.printHand();
                System.out.println("TOTAL POINT after the move: " + currentPlayer.getTotalPointPlayer());
                System.out.println();

                System.out.println("Current total point: " + currentPlayer.getTotalPointPlayer());
                System.out.println("STAND or HIT ?");
                Scanner scanner = new Scanner(System.in);
                move = scanner.next();

                // If the player wants to stand, then skip to the next player in the list
                if (move.toUpperCase().equals("STAND")) {
                    turnIndex++;
                    return;
                }
            }

            // Process move for the dealer after dealing with all the players
            if (turnIndex == numOfPlayers - 1) {
                //dealerPlays();
                System.out.println("Black Jack game is over!!!!");
                System.out.println(" WHO IS WINNER????");
                isGameFinished = true;
            }
        } else {
            // If the phone number is not matched to the current player's one
            System.out.println(" It's not turn of the player having phone number " + phoneNumber + "!!!!");
        }
    }

    // FUCK IT!!
    // show name and total point of all players
    private void showPoint() {
        System.out.println("Player\t\t\t\t\t" + "Points");
        for (int i = 0; i < playersList.size()-1; i++) {
            System.out.println(playersList.get(i).getName() + "\t\t\t\t\t" + playersList.get(i).getTotalPointPlayer());
        }
    }


}


