// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: also on companion object expression sets a var
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        var x = 0
    }
}

fun case_1() {
    checkSubtype<Int>(Box.also { it.x = 42 }.x)
}
