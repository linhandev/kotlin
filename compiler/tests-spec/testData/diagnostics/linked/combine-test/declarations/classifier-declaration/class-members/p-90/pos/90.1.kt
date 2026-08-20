// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 90 -> sentence 90
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 90 -> sentence 90
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 90 -> sentence 90
 * NUMBER: 1
 * DESCRIPTION: equals and hashCode consistency types as Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

data class Data(val x: Int)

fun case1(a: Data, b: Data) {
    checkSubtype<Boolean>((a == b) && (a.hashCode() == b.hashCode()))
    checkSubtype<Boolean>(a == b)
}
