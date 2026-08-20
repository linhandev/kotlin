// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: extension property on Companion can be accessed via ClassName
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object
}

val Box.Companion.value get() = 42

fun case_1() {
    checkSubtype<Int>(Box.value)
}
