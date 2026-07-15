// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 2
 * DESCRIPTION: Space inside LATEINIT token as late init breaks lateinit modifier lexeme
 */

// TESTCASE NUMBER: 1
class BrokenLateInit122 {
    <!SYNTAX!>late<!> init<!SYNTAX!><!> <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>var token122: String<!>
}

fun case1(): String = "OK"
