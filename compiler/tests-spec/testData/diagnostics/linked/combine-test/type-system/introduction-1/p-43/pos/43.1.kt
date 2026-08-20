// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 43 -> sentence 43
 *                expressions, equality-expressions -> paragraph 43 -> sentence 43
 *                type-system, introduction-1 -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: value class equality through Any channel keeps value equality type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56243(val raw: Int)

fun eq56243(a: Any, b: Any): Boolean = a == b

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Boolean>(eq56243(UserId56243(1), UserId56243(1)))
}
