import java.io.File

fun readInput(file: File): Input {

    val lines = file.readLines();

    val clientsPrefs = mutableListOf<ClientPrefs>()
    for (i in 1..lines.lastIndex step 2) {

        val firstLineSplited= lines[i].splitBySpace()
        val secondLineSplited= lines[i+ 1].splitBySpace()
        val clientPrefs = ClientPrefs(
            firstLineSplited.subList(1, firstLineSplited.size),
            secondLineSplited.subList(1, secondLineSplited.size),
        )
        clientsPrefs.add(clientPrefs)
    }
    return Input(
        clientsPrefs = clientsPrefs,
    )
}

data class Input(
    val clientsPrefs: List<ClientPrefs>,
)

data class ClientPrefs(
    val likedIngredients: List<String>,
    val dislikedIngredients: List<String>,
)