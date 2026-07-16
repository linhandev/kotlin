// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 58 -> sentence 58
 * NUMBER: 2
 * DESCRIPTION: Incomplete property annotation @property without colon causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenProperty58 {
    @property<!SYNTAX!><!> Suppress("WARNING")
    val x = 1
}

fun case1(): String {
    return "OK"
}
