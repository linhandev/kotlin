// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: non-functional interface cannot use SAM lambda syntax
 */

// TESTCASE NUMBER: 1
interface Op {
    fun eval(): Int
}

fun case_1() {
    val bad = <!RESOLUTION_TO_CLASSIFIER!>Op<!> { 1 }
}
