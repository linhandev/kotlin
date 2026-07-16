// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: null == null and null comparisons use reference equality ===
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(null == null)) return "NOK"
    if (Any() == null) return "NOK"
    if (!(null != Any())) return "NOK"
    return "OK"
}
