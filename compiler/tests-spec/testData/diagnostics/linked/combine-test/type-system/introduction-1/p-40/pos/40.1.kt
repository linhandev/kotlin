// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 40 -> sentence 40
 *                type-system, introduction-1 -> paragraph 40 -> sentence 40
 *                expressions, cast-expressions -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: reading wrong element type from an erased generic container fails at use-site type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56240(val raw: Int)

// TESTCASE NUMBER: 1
fun case_1() {
    val xs: Any = listOf(UserId56240(1))
    checkSubtype<List<String>>(xs <!UNCHECKED_CAST!>as List<String><!>)
}
