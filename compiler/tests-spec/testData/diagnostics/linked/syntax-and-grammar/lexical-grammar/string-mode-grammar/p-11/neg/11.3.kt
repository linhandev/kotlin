// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 11 -> sentence 11
 * NUMBER: 3
 * DESCRIPTION: MultiLineStrRef $class hard keyword without escaping
 */

// TESTCASE NUMBER: 1
fun case1(): String = """n=$<!SYNTAX!>class<!>"""
