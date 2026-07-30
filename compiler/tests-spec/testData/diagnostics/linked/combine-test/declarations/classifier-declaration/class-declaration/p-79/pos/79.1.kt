// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 79 -> sentence 79
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 79 -> sentence 79
 *                declarations, property-declaration -> paragraph 79 -> sentence 79
 *                declarations, declarations-with-type-parameters -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: generic primary constructor val retains type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun case1() {
    checkSubtype<Int>(Box(1).value)
}

