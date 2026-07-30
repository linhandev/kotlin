/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: internal top-level property accessible in same module
 */

// TESTCASE NUMBER: 1
internal val x: Int = 42

fun test() = x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
