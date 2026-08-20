// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 370 -> sentence 370
 * declarations, declaration-visibility -> paragraph 370 -> sentence 370
 * declarations, property-declaration -> paragraph 370 -> sentence 370
 * NUMBER: 1
 * DESCRIPTION: internal val is accessible outside class within module unlike private type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { internal val code = 42 }

// TESTCASE NUMBER: 1
fun test(): Int = C().code

fun case1() {
    checkSubtype<Int>(test())
}
