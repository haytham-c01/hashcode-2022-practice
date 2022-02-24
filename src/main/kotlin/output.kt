import java.io.File

fun File.saveOutput(output: Output) {
    this.printWriter().apply {
        // print the output to file here

        print(output.ingredients.size)
        print(" ")
        print(output.ingredients.spaceSeparatedString())

    }.flush()
}

data class Output(
    val ingredients:List<String>
)


