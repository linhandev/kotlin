// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 56 -> sentence 56
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 56 -> sentence 56
 * NUMBER: 1
 * DESCRIPTION: out T with Number upper bound allows OutNum Number from Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class OutNum<out T : Number>(val v: T)

fun test(): OutNum<Number> = OutNum(1)

fun case1() {
    checkSubtype<OutNum<Number>>(test())
}
