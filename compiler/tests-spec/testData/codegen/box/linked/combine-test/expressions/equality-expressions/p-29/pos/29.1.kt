// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: enum constants compare by identity/equality
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(): Boolean = E.A == E.A

fun box(): String {
    if (!test()) return "NOK"
    if (E.A == E.B) return "NOK"
    return "OK"
}
