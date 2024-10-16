package org.example;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Integer> scores;

    public BowlingGame() {
        scores = new ArrayList<>();
    }

    public void roll(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException();
        }

        if (!isLastFrame() && isFrameOverTen(pins)) {
            throw new IllegalStateException();
        }

        scores.add(pins);
        if (!isLastFrame() && isStrike(pins)) {
            scores.add(0);
        }
    }

    public int score() {
        if (isInvalidRolls()) {
            throw new IllegalArgumentException();
        }

        int score = 0;
        for (int frame = 1; frame <= 10; frame++) {
            final int first = scores.get((frame - 1) * 2);
            final int second = scores.get((frame - 1) * 2 + 1);

            if (isStrike(first)) {
                score += strikeBonus(frame);
            } else if (isSpare(first, second)) {
                score += spareBonus(frame);
            }
            score += first + second;
        }
        return score;
    }

    private boolean isInvalidRolls() {
        final int firstOfLastFrame = scores.get(18);
        final int secondOfLastFrame = scores.get(19);

        if (isStrike(firstOfLastFrame) || isSpare(firstOfLastFrame, secondOfLastFrame)) {
            return scores.size() > 21;
        }
        return scores.size() > 20;
    }

    private int spareBonus(int frame) {
        return scores.get((frame - 1) * 2 + 2);
    }

    private int strikeBonus(int frame) {
        if (frame == 10) {
            return scores.get((frame - 1) * 2 + 2);
        }
        if (isStrike(scores.get((frame - 1) * 2 + 2))) {
            return scores.get((frame - 1) * 2 + 2) + scores.get((frame - 1) * 2 + 4);
        }
        return scores.get((frame - 1) * 2 + 2) + scores.get((frame - 1) * 2 + 3);
    }

    private boolean isFrameOverTen(int pins) {
        return scores.size() % 2 == 1 && scores.get(scores.size() - 1) + pins > 10;
    }

    private static boolean isStrike(int pins) {
        return pins == 10;
    }

    private static boolean isSpare(int first, int second) {
        return first + second == 10;
    }

    private boolean isLastFrame() {
        return scores.size() >= 18;
    }
}
