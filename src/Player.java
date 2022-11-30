public class Player {
    private String name;
    private String phoneNumber;
    private Hand playerHand;

    private boolean isDealer;
    private int status;
    public static final int STATUS_LOSE = -1, STATUS_WIN = 1, STATUS_TIE = 0, STATUS_UNDETERMINED = 99;

    // initialize the player with name, phone number, an object of Card Hand and undetermined status
    // Otherwise, if it's dealer, the input will be empty for name and phone number. We just need to initialize hand and set status to undetermined
    public Player(String name, String phoneNumber, boolean isDealer) {
        if (isDealer == false) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
        this.playerHand = new Hand();
        this.status = STATUS_UNDETERMINED;
    }


    public String getStatus() {
        switch (status) {
            case STATUS_LOSE:
                return "Lose";
            case STATUS_TIE:
                return "Tie";
            case STATUS_WIN:
                return "Win";
        }
        return "Undetermined";
    }

    // method to present status of player
    public String getResult() {
        return "Player: " + this.getName() + "\nStatus: " + this.getStatus();
    }


    // change status of the player
    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusTie() {
        setStatus(STATUS_TIE);
    }

    public void setStatusWin() {
        setStatus(STATUS_WIN);
    }

    public void setStatusLose() {
        setStatus(STATUS_LOSE);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    /*public void setPlayerHand(CardHand playerHand) {
        this.playerHand = playerHand;
    }*/


    // method to calculate the total point of the player's hand
    public int getTotalPointPlayer() {
        return playerHand.calculateTotalPoint();
    }

    // present the player's hand
    public void printHand() {
        playerHand.printHand();
    }

    // return String for the player's info: name, phone number and status
    public String toString() {
        return "Name: " + getName() + ".  " + "Phone number: " + getPhoneNumber() + "\nStatus: " + getStatus();
    }

    // add one card to the player hand. DELETED<- deal card will do in Deck class
    //public void dealCard(Card cardNode) {
    //  this.getPlayerHand().addCardToHand(cardNode);
    //}

    // check if the player busted or not
    public boolean isPlayerBusted() {

        return playerHand.isBusted();
    }

    // check if the player has Blackjack or not
    public boolean isPlayerBlackjack() {
        return playerHand.isBlackjack();
    }

    // add given card to player's hand
    public void addCardToPlayerHand(Card card){
        playerHand.addCardToHand(card);
    }
}
