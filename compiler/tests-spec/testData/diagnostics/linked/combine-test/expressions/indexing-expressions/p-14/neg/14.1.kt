// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: non-operator get does not participate in indexing
 */

// TESTCASE NUMBER: 1
class Box(val data: IntArray) {
    fun get(i: Int): Int = data[i]
}

fun test() = <!OPERATOR_MODIFIER_REQUIRED!>Box(intArrayOf(1))[0]<!>
