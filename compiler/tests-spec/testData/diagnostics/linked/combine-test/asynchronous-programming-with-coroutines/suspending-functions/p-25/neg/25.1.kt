// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 25 -> sentence 25
 *                declarations, property-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: property cannot be declared as suspend val
 */

// TESTCASE NUMBER: 1
class C56125 {
    <!WRONG_MODIFIER_TARGET!>suspend<!> val x56125: Int = 1
}
