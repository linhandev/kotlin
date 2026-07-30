// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 79 -> sentence 79
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 79 -> sentence 79
 *                declarations, classifier-declaration, value-class-declaration -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: value class equals true for equal underlying values
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Value(val x: Int)

fun test(): Boolean = Value(42) == Value(42)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
