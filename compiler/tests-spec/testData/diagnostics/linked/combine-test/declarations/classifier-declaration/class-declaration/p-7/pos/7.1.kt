// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: where clause multiple upper bounds
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Repo<T> where T : CharSequence, T : Comparable<T> { fun len(x: T): Int = x.length }

fun test(): Int = Repo<String>().len("ab")

fun case1() {
    checkSubtype<Int>(test())
}
