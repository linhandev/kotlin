// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 86 -> sentence 86
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 86 -> sentence 86
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: nullable var identity === infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box

fun case1() {
    val a: Box? = Box()
    val b: Box? = a
    val c: Box? = Box()
    checkSubtype<Boolean>(a === b)
    checkSubtype<Boolean>(a === c)
}
