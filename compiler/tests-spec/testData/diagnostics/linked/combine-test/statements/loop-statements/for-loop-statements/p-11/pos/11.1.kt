// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 *                built-in-types-and-their-semantics, iterator-types -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: iterator() returns object with operator hasNext/next type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Nat(val max: Int) { operator fun iterator() = It(max); class It(private val max: Int) { var i = 0; operator fun hasNext(): Boolean = i < max; operator fun next(): Int = i++ } }

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in Nat(3)) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
