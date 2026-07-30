// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 13 -> sentence 13
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: parameterized enum constants are still matched by constant name
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Planet(val m: Double) {
    EARTH(1.0),
    MARS(0.1)
}

fun case_1(p: Planet) {
    checkSubtype<String>(when (p) {
        Planet.EARTH -> "earth"
        Planet.MARS -> "mars"
    })
}
