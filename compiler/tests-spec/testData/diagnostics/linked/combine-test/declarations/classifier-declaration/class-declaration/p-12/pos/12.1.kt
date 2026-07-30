// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: star projection Box star value is Any question
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test(b: Box<*>): Any? = b.value

fun case1() {
    checkSubtype<Any?>(test(Box<String>("value")))
}
