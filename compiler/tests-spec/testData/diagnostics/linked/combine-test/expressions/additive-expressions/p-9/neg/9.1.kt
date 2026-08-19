// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: plus return type mismatch: String result has no property v
 */

// TESTCASE NUMBER: 1
data class A(val v: Int) {
    operator fun plus(b: B): String = "x"
}

data class B(val v: Int)

fun case_1(): Int = (A(1) + B(2)).<!UNRESOLVED_REFERENCE!>v<!>
