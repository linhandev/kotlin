// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: recursive upper bound Comparable T
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Max<T : Comparable<T>>(val a: T, val b: T) { fun max(): T = if (a >= b) a else b }

fun test(): Int = Max(1, 2).max()

fun case1() {
    checkSubtype<Int>(test())
}
