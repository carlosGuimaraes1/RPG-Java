package domain.enums;

public enum ConsoleColor {
    RESET("\033[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m");

    public String ansiCode;

    ConsoleColor(String ansiCode) {
        this.ansiCode = ansiCode;
    }
}
