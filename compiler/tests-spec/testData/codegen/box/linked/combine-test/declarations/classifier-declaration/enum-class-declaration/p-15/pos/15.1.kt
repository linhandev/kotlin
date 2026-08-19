// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: enum members are accessible inside when branches
 */

// TESTCASE NUMBER: 1
enum class E(val code: Int) {
    A(1),
    B(2)
}

fun test(e: E): Int = when (e) {
    E.A -> e.code
    E.B -> e.code
}

fun box(): String {
    if (test(E.A) != 1) return "NOK"
    if (test(E.B) != 2) return "NOK"
    return "OK"
}
