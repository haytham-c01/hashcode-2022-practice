import java.io.File

private val baseDirectory = "resources${File.separator}"
private val inDirectory = "${baseDirectory}in${File.separator}"
private val outDirectory = "${baseDirectory}out${File.separator}"

private val inputFiles = listOf(
    File("${inDirectory}a_an_example.in.txt"),
    File("${inDirectory}b_basic.in.txt"),
    File("${inDirectory}c_coarse.in.txt"),
    File("${inDirectory}d_difficult.in.txt"),
    File("${inDirectory}e_elaborate.in.txt"),
)

private val outputFiles = listOf(
    File("${outDirectory}a.out"),
    File("${outDirectory}b.out"),
    File("${outDirectory}c.out"),
    File("${outDirectory}d.out"),
    File("${outDirectory}e.out"),
)

fun main(args: Array<String>) {
    inputFiles.forEachIndexed { index, file ->
        val startTime= System.currentTimeMillis()

        val input = readInput(file)
        val output = Processor().process(input)
        outputFiles[index].saveOutput(output)

        val endTime= System.currentTimeMillis()
        val elapsedTime= (endTime- startTime) /1000
        println("${file.name} -> completed in $elapsedTime seconds.")
    }
}

