// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 22 -> sentence 22
 *                type-system, type-kinds, type-parameters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: generic Iterable<T> preserves element type
 */

// TESTCASE NUMBER: 1
fun <T> sum(xs: Iterable<T>, f: (T) -> Int): Int { var s = 0; for (x in xs) s += f(x); return s }

// TESTCASE NUMBER: 1
fun test(): Int = sum(listOf("1", "2")) { it.toInt() }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
