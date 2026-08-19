// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: when expression branch with in operator invoking custom contains operator convention
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun contains(x: Int): Boolean = x > 0
}

fun test(x: Int): String = when (x) {
    in Box() -> "positive"
    else -> "other"
}

fun box(): String {
    if (test(5) != "positive") return "NOK"
    if (test(1) != "positive") return "NOK"
    if (test(0) != "other") return "NOK"
    if (test(-3) != "other") return "NOK"
    return "OK"
}
