// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, overriding -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: override var with String over Int base and Int over Any base report VAR_TYPE_MISMATCH_ON_OVERRIDE
 */

// TESTCASE NUMBER: 1
open class VarIntBase540 {
    open var prop: Int = 1
}

class VarStringOverride540 : VarIntBase540() {
    override var prop: <!VAR_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = ""
}

// TESTCASE NUMBER: 2
open class VarAnyBase540 {
    open var data: Any = 1
}

class VarIntOverride540 : VarAnyBase540() {
    override var data: <!VAR_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 2
}
