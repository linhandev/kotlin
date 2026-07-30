// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 12 -> sentence 12
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: else covers null and remaining cases for nullable enum when
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(e: E?): String = when (e) {
    E.A -> "a"
    else -> "other"
}

fun box(): String {
    if (test(E.A) != "a") return "NOK"
    if (test(E.B) != "other") return "NOK"
    if (test(null) != "other") return "NOK"
    return "OK"
}
