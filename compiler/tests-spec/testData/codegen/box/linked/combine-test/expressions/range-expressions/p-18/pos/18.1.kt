// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: Long in IntRange via overloaded contains
 */

// TESTCASE NUMBER: 1
val r = 1..10

fun test(x: Long): Boolean = x in r

fun box(): String {
    if (!test(5L)) return "NOK"
    if (test(11L)) return "NOK"
    if (!test(1L)) return "NOK"
    if (!test(10L)) return "NOK"
    return "OK"
}
