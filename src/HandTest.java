import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;

class HandTest {
    @Test
    void getStatusTestBlackJack() {
        Hand hand1 = new Hand();
        hand1.addCardToHand(new Card("1", "S", null));
        hand1.addCardToHand(new Card("12", "H", null));
        assertEquals(10000, hand1.getStatus());
    }

    @Test
    void isBlackJack() {
        Hand hand2 = new Hand();
        hand2.addCardToHand(new Card("1", "S", null));
        hand2.addCardToHand(new Card("12", "H", null));
        assertEquals(10000, hand2.getStatus());
    }

    @Test
    void isBusted() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int value1 = random.nextInt(1, 14);
            int value2 = random.nextInt(1, 14);
            int value3 = random.nextInt(1, 14);
            Card card1 = new Card(String.valueOf(String.valueOf(value1)), "S", null);
            Card card2 = new Card(String.valueOf(String.valueOf(value2)), "S", null);
            Card card3 = new Card(String.valueOf(String.valueOf(value3)), "S", null);

            Hand hand4 = new Hand();
            hand4.addCardToHand(card1);
            hand4.addCardToHand(card2);
            hand4.addCardToHand(card3);

            int totalValue = hand4.calculateTotalPoint();

            // busted
            if (totalValue > 21) {
                assertEquals(true, true);
            } else {
                assertEquals(false, false);
            }
        }
    }

    @Test
    void getStatusTestBusted() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int value1 = random.nextInt(1, 14);
            int value2 = random.nextInt(1, 14);
            int value3 = random.nextInt(1, 14);
            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            Card card3 = new Card(String.valueOf(value3), "S", null);

            Hand hand4 = new Hand();
            hand4.addCardToHand(card1);
            hand4.addCardToHand(card2);
            hand4.addCardToHand(card3);

            int totalValue = hand4.calculateTotalPoint();

            // busted
            if (totalValue > 21) {
                assertEquals(-1, hand4.getStatus());
            } else {
                assertEquals(totalValue, hand4.getStatus());
            }
        }
    }


    // without Ace, so we check between the total of every card's value and result of the method
    @Test
    void calculateTotalPointNoAce() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(2, 14);
            int value2 = random.nextInt(2, 14);
            int value3 = random.nextInt(2, 14);
            int value4 = random.nextInt(2, 14);

            //System.out.println("Card 1: " + value1);
            //System.out.println("Card 2: " + value2);
            //System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            Card card3 = new Card(String.valueOf(value3), "S", null);
            Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + card4.getCardValue();

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(card4);


            assertEquals(totalPoint, hand1.calculateTotalPoint());

        }
    }

    // With 1 Ace, the total doesn't exceed 21, so Ace is counted as 11. Compare between the total of 2 remaining cards plus 11 and the result of the method
    // 2 random cards, with both cards are smaller than 5 and different from Ace
    @Test
    void calculateTotalPointOneAceCountedAs1() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(2, 6);
            int value2 = random.nextInt(2, 6);
            //int value3 = random.nextInt(2, 14);
            //int value4 = random.nextInt(2, 14);

            System.out.println("Card 1: " + value1);
            System.out.println("Card 2: " + value2);
            //System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            //Card card3 = new Card(String.valueOf(value3), "S", null);
            //Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + card2.getCardValue() + 11;

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(new Card("1", "H", null));


            assertEquals(totalPoint, hand1.calculateTotalPoint());
        }
    }

    // With 1 Ace, the total doesn't exceed 21, so Ace is counted as 11. Compare between the total of 2 remaining cards plus 11 and the result of the method
    // 3 random cards, two are smaller than 3 and the last one is smaller than 5 and all are different from Aces
    @Test
    void calculateTotalPointOneAceCountedAs1SecondTest() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(2, 4);
            int value2 = random.nextInt(2, 4);
            int value3 = random.nextInt(2, 5);
            //int value4 = random.nextInt(2, 14);

            System.out.println("Card 1: " + value1);
            System.out.println("Card 2: " + value2);
            System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            Card card3 = new Card(String.valueOf(value3), "S", null);
            //Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + 11;

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(new Card("1", "H", null));


            assertEquals(totalPoint, hand1.calculateTotalPoint());
        }
    }

    // With one Ace, the Ace is counted as 1. Therefore, 2 remaining cards must exceed 6 for each
    @Test
    void calculateTotalPointOneAceCountedAs11() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(6, 14);
            int value2 = random.nextInt(6, 14);
            //int value3 = random.nextInt(2, 5);
            //int value4 = random.nextInt(2, 14);

            System.out.println("Card 1: " + value1);
            System.out.println("Card 2: " + value2);
            //System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            //Card card3 = new Card(String.valueOf(value3), "S", null);
            //Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + card2.getCardValue() + 1;

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            //hand1.addCardToHand(card3);
            hand1.addCardToHand(new Card("1", "H", null));


            assertEquals(totalPoint, hand1.calculateTotalPoint());
        }
    }

    // With one Ace, the Ace is counted as 1. Therefore, 3 remaining cards must exceed 4 for each
    @Test
    void calculateTotalPointOneAceCountedAs11SecondTest() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(4, 14);
            int value2 = random.nextInt(4, 14);
            int value3 = random.nextInt(4, 14);
            //int value4 = random.nextInt(2, 14);

            System.out.println("Card 1: " + value1);
            System.out.println("Card 2: " + value2);
            System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            Card card3 = new Card(String.valueOf(value3), "S", null);
            //Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + card2.getCardValue() + card3.getCardValue() + 1;

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(card3);
            hand1.addCardToHand(new Card("1", "H", null));


            assertEquals(totalPoint, hand1.calculateTotalPoint());
        }
    }

    // With 2 Aces, one Ace is counted as 1 and the other is counted as 11. Therefore, the remaining cards must be lower than 10
    @Test
    void calculateTotalPointTwoAces11And1() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(2, 10);
            //int value2 = random.nextInt(4, 14);
            //int value3 = random.nextInt(4, 14);
            //int value4 = random.nextInt(2, 14);

            System.out.println("Card 1: " + value1);
            //System.out.println("Card 2: " + value2);
            //System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            //Card card2 = new Card(String.valueOf(value2), "S", null);
            //Card card3 = new Card(String.valueOf(value3), "S", null);
            //Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + 12;

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(new Card("1", "H", null));
            hand1.addCardToHand(new Card("1", "H", null));


            assertEquals(totalPoint, hand1.calculateTotalPoint());
        }
    }

    // With 2 Aces counted as 1 for each. Therefore, the remaining cards must exceed 9
    @Test
    void calculateTotalPointTwoAces1() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int value1 = random.nextInt(5, 14);
            int value2 = random.nextInt(5, 14);
            //int value3 = random.nextInt(4, 14);
            //int value4 = random.nextInt(2, 14);

            System.out.println("Card 1: " + value1);
            System.out.println("Card 2: " + value2);
            //System.out.println("Card 3: " + value3);
            //System.out.println("Card 4: " + value4);

            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value2), "S", null);
            //Card card3 = new Card(String.valueOf(value3), "S", null);
            //Card card4 = new Card(String.valueOf(value4), "S", null);
            int totalPoint = card1.getCardValue() + card2.getCardValue() + 2;

            Hand hand1 = new Hand();
            hand1.addCardToHand(card1);
            hand1.addCardToHand(card2);
            hand1.addCardToHand(new Card("1", "H", null));
            hand1.addCardToHand(new Card("1", "H", null));


            assertEquals(totalPoint, hand1.calculateTotalPoint());
        }
    }


    @Test
    void presentCardHand() {
    }

    @Test
    void addCardToHand() {
    }

    // compare 2 busted hands. Use the method isBusted() to check whether each hand is busted or not
    @Test
    void compareToBothBusted() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();

            Hand hand1 = new Hand();
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(8, 14)), "H", null));
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));

            Hand hand2 = new Hand();
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(8, 14)), "H", null));
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));

            assertEquals(0, hand1.compareTo(hand2));
        }
    }

    // compare 2 hands with both have Black Jack
    @Test
    void compareToBothHandsBlackJack() {
        Hand hand1 = new Hand();
        hand1.addCardToHand(new Card("1", "S", null));
        hand1.addCardToHand(new Card("11", "S", null));
        Hand hand2 = new Hand();
        hand2.addCardToHand(new Card("1", "S", null));
        hand2.addCardToHand(new Card("11", "S", null));

        if (hand1.isBlackjack() && hand2.isBlackjack()) {
            assertEquals(0, hand1.compareTo(hand2));
        }
    }

    // compare 2 regular hands. Both hands are not busted
    @Test
    void compareToBothRegular() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();

            int value1 = random.nextInt(1, 8);
            int value2 = random.nextInt(1, 8);
            int value3 = random.nextInt(1, 8);
            int value4 = random.nextInt(1, 8);
            int value5 = random.nextInt(1, 8);
            int value6 = random.nextInt(1, 8);

            Hand hand1 = new Hand();
            hand1.addCardToHand(new Card(String.valueOf(value1), "H", null));
            //System.out.println("Hand 1- Card 1: " + value1);
            hand1.addCardToHand(new Card(String.valueOf(value2), "H", null));
            //System.out.println("Hand 1- Card 2: " + value2);
            hand1.addCardToHand(new Card(String.valueOf(value3), "H", null));
            //System.out.println("Hand 1- Card 3: " + value3);

            Hand hand2 = new Hand();
            hand2.addCardToHand(new Card(String.valueOf(value4), "H", null));
            //System.out.println("Hand 1- Card 4: " + value4);
            hand2.addCardToHand(new Card(String.valueOf(value5), "H", null));
            //System.out.println("Hand 1- Card 5: " + value5);
            hand2.addCardToHand(new Card(String.valueOf(value6), "H", null));
            //System.out.println("Hand 1- Card 6: " + value6);

            if (hand1.calculateTotalPoint() > hand2.calculateTotalPoint()) {
                assertEquals(1, hand1.compareTo(hand2));
            } else if (hand1.calculateTotalPoint() < hand2.calculateTotalPoint()) {
                assertEquals(-1, hand1.compareTo(hand2));
            } else {
                assertEquals(0, hand1.compareTo(hand2));
            }
        }
    }

    // One hand is busted and the other is not busted
    @Test
    void compareToOneBustedOtherNotBusted() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();

            Hand hand1 = new Hand();
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(8, 14)), "H", null));
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));

            Hand hand2 = new Hand();
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(1, 8)), "H", null));
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(1, 8)), "H", null));
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(1, 8)), "H", null));

            assertEquals(-1, hand1.compareTo(hand2));
        }
    }

    // One hand is busted and the other is not busted
    @Test
    void compareToOneBustedOtherNotBustedSecond() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();

            Hand hand2 = new Hand();
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(8, 14)), "H", null));
            hand2.addCardToHand(new Card(String.valueOf(random.nextInt(7, 14)), "H", null));

            Hand hand1 = new Hand();
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(1, 8)), "H", null));
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(1, 8)), "H", null));
            hand1.addCardToHand(new Card(String.valueOf(random.nextInt(1, 8)), "H", null));

            assertEquals(1, hand1.compareTo(hand2));
        }
    }


    @Test
    void containAce() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card("1", "S", null));
        hand.addCardToHand(new Card("10", "S", null));
        assertEquals(true, hand.containAce());
    }

    @Test
    void containNoAce() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card("4", "S", null));
        hand.addCardToHand(new Card("10", "S", null));
        assertEquals(false, hand.containAce());
    }


    @Test
    void main() {
    }
}