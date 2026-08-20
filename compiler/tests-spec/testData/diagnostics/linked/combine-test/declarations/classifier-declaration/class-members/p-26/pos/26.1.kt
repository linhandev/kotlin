// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 26 -> sentence 26
 *                expressions, comparison-expressions -> paragraph 26 -> sentence 26
 *                expressions, conditional-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: compareTo in conditional expression infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun compareTo(other: Vector) = x.compareTo(other.x)
}

fun case1(v: Vector) {
    checkSubtype<String>(if (v > Vector(5)) "big" else "small")
}
