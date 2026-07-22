// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, reference-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: non-equal literal values are non-equal by reference
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (1 === 2) return "NOK"
    if (!(1 !== 2)) return "NOK"
    return "OK"
}
