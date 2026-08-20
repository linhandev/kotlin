// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 84 -> sentence 84
 *                declarations, property-declaration -> paragraph 84 -> sentence 84
 * NUMBER: 1
 * DESCRIPTION: internal primary constructor callable in same module
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Secret internal constructor(val code: Int)

fun case1() {
    checkSubtype<Secret>(Secret(42))
}

