// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 3
 * DESCRIPTION: CROSSINLINE token without inline function causes compile error
 */

// TESTCASE NUMBER: 1
fun brokenCross125(<!ILLEGAL_INLINE_PARAMETER_MODIFIER!>crossinline<!> block: () -> String): String = block()

fun case1(): String = "OK"
