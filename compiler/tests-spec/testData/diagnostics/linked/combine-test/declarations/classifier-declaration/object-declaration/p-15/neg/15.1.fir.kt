// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: non-fun interface cannot be constructed with a lambda
 */

// TESTCASE NUMBER: 1
interface Op {
    fun eval(): Int
}

fun case_1(): Int = <!INTERFACE_AS_FUNCTION!>Op<!> { 1 }.<!UNRESOLVED_REFERENCE!>eval<!>()
