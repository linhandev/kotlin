// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 25 -> sentence 25
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: if-else chain can cover all enum constants equivalently to when
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(e: E): Int = if (e == E.A) 1 else if (e == E.B) 2 else error("x")

fun box(): String {
    if (test(E.A) != 1) return "NOK"
    if (test(E.B) != 2) return "NOK"
    return "OK"
}
