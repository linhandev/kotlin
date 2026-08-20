// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: internal top-level property accessible in same module
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
internal val x: Int = 42

fun case_1() {
    checkSubtype<Int>(x)
}
