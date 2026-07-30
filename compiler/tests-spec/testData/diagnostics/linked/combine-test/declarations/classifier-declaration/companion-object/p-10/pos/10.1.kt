// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: extension function on Companion can be called via ClassName
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object
}

fun Box.Companion.ext() = "ext"

fun case_1() {
    checkSubtype<String>(Box.ext())
}
