// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                expressions, comparison-expressions -> paragraph 27 -> sentence 27
 *                expressions, when-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: compareTo in when expression infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun compareTo(other: Vector) = x.compareTo(other.x)
}

fun case1(v: Vector) {
    checkSubtype<String>(when {
        v > Vector(5) -> "big"
        else -> "small"
    })
}
