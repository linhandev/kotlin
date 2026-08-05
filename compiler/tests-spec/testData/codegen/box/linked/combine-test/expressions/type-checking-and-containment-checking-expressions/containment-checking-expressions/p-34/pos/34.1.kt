// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 34 -> sentence 34
 *                built-in-types-and-their-semantics, iterator-types -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: in operator on String checks Char membership via CharSequence contains at runtime
 */

// TESTCASE NUMBER: 1
fun test(c: Char, s: String): Boolean = c in s

fun box(): String {
    if (!test('a', "abc")) return "NOK: char present"
    if (test('d', "abc")) return "NOK: char absent"
    if (!test('b', "abc")) return "NOK: middle char present"
    if (test('a', "abc") != "abc".contains('a')) return "NOK: in not equivalent to contains for present char"
    if (test('z', "abc") != "abc".contains('z')) return "NOK: in not equivalent to contains for absent char"
    return "OK"
}
