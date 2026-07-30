// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: subject-less when can compare enum values with ==
 */

// TESTCASE NUMBER: 1
enum class E { ON, OFF }

fun test(e: E): String = when {
    e == E.ON -> "on"
    e == E.OFF -> "off"
    else -> "x"
}

fun box(): String {
    if (test(E.ON) != "on") return "NOK"
    if (test(E.OFF) != "off") return "NOK"
    return "OK"
}
