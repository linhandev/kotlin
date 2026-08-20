// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: nested class in companion is constructed via Outer.Companion.Inner
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    companion object {
        class Inner(val x: Int)
    }
}

fun case_1() {
    checkSubtype<Int>(Outer.Companion.Inner(42).x)
}
