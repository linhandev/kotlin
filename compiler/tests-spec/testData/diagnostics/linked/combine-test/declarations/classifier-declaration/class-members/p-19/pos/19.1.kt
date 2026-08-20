// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 19 -> sentence 19
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: class member operator fun contains in expression infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val items: List<Int>) {
    operator fun contains(item: Int) = item in items
}

fun case1() {
    checkSubtype<Boolean>(2 in Box(listOf(1, 2, 3)))
}
