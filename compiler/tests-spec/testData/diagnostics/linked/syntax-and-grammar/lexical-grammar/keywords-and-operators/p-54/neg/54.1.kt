// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: THIS_AT token without label this@ causes parser error
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        fun broken() = this<!SYNTAX!>@<!>
    }
}

fun case1(): String {
    return <!TYPE_MISMATCH!>Outer().Inner().broken()<!>
}
