// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: false || evaluates side-effecting throw call
 */

// TESTCASE NUMBER: 1
var n = 0
fun boom(): Nothing {
    n++
    throw IllegalStateException()
}

fun test(): Boolean = false || boom()

fun box(): String {
    try {
        test()
        return "NOK: no throw"
    } catch (_: IllegalStateException) {
        if (n != 1) return "NOK: side-effect"
        return "OK"
    }
}
