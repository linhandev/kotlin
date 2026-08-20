// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 82 -> sentence 82
 *                declarations, property-declaration -> paragraph 82 -> sentence 82
 * NUMBER: 1
 * DESCRIPTION: primary constructor param usable in other property initializers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Wrap(x: Int) { val doubled = x * 2 }

fun case1() {
    checkSubtype<Int>(Wrap(3).doubled)
}

