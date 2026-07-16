// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: platform restrictions forbid inlining open or abstract members
 */

// TESTCASE NUMBER: 1
open class OpenInlineRestriction {
    <!DECLARATION_CANT_BE_INLINED!>inline<!> open fun wrong() {}
}

// TESTCASE NUMBER: 2
interface InlineInterfaceRestriction {
    <!DECLARATION_CANT_BE_INLINED!>inline<!> fun wrong()
}
