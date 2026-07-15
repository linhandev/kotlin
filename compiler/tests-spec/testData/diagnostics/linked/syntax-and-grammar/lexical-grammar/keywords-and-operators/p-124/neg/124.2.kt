// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 124 -> sentence 124
 * NUMBER: 2
 * DESCRIPTION: Space inside NOINLINE token as no inline breaks noinline modifier lexeme
 */

// TESTCASE NUMBER: 1
inline fun brokenNoinline124(<!VALUE_PARAMETER_WITH_NO_TYPE_ANNOTATION!>no<!><!SYNTAX!><!> <!WRONG_MODIFIER_TARGET!>inline<!> block: () -> String): String = block()

fun case1(): String = "OK"
