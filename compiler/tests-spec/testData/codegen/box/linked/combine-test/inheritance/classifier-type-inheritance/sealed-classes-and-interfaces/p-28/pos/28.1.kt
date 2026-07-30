// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 28 -> sentence 28
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: sealed interface with data object and data class is exhaustively matchable
 */

// TESTCASE NUMBER: 1
sealed interface Status
data object Ok : Status
data class Fail(val reason: String) : Status

fun test(s: Status): String = when (s) {
    is Ok -> "ok"
    is Fail -> s.reason
}

fun box(): String {
    if (test(Ok) != "ok") return "NOK"
    if (test(Fail("x")) != "x") return "NOK"
    return "OK"
}
