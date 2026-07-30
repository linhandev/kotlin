// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 62 -> sentence 62
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 62 -> sentence 62
 *                type-inference, introduction-1 -> paragraph 62 -> sentence 62
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 62 -> sentence 62
 * NUMBER: 1
 * DESCRIPTION: generic choose returns the first argument when both arguments share a type, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun <T> choose(a: T, b: T): T = a

fun box(): String {
    if (choose(1, 2) != 1) return "NOK"
    if (choose("a", "b") != "a") return "NOK"
    return "OK"
}
