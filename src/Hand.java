import java.util.ArrayList;

public class Hand {
    public static final int BLACKJACK_VALUE = 10000;
    public static final int BUSTED_VALUE = -1;
    private ArrayList<Card> cardHand;


    // initialize hand with no card
    public Hand() {
        cardHand = new ArrayList<>();
    }

    // calculate the total point of hand
    public int calculateTotalPoint() {

        // divide the total point into Ace sum and non-Ace sum. Initialize totalPoint, nonAcesSum, acesSum and numOfAces with 0
        int totalPoint = 0;
        int nonAcesSum = 0;
        int acesSum = 0;
        int numOfAces = 0;

        // calculate sum of non-Aces cards and count the number of Aces
        for (Card card : cardHand) {
            if (card.getCardValue() == 1) {
                numOfAces++;
            } else {
                nonAcesSum += card.getCardValue();
            }
        }

        // if there is any Ace, count ONE Ace as 11 by starting the Aces sum with 11 and adding 1 for every remaining Ace
        if (numOfAces > 0) {
            acesSum = 11;
        }
        for (int countAce = 0; countAce < numOfAces - 1; countAce++) {
            acesSum += 1;
        }

        // if the player is busted, then every Ace are counted as 1 only
        if (acesSum + nonAcesSum > 21) {
            acesSum = numOfAces;
        }

        totalPoint = acesSum + nonAcesSum;

        return totalPoint;
    }

    // check if hand is busted, that the total point is larger than 21
    public boolean isBusted() {
        return this.calculateTotalPoint() > 21;
    }

    // check if hand has BlackJack, that there are 2 cards in hand and the total point is 21
    public boolean isBlackjack() {
        return (cardHand.size() == 2 && this.calculateTotalPoint() == 21);
    }

    // get the integer status of hand, return the BlackJack constant if hand has BlackJack or return the Busted constant if hand is busted, else return the total point of hand
    public int getStatus() {
        if (isBusted()) {
            return BUSTED_VALUE;
        } else if (isBlackjack()) {
            return BLACKJACK_VALUE;
        } else {
            return this.calculateTotalPoint();
        }
    }


    // present hand
    public void printHand() {
        // System.out.println("Card hand of the player: ");
        for (int i = 0; i < cardHand.size(); i++) {
            System.out.println(cardHand.get(i).toString());
        }
    }

    // add a new card to hand
    public void addCardToHand(Card newCard) {
        cardHand.add(newCard);
    }


    // compare status of 2 hands. Return 0 if both have BlackJack or get busted, return 1 if status of this hand is higher than other hand, otherwise return -1
    public int compareTo(Hand otherHand) {
        // return 0 if both are busted, or both have Black Jack. Using the getStatus() method
        //if ((this.getStatus() == -1 && otherHand.getStatus() == -1) || (this.getStatus() == 10000 && otherHand.getStatus() == 10000)) {
        //  return 0;
        return this.getStatus() > otherHand.getStatus() ? 1 : this.getStatus() == otherHand.getStatus() ? 0 : -1;
    }

    // Check whether card hand including any ace
    public boolean containAce() {
        for (Card card : cardHand) {
            if (card.getCardValue() == 1) {
                return true;
            }
        }
        return false;
    }


}
