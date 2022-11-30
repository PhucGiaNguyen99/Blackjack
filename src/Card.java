public class Card {
    private String value;
    private String suit;

    private int numOfCards;
    private Card link;

    public Card(String value, String suit, Card link) {
        this.value = value;
        this.suit = suit;
        this.link = link;
    }

    // Return the card of the deck
    public String toString() {
        switch (suit) {
            case "H":
                switch (value) {
                    case "1":
                        return "A- H";
                    case "11":
                        return "J- H";
                    case "12":
                        return "Q- H";
                    case "13":
                        return "K- H";
                    default:
                        return value + "- H";

                }
            case "S":
                switch (value) {
                    case "1":
                        return "A- S";
                    case "11":
                        return "J- S";
                    case "12":
                        return "Q- S";
                    case "13":
                        return "K- S";
                    default:
                        return value + "- S";

                }
            case "D":
                switch (value) {
                    case "1":
                        return "A- D";
                    case "11":
                        return "J- D";
                    case "12":
                        return "Q- D";
                    case "13":
                        return "K- D";
                    default:
                        return value + "- D";

                }
            case "C":
                switch (value) {
                    case "1":
                        return "A- C";
                    case "11":
                        return "J- C";
                    case "12":
                        return "Q- C";
                    case "13":
                        return "K- C";
                    default:
                        return value + "- C";
                }
        }
        return null;
    }

    public void setCard(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card getLink() {
        return link;
    }

    public void setLink(Card newNode) {
        this.link = newNode;
    }


    // return the value of the card. The value 11, 12 and 13 are counted as 10, otherwise counted as its integer value.
    public int getCardValue() {
        if (value.equals("11") || value.equals("12") || value.equals("13")) {
            return 10;
        }
        return Integer.parseInt(this.value);
    }


    // compare the value of two cards. Return -1 if the object card is smaller than the compared card, return 0 if both cards are equal, otherwise return 1
    public int compareCards(Card otherCard) {
        return this.getCardValue() < otherCard.getCardValue() ? -1 : this.getCardValue() == otherCard.getCardValue() ? 0 : 1;
    }


}
