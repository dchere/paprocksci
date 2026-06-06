package com.paprocksci.ui;

import com.paprocksci.model.Hand;

public final class HandAsciiArt {

    private static final String[][] ART = {
            {
                    "    _______",
                    "---'   ____)",
                    "      (_____)",
                    "      (_____)",
                    "      (____)",
                    "---.__(___)"
            },
            {
                    "    ________",
                    "---'    ____)____",
                    "           ______)",
                    "          _______)",
                    "         _______)",
                    "---.__________)"
            },
            {
                    "    _______",
                    "---'   ____)____",
                    "          ______)",
                    "       __________)",
                    "      (____)",
                    "---.__(___)"
            },
            {
                    " _",
                    "| \\",
                    "|  |",
                    "|  |____",
                    "|     __)",
                    "|     __)",
                    "|     __)",
                    "|_______)"
            },
            {
                    " _______",
                    "|     __)",
                    "|     __)",
                    "|     __)",
                    "|  _____)",
                    "| |",
                    "| |",
                    "|_/"
            }
    };

    private HandAsciiArt() {
    }

    public static String[] lines(Hand hand) {
        return normalizeLines(ART[hand.ordinal()].clone());
    }

    static String[] normalizeLines(String[] lines) {
        int width = blockWidth(lines);
        String[] normalized = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            normalized[i] = padLine(lines[i], width);
        }
        return normalized;
    }

    public static void printDuel(Hand leftHand, Hand rightHand,
            String leftLabel, String rightLabel,
            AnsiColor.Style leftStyle, AnsiColor.Style rightStyle) {
        String[] leftLines = lines(leftHand);
        String[] rightLines = mirrorLines(lines(rightHand));
        int leftWidth = blockWidth(leftLines);
        int rightWidth = blockWidth(rightLines);
        int height = Math.max(leftLines.length, rightLines.length);

        System.out.println(formatRow(leftLabel, leftWidth, rightLabel, rightWidth, leftStyle, rightStyle));

        for (int row = 0; row < height; row++) {
            String left = lineAt(leftLines, row, height);
            String right = lineAt(rightLines, row, height);
            System.out.println(formatRow(left, leftWidth, right, rightWidth, leftStyle, rightStyle));
        }
    }

    static String lineAt(String[] lines, int row, int totalHeight) {
        int offset = (totalHeight - lines.length) / 2;
        int index = row - offset;
        if (index < 0 || index >= lines.length) {
            return "";
        }
        return lines[index];
    }

    static String[] mirrorLines(String[] lines) {
        String[] mirrored = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            mirrored[i] = mirrorLine(lines[i]);
        }
        return mirrored;
    }

    static String mirrorLine(String line) {
        int[] codePoints = line.codePoints()
                .map(HandAsciiArt::mirrorCodePoint)
                .toArray();
        StringBuilder mirrored = new StringBuilder(line.length());
        for (int i = codePoints.length - 1; i >= 0; i--) {
            mirrored.appendCodePoint(codePoints[i]);
        }
        return mirrored.toString();
    }

    private static int mirrorCodePoint(int codePoint) {
        return switch (codePoint) {
            case '(' -> ')';
            case ')' -> '(';
            case '/' -> '\\';
            case '\\' -> '/';
            default -> codePoint;
        };
    }

    static int blockWidth(String[] lines) {
        int max = 0;
        for (String line : lines) {
            max = Math.max(max, line.length());
        }
        return max;
    }

    static String padLine(String line, int width) {
        if (line.length() > width) {
            return line.substring(0, width);
        }
        return String.format("%-" + width + "s", line);
    }

    private static String formatRow(String left, int leftWidth, String right, int rightWidth,
            AnsiColor.Style leftStyle, AnsiColor.Style rightStyle) {
        return AnsiColor.apply(leftStyle, padLine(left, leftWidth))
                + " | "
                + AnsiColor.apply(rightStyle, padLine(right, rightWidth));
    }
}
