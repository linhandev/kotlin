// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                expressions, prefix-expressions, unary-plus-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: class member operator fun unaryPlus in unary plus expression infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun unaryPlus() = Vector(x)
}

fun case1() {
    checkSubtype<Vector>(+Vector(5))
}
