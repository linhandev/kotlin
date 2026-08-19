// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 38 -> sentence 38
 *                type-inference, introduction-1 -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: assignment and return contexts provide expected type for downward inference of type arguments
 */

// TESTCASE NUMBER: 1
fun <T> empty(): List<T> = emptyList()

fun box(): String {
    val a: List<String> = empty()
    if (a != listOf<String>()) return "NOK"

    val b: List<Int> = empty()
    if (b != listOf<Int>()) return "NOK"

    return "OK"
}
