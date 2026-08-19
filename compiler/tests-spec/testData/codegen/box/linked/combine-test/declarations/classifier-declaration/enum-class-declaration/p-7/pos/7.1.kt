// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: comma merges multiple enum constants into one when branch
 */

// TESTCASE NUMBER: 1
enum class E { A, B, C, D }

fun test(e: E): String = when (e) {
    E.A, E.B -> "ab"
    E.C, E.D -> "cd"
}

fun box(): String {
    if (test(E.A) != "ab") return "NOK"
    if (test(E.D) != "cd") return "NOK"
    return "OK"
}
