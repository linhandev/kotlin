// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 2
 * DESCRIPTION: Space inside CROSSINLINE token as cross inline breaks crossinline modifier lexeme
 */

// TESTCASE NUMBER: 1
inline fun brokenCross125(<!VALUE_PARAMETER_WITH_NO_TYPE_ANNOTATION!>cross<!><!SYNTAX!><!> <!WRONG_MODIFIER_TARGET!>inline<!> block: () -> String): String = block()

fun case1(): String = "OK"
