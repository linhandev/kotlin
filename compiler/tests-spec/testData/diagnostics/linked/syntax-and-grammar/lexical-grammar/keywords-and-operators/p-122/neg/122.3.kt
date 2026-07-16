// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 3
 * DESCRIPTION: LATEINIT token on val property causes compile error
 */

// TESTCASE NUMBER: 1
class BrokenLateInitVal122 {
    <!INAPPLICABLE_LATEINIT_MODIFIER!>lateinit<!> val token122: String
}

fun case1(): String = "OK"
