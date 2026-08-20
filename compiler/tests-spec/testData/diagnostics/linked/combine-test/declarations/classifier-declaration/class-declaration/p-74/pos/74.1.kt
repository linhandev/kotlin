// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 74 -> sentence 74
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 74 -> sentence 74
 * NUMBER: 1
 * DESCRIPTION: data class primary constructor vals participate in equals/hashCode
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Point(val x: Int, val y: Int)

fun test(): Boolean = Point(1, 2) == Point(1, 2)

fun case1() {
    checkSubtype<Boolean>(test())
}
