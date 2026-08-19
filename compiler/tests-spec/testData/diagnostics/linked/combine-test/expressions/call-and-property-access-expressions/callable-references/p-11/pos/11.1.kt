// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 11 -> sentence 11
 *                declarations, function-declaration, extension-function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: unbound extension function reference String::twice infers function type (String) -> String with receiver as first parameter, verifying that the receiver type participates in the function type
 * HELPERS: checkType
 */

fun String.twice(): String = this + this

// TESTCASE NUMBER: 1
fun case1() {
    val f: (String) -> String = String::twice
    checkSubtype<(String) -> String>(f)
}
