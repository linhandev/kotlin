// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: companion object members are not inherited by subclasses
 */

// TESTCASE NUMBER: 1
open class Base {
    companion object {
        fun foo(): String = "base"
    }
}

class Derived : Base()

fun case_1() = Derived.<!UNRESOLVED_REFERENCE!>foo<!>()
