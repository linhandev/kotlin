// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 10 -> sentence 10
 *                declarations, function-declaration, extension-function-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: unbound extension function reference String::tag infers function type (String) -> String with receiver as first parameter, verifying type inference
 * HELPERS: checkType
 */

fun String.tag(): String = "[$this]"

// TESTCASE NUMBER: 1
fun case1() {
    val f: (String) -> String = String::tag
    checkSubtype<(String) -> String>(f)
}
