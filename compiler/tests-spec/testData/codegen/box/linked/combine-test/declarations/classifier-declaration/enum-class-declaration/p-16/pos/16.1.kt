// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 16 -> sentence 16
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: exhaustive when result can be assigned to a val
 */

// TESTCASE NUMBER: 1
enum class E { X, Y }

fun test(e: E): String {
    val s = when (e) {
        E.X -> "x"
        E.Y -> "y"
    }
    return s
}

fun box(): String {
    if (test(E.X) != "x") return "NOK"
    if (test(E.Y) != "y") return "NOK"
    return "OK"
}
