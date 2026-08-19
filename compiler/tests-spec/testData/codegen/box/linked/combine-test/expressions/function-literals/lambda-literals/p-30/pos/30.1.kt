// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: Sequence map destructuring matches List semantics
 */

// TESTCASE NUMBER: 1
fun test(): List<Int> =
    sequenceOf(1 to 2, 2 to 3).map { (a, b) -> a + b }.toList()

fun box(): String {
    if (test() != listOf(3, 5)) return "NOK"
    return "OK"
}
