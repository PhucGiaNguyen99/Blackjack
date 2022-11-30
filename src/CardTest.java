import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testToStringCardValueFrom2To9() {
        for (int i = 0; i < 1000; i++) {
            // random value from 1-9
            Random random = new Random();
            int randomIntValue = random.nextInt(2, 10);
            String randomStringValue = String.valueOf(randomIntValue);
            Card card1 = new Card(randomStringValue, "H", null);
            assertEquals(randomStringValue + "- H", card1.toString());
        }
    }

    @Test
    void testToStringCardValue1() {
        Card card2 = new Card("1", "H", null);
        assertEquals("A- H", card2.toString());
    }

    @Test
    void testToStringCardValueJQK() {
        for (int i = 0; i < 1000; i++) {
            String[] values = new String[]{"J", "Q", "K"};
            Random random = new Random();
            String randomStringCardValue = values[random.nextInt(3)];
            Card card3 = new Card(randomStringCardValue, "S", null);
            assertEquals(randomStringCardValue + "- S", card3.toString());
        }
    }
    
    @Test
    void testGetCardValueFrom2To9() {
        for (int i = 0; i < 1000; i++) {
            // random value from 1-9
            Random random = new Random();
            int randomValue = random.nextInt(2, 10);
            Card card1 = new Card(String.valueOf(randomValue), "H", null);
            assertEquals(randomValue, card1.getCardValue());
        }
    }

    @Test
    void testGetCardValue1() {
        Card card2 = new Card("1", "H", null);
        assertEquals(1, card2.getCardValue());
    }

    @Test
    void testGetCardValueJQK() {
        for (int i = 0; i < 1000; i++) {
            String[] values = new String[]{"J", "Q", "K"};
            Random random = new Random();
            String randomStringCardValue = values[random.nextInt(3)];
            Card card3 = new Card(randomStringCardValue, "S", null);
            assertEquals(randomStringCardValue + "- S", card3.toString());
        }
    }

    @Test
    void compareCards() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            String randomStringNumber1 = String.valueOf(random.nextInt(14));
            String randomStringNumber2 = String.valueOf(random.nextInt(14));
            Card card1 = new Card(randomStringNumber1, "H", null);
            Card card2 = new Card(randomStringNumber2, "S", null);
            if (card1.getCardValue() > card2.getCardValue()) {
                assertEquals(1, card1.compareCards(card2));
            } else if (card1.getCardValue() == card2.getCardValue()) {
                assertEquals(0, card1.compareCards(card2));
            } else {
                assertEquals(-1, card1.compareCards(card2));
            }
        }
    }
}