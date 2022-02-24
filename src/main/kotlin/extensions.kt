

fun String.splitBySpace(): List<String> = split(" ")
fun <T> List<T>.spaceSeparatedString():String = joinToString(separator = " ")
fun List<String>.getAsInt(index: Int):Int = get(index).toInt()