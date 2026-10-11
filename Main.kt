import kotlin.random.Random

enum class Move(val label: String) {
    ROCK("Rock"),
    PAPER("Paper"),
    SCISSORS("Scissors");

    fun beats(other: Move): Boolean = when (this) {
        ROCK -> other == SCISSORS
        PAPER -> other == ROCK
        SCISSORS -> other == PAPER
    }

    companion object {
        fun random(): Move = values()[Random.nextInt(values().size)]

        fun fromInput(text: String): Move? = when (text) {
            "1", "r", "rock" -> ROCK
            "2", "p", "paper" -> PAPER
            "3", "s", "scissors" -> SCISSORS
            else -> null
        }
    }
}

class Score {
    var wins = 0
    var losses = 0
    var draws = 0

    val rounds: Int get() = wins + losses + draws

    fun show() {
        println("Score -> Wins: $wins | Losses: $losses | Draws: $draws")
    }
}

fun playRound(user: Move, computer: Move, score: Score) {
    println("You: ${user.label} | Computer: ${computer.label}")
    when {
        user == computer -> {
            score.draws++
            println("Draw!")
        }
        user.beats(computer) -> {
            score.wins++
            println("You win!")
        }
        else -> {
            score.losses++
            println("You lose!")
        }
    }
    score.show()
}

fun main() {
    val score = Score()
    println("=== Rock Paper Scissors ===")

    while (true) {
        print("\nYour move (r = Rock, p = Paper, s = Scissors, q = Quit): ")
        val text = readLine()?.trim()?.lowercase() ?: break

        if (text == "q") break

        val user = Move.fromInput(text)
        if (user == null) {
            println("Invalid input, please try again.")
            continue
        }

        playRound(user, Move.random(), score)
    }

    println("\n=== Final Result ===")
    println("Rounds played: ${score.rounds}")
    score.show()
    println("Thanks for playing!")
}
