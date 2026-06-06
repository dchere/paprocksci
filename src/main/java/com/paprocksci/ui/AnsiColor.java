package com.paprocksci.ui;

public final class AnsiColor {

    public enum Style {
        PROMPT("\033[36m"),
        HINT("\033[90m"),
        ERROR("\033[31m"),
        SUCCESS("\033[32m"),
        WARNING("\033[33m"),
        INFO("\033[96m"),
        HEADER("\033[1m\033[97m"),
        PLAYER("\033[32m"),
        COMPUTER("\033[35m"),
        DRAW("\033[33m"),
        BLUE("\033[34m"),
        YELLOW("\033[93m"),
        RESET("\033[0m");

        private final String code;

        Style(String code) {
            this.code = code;
        }
    }

    private static boolean enabled = detectColorSupport();

    private AnsiColor() {
    }

    private static boolean detectColorSupport() {
        if (System.getenv("NO_COLOR") != null) {
            return false;
        }
        if ("true".equalsIgnoreCase(System.getenv("FORCE_COLOR"))) {
            return true;
        }
        if (System.console() != null) {
            return true;
        }
        String term = System.getenv("TERM");
        return term != null && !term.isBlank() && !"dumb".equals(term);
    }

    static void setEnabledForTesting(boolean colorEnabled) {
        enabled = colorEnabled;
    }

    static void resetEnabledForTesting() {
        enabled = detectColorSupport();
    }

    public static String apply(Style style, String text) {
        if (!enabled || text == null) {
            return text;
        }
        return style.code + text + Style.RESET.code;
    }

    public static String prompt(String question, String hint) {
        return apply(Style.PROMPT, question) + apply(Style.HINT, hint);
    }

    public static void println(Style style, String text) {
        System.out.println(apply(style, text));
    }

    public static void printf(Style style, String format, Object... args) {
        System.out.printf(apply(style, format), args);
    }
}
