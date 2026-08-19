// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 34 -> sentence 34
 *                built-in-types-and-their-semantics, iterator-types -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: in operator on String checks Char membership via CharSequence contains and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>('a' in "abc")
    checkSubtype<Boolean>('d' in "abc")
}
