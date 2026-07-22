// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, reference-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: null === null; Any() !== null; Any() === null is false
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(null === null)) return "NOK"
    if (Any() === null) return "NOK"
    if (!(Any() !== null)) return "NOK"
    return "OK"
}
