// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 *                expressions, comparison-expressions -> paragraph 2 -> sentence 2
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Char rangeTo with in containment
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 'b' in 'a'..'c' && 'd' !in 'a'..'c'

fun box(): String {
    if (!test()) return "NOK"
    if ('a' !in 'a'..'c') return "NOK"
    if ('c' !in 'a'..'c') return "NOK"
    return "OK"
}
