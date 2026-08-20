// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                built-in-types-and-their-semantics, iterator-types -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: operator fun iterator() alone suffices without implementing Iterable type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Nat(val max: Int) { operator fun iterator(): Iterator<Int> = (0 until max).iterator() }

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in Nat(3)) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
