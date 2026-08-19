// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: when branch block returns the last expression value
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(e: E): Int = when (e) {
    E.A -> {
        val t = 1
        t
    }
    E.B -> 2
}

fun box(): String {
    if (test(E.A) != 1) return "NOK"
    if (test(E.B) != 2) return "NOK"
    return "OK"
}
