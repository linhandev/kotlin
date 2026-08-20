// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 80 -> sentence 80
 *                declarations, classifier-declaration, value-class-declaration -> paragraph 80 -> sentence 80
 * NUMBER: 1
 * DESCRIPTION: value class == with different values infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Value(val x: Int)

fun case1() {
    checkSubtype<Boolean>(Value(42) == Value(10))
}
