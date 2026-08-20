// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 37 -> sentence 37
 *                type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 37 -> sentence 37
 *                type-system, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: List of value class elements unpacks correctly after generic storage type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56237(val raw: Int)

// TESTCASE NUMBER: 1
fun case_1() {
    val xs: List<UserId56237> = listOf(UserId56237(3))
    checkSubtype<Int>(xs.first().raw)
}
