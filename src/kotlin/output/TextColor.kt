package src.kotlin.output

enum class TextColor(val code: String) {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    GRAY("\u001B[37m"),

    HIGH_RED("\u001B[0;91m"),
    HIGH_GREEN("\u001B[0;92m"),
    HIGH_YELLOW("\u001B[0;93m"),
    HIGH_BLUE("\u001B[0;94m"),
    HIGH_MAGENTA("\u001B[0;95m"),
    HIGH_CYAN("\u001B[0;96m"),
}