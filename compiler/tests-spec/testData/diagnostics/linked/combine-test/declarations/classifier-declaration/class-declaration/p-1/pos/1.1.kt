// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: generic class constructor infers type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test(): Box<Int> = Box(1)

fun case1() {
    checkSubtype<Box<Int>>(test())
}
