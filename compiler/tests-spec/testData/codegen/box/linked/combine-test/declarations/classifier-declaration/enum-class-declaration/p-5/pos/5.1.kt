// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: exhaustive enum when without else requires compatible branch types
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(e: E): Int = when (e) {
    E.A -> 1
    E.B -> 2
}

fun box(): String {
    if (test(E.A) != 1) return "NOK"
    if (test(E.B) != 2) return "NOK"
    return "OK"
}
