package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    void gutterGame() {
        // given
        BowlingGame game = new BowlingGame();

        // when
        pointsZero(game, 1, 10);

        // then
        assertEquals(0, game.score());
    }

    @Test
    void onePin() {
        // given
        BowlingGame game = new BowlingGame();

        // when
        game.roll(1);
        game.roll(0);
        pointsZero(game, 2, 10);

        // then
        assertEquals(1, game.score());
    }

    @Test
    void accurateBowlingOverTen() {
        // given
        BowlingGame game = new BowlingGame();

        // when // then
        assertThrows(IllegalArgumentException.class,
                () -> game.roll(11));
    }

    @Test
    void accurateBowlingUnderZero() {
        // given
        BowlingGame game = new BowlingGame();

        // when // then
        assertThrows(IllegalArgumentException.class,
                () -> game.roll(-1));
    }

    @Test
    void spare() {
        // given
        BowlingGame game = new BowlingGame();
        game.roll(7);

        // when
        game.roll(3);

        game.roll(1);
        game.roll(0);
        pointsZero(game, 3, 10);

        // then
        assertEquals(12, game.score());
    }

    @Test
    void spare2() {
        // given
        BowlingGame game = new BowlingGame();
        pointsZero(game, 1, 9);

        // when
        game.roll(7);
        game.roll(3);
        game.roll(8);

        // then
        assertEquals(18, game.score());
    }

    @Test
    void strike() {
        // given
        BowlingGame game = new BowlingGame();
        game.roll(10);

        // when
        game.roll(1);
        game.roll(1);

        pointsZero(game, 3, 10);

        // then
        assertEquals(14, game.score());
    }

    @Test
    void strike2() {
        // given
        BowlingGame game = new BowlingGame();
        pointsZero(game, 1, 9);

        // when
        game.roll(10);
        game.roll(4);
        game.roll(3);

        // then
        assertEquals(17, game.score());
    }

    @Test
    void strike3() {
        // given
        BowlingGame game = new BowlingGame();
        pointsZero(game, 1, 9);

        // when
        game.roll(10);
        game.roll(10);
        game.roll(10);

        // then
        assertEquals(30, game.score());
    }

    @Test
    void strike4() {
        // given
        BowlingGame game = new BowlingGame();

        // when
        game.roll(10);
        game.roll(10);
        game.roll(10);
        pointsZero(game, 4, 10);

        // then
        assertEquals(60, game.score());
    }

    @Test
    void strike5() {
        // given
        BowlingGame game = new BowlingGame();
        pointsZero(game, 1, 8);

        // when
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);

        // then
        assertEquals(60, game.score());
    }

    @Test
    void accurateBowling2() {
        // given
        BowlingGame game = new BowlingGame();
        game.roll(4);

        // when // then
        assertThrows(IllegalStateException.class, () -> game.roll(7));
    }

    @Test
    void perfectGame() {
        // given
        BowlingGame game = new BowlingGame();

        // when
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }

        // then
        assertEquals(300, game.score());
    }

    @Test
    void accurateBowling3() {
        // given
        BowlingGame game = new BowlingGame();
        pointsZero(game, 1, 9);

        // when
        game.roll(10);
        game.roll(3);
        game.roll(0);

        // then
        assertEquals(13, game.score());
    }

    @Test
    void realGame() {
        // given
        BowlingGame game = new BowlingGame();
        int[] rolls = {10, 9, 1, 7, 0, 9, 1, 10, 10, 8, 2, 10, 9, 1, 9, 1, 7};

        // when
        for (int roll : rolls) {
            game.roll(roll);
        }

        // then
        assertEquals(188, game.score());
    }

    @Test
    void accurateBowling4() {
        // given
        BowlingGame game = new BowlingGame();
        int[] rolls = {10, 9, 1, 7, 0, 9, 1, 10, 10, 8, 2, 10, 9, 1, 9, 1, 7};

        for (int roll : rolls) {
            game.roll(roll);
        }
        game.roll(3);

        // when // then
        assertThrows(IllegalArgumentException.class, game::score);
    }

    @Test
    void accurateBowling4NoSpareOnLastFrame() {
        // given
        BowlingGame game = new BowlingGame();
        int[] rolls = {10, 9, 1, 7, 0, 9, 1, 10, 10, 8, 2, 10, 9, 1, 8, 1};

        for (int roll : rolls) {
            game.roll(roll);
        }
        game.roll(3);

        // when // then
        assertThrows(IllegalArgumentException.class, game::score);
    }

    private void pointsZero(BowlingGame game, int from, int to) {
        for (int frame = from; frame <= to; frame++) {
            game.roll(0);
            game.roll(0);
        }
    }
}