// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: companion object members cannot override another companion
 */

// TESTCASE NUMBER: 1
open class Base {
    companion object {
        fun foo(): String = "base"
    }
}

class Derived : Base() {
    companion object {
        <!NOTHING_TO_OVERRIDE!>override<!> fun foo(): String = "derived"
    }
}

fun case_1() = Derived.foo()
