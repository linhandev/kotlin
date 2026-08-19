// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 80 -> sentence 80
 *                declarations, classifier-declaration, value-class-declaration -> paragraph 80 -> sentence 80
 * NUMBER: 1
 * DESCRIPTION: value class equals false for different underlying values
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Value(val x: Int)

fun test(): Boolean = Value(42) == Value(10)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
