// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension function declaration is callable on matching receivers at runtime
 */

// TESTCASE NUMBER: 1
fun String.lastChar(): Char = this[length - 1]

fun <T> List<T>.head(): T? = if (isEmpty()) null else first()

fun box(): String {
    val last = "abc".lastChar()
    val head = listOf(1, 2, 3).head()
    return if (last == 'c' && head == 1) "OK" else "NOK last=$last head=$head"
}
