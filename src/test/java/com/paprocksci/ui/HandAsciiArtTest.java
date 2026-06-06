package com.paprocksci.ui;

import com.paprocksci.model.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandAsciiArtTest {

    @Test
    void eachHandHasAsciiArt() {
        assertEquals(Hand.values().length, 5);
        for (Hand hand : Hand.values()) {
            assertTrue(HandAsciiArt.lines(hand).length > 0);
        }
    }

    @Test
    void allArtLinesPadToBlockWidth() {
        for (Hand hand : Hand.values()) {
            String[] lines = HandAsciiArt.lines(hand);
            int width = HandAsciiArt.blockWidth(lines);
            for (String line : lines) {
                assertEquals(width, line.length());
            }
        }
    }

    @Test
    void padLineAlignsShortStrings() {
        assertEquals("    _______  ", HandAsciiArt.padLine("    _______", 13));
        assertEquals("---.__(___)  ", HandAsciiArt.padLine("---.__(___)", 13));
    }

    @Test
    void rockArtMatchesClassicShape() {
        String[] rock = HandAsciiArt.lines(Hand.ROCK);
        assertEquals(6, rock.length);
        assertEquals("    _______  ", rock[0]);
        assertEquals("---.__(___)  ", rock[5]);
    }

    @Test
    void mirrorLineFlipsFistOrientationWhenPadded() {
        String padded = HandAsciiArt.padLine("---'   ____)", 13);
        assertEquals(" (____   '---", HandAsciiArt.mirrorLine(padded));
    }

    @Test
    void likeArtMatchesSimplifiedThumbsUp() {
        String[] like = HandAsciiArt.lines(Hand.LIKE);
        assertEquals(8, like.length);
        assertTrue(like[0].startsWith(" _"));
        assertTrue(like[1].startsWith("| \\"));
        assertTrue(like[7].startsWith("|_______)"));
    }

    @Test
    void dislikeArtMatchesSimplifiedThumbsDown() {
        String[] dislike = HandAsciiArt.lines(Hand.DISLIKE);
        assertEquals(8, dislike.length);
        assertTrue(dislike[0].startsWith(" _______"));
        assertTrue(dislike[1].startsWith("|     __)"));
        assertTrue(dislike[7].startsWith("|_/"));
    }

    @Test
    void mirroringTwiceRestoresOriginalLine() {
        for (Hand hand : Hand.values()) {
            for (String line : HandAsciiArt.lines(hand)) {
                assertEquals(line, HandAsciiArt.mirrorLine(HandAsciiArt.mirrorLine(line)));
            }
        }
    }

    @Test
    void shorterArtIsVerticallyCentered() {
        String[] shortArt = { "a", "b", "c" };
        int height = 7;

        assertEquals("", HandAsciiArt.lineAt(shortArt, 0, height));
        assertEquals("", HandAsciiArt.lineAt(shortArt, 1, height));
        assertEquals("a", HandAsciiArt.lineAt(shortArt, 2, height));
        assertEquals("b", HandAsciiArt.lineAt(shortArt, 3, height));
        assertEquals("c", HandAsciiArt.lineAt(shortArt, 4, height));
        assertEquals("", HandAsciiArt.lineAt(shortArt, 5, height));
        assertEquals("", HandAsciiArt.lineAt(shortArt, 6, height));
    }
}
