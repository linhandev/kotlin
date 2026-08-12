// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 3 -> sentence 3
 *                operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: String and CharSequence can be iterated character-by-character in for-in
 */

// TESTCASE NUMBER: 1
fun test(): String { val sb = StringBuilder(); for (c in "ab") sb.append(c); return sb.toString() }

fun box(): String {
    if (test() != "ab") return "NOK"
    return "OK"
}
