// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 81 -> sentence 81
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 81 -> sentence 81
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 81 -> sentence 81
 * NUMBER: 1
 * DESCRIPTION: distinct instances with equal property values are not ===
 */

// TESTCASE NUMBER: 1

class Box(val x: Int)

fun test(): Boolean = Box(42) === Box(42)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
