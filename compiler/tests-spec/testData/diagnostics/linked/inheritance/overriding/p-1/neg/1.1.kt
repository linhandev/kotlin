// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, overriding -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: five invalid overrides: foo without override; String bar return; val over var; protected override of public g; private open h
 */

// TESTCASE NUMBER: 1
open class MissingOverrideBase540 {
    open fun foo(): Int = 1
}

class MissingOverride540 : MissingOverrideBase540() {
    fun <!VIRTUAL_MEMBER_HIDDEN!>foo<!>(): Int = 2
}

// TESTCASE NUMBER: 2
open class ReturnBase540 {
    open fun bar(): Int = 1
}

class BadReturn540 : ReturnBase540() {
    override fun bar(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = ""
}

// TESTCASE NUMBER: 3
open class VarBase540 {
    open var prop: Any = 1
}

class ValOverridesVar540 : VarBase540() {
    override <!VAR_OVERRIDDEN_BY_VAL!>val<!> prop: Any = 2
}

// TESTCASE NUMBER: 4
open class PublicVisBase540 {
    open fun g(): Int = 1
}

class StrongerVis540 : PublicVisBase540() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>protected<!> override fun g(): Int = 2
}

// TESTCASE NUMBER: 5
open class PrivateOpenBase540 {
    <!INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>open<!> fun h() {}
}
