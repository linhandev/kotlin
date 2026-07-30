// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 3 -> sentence 3
 *                declarations, destructuring-declarations -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: componentN order matches primary constructor parameter order
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Tag(val id: Int, val name: String)

fun case_1(t: Tag) {
    checkSubtype<Int>(t.component1())
    checkSubtype<String>(t.component2())
}
