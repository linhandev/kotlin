// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 22 -> sentence 22
 *                inheritance, inheriting -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: suspend override cannot narrow a non-suspending open member
 */

open class B56122 {
    open fun f56122(): Int = 1
}

// TESTCASE NUMBER: 1
class D56122 : B56122() {
    <!CONFLICTING_OVERLOADS!><!NOTHING_TO_OVERRIDE!>override<!> suspend fun f56122(): Int<!> = 2
}
