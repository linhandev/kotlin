// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 51 -> sentence 51
 *                syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: nested generic call type arguments with angle bracket syntax are correctly parsed
 */

// TESTCASE NUMBER: 1
fun <T> box(x: T): List<T> = listOf(x)

fun box(): String {
    val result = box<List<Int>>(listOf(1))
    if (result !is List<*>) return "NOK"
    if (result != listOf(listOf(1))) return "NOK"

    val result2 = box<List<String>>(listOf("a", "b"))
    if (result2 != listOf(listOf("a", "b"))) return "NOK"

    return "OK"
}
