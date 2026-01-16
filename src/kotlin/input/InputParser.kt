package src.kotlin.input

import src.kotlin.map.Position
import java.util.*

class InputParser {
    private val scanner = Scanner(System.`in`)

    fun number(range: Int): Int {
        var decision: Int

        println("--------------------------->enter a number to decide\n")
        while (true) {
            try {
                decision = scanner.nextInt()
                if (decision <= range) {
                    return decision
                } else {
                    println("Please only enter a number within the decision range.\n")
                    scanner.next()
                }
            } catch (e: Exception) {
                println("Please only enter numbers.\n")
                scanner.next()
            }
        }
    }

    fun position(): Position {
        println("------------------------------------------------------------------------------------------------------------------------------------\n")
        println("You can now input a location you would like to move to.\n")
        while (true) {
            try {
                println("Please enter a target row:\n")
                val targetRow = scanner.nextByte()

                println("\nPlease enter a target col:\n")
                val targetCol = scanner.nextByte()

                return Position(targetRow, targetCol)
            } catch (e: java.lang.Exception) {
                println("Please only enter numbers.\n")
                scanner.next()
            }
        }
    }
}