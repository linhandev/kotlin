// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 3
 * DESCRIPTION: Incomplete package declaration PACKAGE without qualified name causes parser error
 */

// TESTCASE NUMBER: 1
package<!SYNTAX!><!>

fun case1(): String {
    return "OK"
}
