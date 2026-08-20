// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: unbounded type parameter allows Any members like toString
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class AnyBox<T>(val v: T) { fun s(): String = v.toString() }

fun test(): String = AnyBox(1).s()

fun case1() {
    checkSubtype<String>(test())
}
