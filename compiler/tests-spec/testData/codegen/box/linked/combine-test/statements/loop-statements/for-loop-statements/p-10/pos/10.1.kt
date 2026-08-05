// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                built-in-types-and-their-semantics, iterator-types -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: 仅提供 operator fun iterator()（不必实现 Iterable）
 */

// TESTCASE NUMBER: 1
class Nat(val max: Int) { operator fun iterator(): Iterator<Int> = (0 until max).iterator() }

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in Nat(3)) s += x; return s }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
