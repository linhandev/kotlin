// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: in operator prefers local Int contains extension over package-level contains at runtime
 */

// TESTCASE NUMBER: 1
operator fun Int.contains(other: Int): Boolean = this <= other

fun test(x: Int): Boolean {
    operator fun Int.contains(other: Int): Boolean = this > other
    return 5 in x
}

fun box(): String {
    if (!test(10)) return "NOK"
    if (test(3)) return "NOK"
    if (!test(6)) return "NOK"
    if (test(5)) return "NOK"
    return "OK"
}
