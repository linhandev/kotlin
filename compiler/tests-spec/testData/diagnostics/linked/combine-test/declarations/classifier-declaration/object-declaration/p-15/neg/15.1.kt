// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: ordinary interface requires an object implementation and cannot be constructed with a lambda
 */

// TESTCASE NUMBER: 1
interface Op {
    fun eval(): Int
}

object NamedOp : Op {
    override fun eval(): Int = 1
}

fun case_1(): Int = <!RESOLUTION_TO_CLASSIFIER!>Op<!> { 1 }.<!DEBUG_INFO_MISSING_UNRESOLVED!>eval<!>()
