class Processor {

    private val ingradientsScores = mutableMapOf<String, Int>()
    private var guruanteedClients = mutableSetOf<Int>();
    private lateinit var input: Input
    private val selectedIngredients = mutableSetOf<String>()

    fun process(input: Input): Output {
        this.input = input

        input.clientsPrefs.forEach {
            it.likedIngredients.forEach { likedIngredient ->
                ingradientsScores[likedIngredient] = ingradientsScores[likedIngredient]?.plus(1) ?: 0
            }

            it.dislikedIngredients.forEach { dislikedIngredient ->
                ingradientsScores[dislikedIngredient] = ingradientsScores[dislikedIngredient]?.minus(1) ?: 0
            }
        }


        val sortedIngredeients = ingradientsScores
            .toList()
            .sortedBy { it.second }
            .toMap()
            .keys


        sortedIngredeients.forEach { ingredient ->
            if (addToSelected(ingredient)) {
                selectedIngredients.add(ingredient)
                updateGuruanteedClient()
            }
        }

        return Output(selectedIngredients.toList())
    }

    private fun updateGuruanteedClient() {
        input.clientsPrefs.forEachIndexed { index, clientPrefs ->
            val isGuruanteedClient =
                selectedIngredients.containsAll(clientPrefs.likedIngredients) && clientPrefs.dislikedIngredients.firstOrNull {
                    selectedIngredients.contains(it)
                } == null

            if (isGuruanteedClient) {
                guruanteedClients.add(index)
            }
        }
    }

    private fun addToSelected(ingredient: String): Boolean {
        return guruanteedClients.isEmpty() || !willLoseClient(ingredient)
    }

    private fun willLoseClient(ingredient: String): Boolean {
        guruanteedClients.forEach { clientIndex ->
            val dislikeThisIngredient = input.clientsPrefs[clientIndex].dislikedIngredients.contains(ingredient)
            if (dislikeThisIngredient) {
                return true
            }
        }

        return false
    }

}


