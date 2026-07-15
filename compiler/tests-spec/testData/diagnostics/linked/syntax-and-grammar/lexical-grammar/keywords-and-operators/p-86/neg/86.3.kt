// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 3
 * DESCRIPTION: Incomplete catch clause CATCH without parameter causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenCatchParam86(): String {
    try {
        return "OK"
    } catch<!SYNTAX!><!> {
        return "NOK"
    }
}

fun case1(): String = "OK"
