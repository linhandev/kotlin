// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: non-operator member rangeTo does not participate in ..
 */

// TESTCASE NUMBER: 1
data class N(val v: Int) {
    fun rangeTo(o: N): IntRange = 0..0
}

fun test() = N(1) <!OPERATOR_MODIFIER_REQUIRED!>..<!> N(2)
