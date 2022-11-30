import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getStatus() {
        Player player1 = new Player("Phuc", "0062", false);
        assertEquals("Undetermined", player1.getStatus());
    }

    @Test
    void getResult() {
        Player player1 = new Player("Phuc", "0062", false);
        assertEquals("Player: Phuc" + "\nStatus: Undetermined", player1.getResult());

    }

    @Test
    void setStatus() {
        Player player1 = new Player("Phuc", "0062", false);
        player1.setStatusLose();
        assertEquals("Lose", player1.getStatus());
    }

    @Test
    void setStatusTie() {
        Player player1 = new Player("Phuc", "0062", false);
        player1.setStatusTie();
        assertEquals("Tie", player1.getStatus());
    }

    @Test
    void setStatusWin() {
        Player player1 = new Player("Phuc", "0062", false);
        player1.setStatusWin();
        assertEquals("Win", player1.getStatus());
    }

    @Test
    void setStatusLose() {
        Player player1 = new Player("Phuc", "0062", false);
        player1.setStatusLose();
        assertEquals("Lose", player1.getStatus());
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getPhoneNumber() {
    }

    @Test
    void setPhoneNumber() {
    }

    @Test
    void getPlayerHand() {
    }

    @Test
    void getTotalPointPlayer() {

    }

    @Test
    void printHand() {
    }

    @Test
    void testToString() {
    }

    @Test
    void dealCard() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int value1 = random.nextInt(2, 14);
            int value2 = random.nextInt(2, 14);
            int value3 = random.nextInt(2, 14);
            Card card1 = new Card(String.valueOf(value1), "S", null);
            Card card2 = new Card(String.valueOf(value1), "S", null);

            Card card3 = new Card(String.valueOf(value1), "S", null);


            System.out.println("Card 1: " + value1);
            System.out.println("Card 2: " + value2);
            System.out.println("Card 3: " + value3);
            Player player1 = new Player("Phuc", "0062", false);
            //player1.dealCard(card1);
            //player1.dealCard(card2);
            //player1.dealCard(card3);
            assertEquals(card1.getCardValue() + card2.getCardValue() + card3.getCardValue(), player1.getTotalPointPlayer());
        }

    }

    @Test
    void isPlayerBusted() {
    }

    @Test
    void isPlayerBlackJack() {
    }
}