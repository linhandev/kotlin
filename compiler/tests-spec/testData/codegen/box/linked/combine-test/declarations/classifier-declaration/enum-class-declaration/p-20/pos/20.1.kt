// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 20 -> sentence 20
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: enum entries size is independent of when exhaustiveness
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(): Int = E.entries.size + when (E.A) {
    E.A -> 1
    E.B -> 2
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
