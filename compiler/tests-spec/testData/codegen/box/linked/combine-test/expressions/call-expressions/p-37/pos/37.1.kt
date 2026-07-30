// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 37 -> sentence 37
 *                type-inference, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: explicit type argument supplements uninferrable type parameter
 */

// TESTCASE NUMBER: 1
fun <T> empty(): List<T> = emptyList()

fun box(): String {
    if (empty<Int>() != listOf<Int>()) return "NOK"
    if (empty<String>() != listOf<String>()) return "NOK"
    return "OK"
}
