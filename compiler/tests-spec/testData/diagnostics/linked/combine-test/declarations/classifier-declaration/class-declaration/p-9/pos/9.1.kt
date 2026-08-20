// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: out variance allows OutBox Int as OutBox Number
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class OutBox<out T>(val value: T)

fun test(): OutBox<Number> = OutBox<Int>(1)

fun case1() {
    checkSubtype<OutBox<Number>>(test())
}
