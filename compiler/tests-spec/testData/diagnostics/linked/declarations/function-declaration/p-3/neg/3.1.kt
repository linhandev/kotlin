// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: override with mismatched parameter type is not an override; override with mismatched return type is rejected
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun foo(x: Int): String = ""
}

class BadParamType : Base() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun foo(x: String): String = ""
}

// TESTCASE NUMBER: 2
open class ReturnBase {
    open fun bar(): Int = 1
}

class BadReturnType : ReturnBase() {
    override fun bar(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = ""
}
