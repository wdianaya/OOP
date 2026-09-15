package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {

    @Test
    void testCheckedRes() {
        BlackJack game = new BlackJack();
        assertEquals(Utils.WIN, game.checkedRes(21));
        assertEquals(Utils.FAIL, game.checkedRes(22));
        assertEquals(Utils.FAIL, game.checkedRes(25));
        assertEquals(Utils.CONT, game.checkedRes(20));
        assertEquals(Utils.CONT, game.checkedRes(0));
    }
}