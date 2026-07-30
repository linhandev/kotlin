// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 55 -> sentence 55
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 55 -> sentence 55
 *                declarations, declaration-site-variance-and-use-site-variance -> paragraph 55 -> sentence 55
 * NUMBER: 1
 * DESCRIPTION: covariant out-projected type argument in function parameter accepts subtype argument
 */

// TESTCASE NUMBER: 1
fun sum(ns: List<out Number>): Double = ns.sumOf { it.toDouble() }

fun box(): String {
    if (sum(listOf(1, 2)) != 3.0) return "NOK"
    if (sum(listOf(1.5, 2.5)) != 4.0) return "NOK"
    if (sum(listOf(1, 2L, 3.0)) != 6.0) return "NOK"
    return "OK"
}
