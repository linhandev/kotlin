// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 27 -> sentence 27
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: constructor cannot be marked suspend
 */

// TESTCASE NUMBER: 1
class C56127 <!WRONG_MODIFIER_TARGET!>suspend<!> constructor()
