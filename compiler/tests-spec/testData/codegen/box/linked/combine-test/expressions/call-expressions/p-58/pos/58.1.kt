// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 58 -> sentence 58
 *                type-inference, introduction-1 -> paragraph 58 -> sentence 58
 *                overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: overload resolution selects non-generic overload over generic when more specific
 */

// TESTCASE NUMBER: 1
fun f(x: Int): Int = x
fun <T> f(x: T): T = x

fun box(): String {
    if (f(1) != 1) return "NOK"
    if (f("hello") != "hello") return "NOK"
    return "OK"
}
