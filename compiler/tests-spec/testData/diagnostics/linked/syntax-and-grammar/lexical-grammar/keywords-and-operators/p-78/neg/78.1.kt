// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: THIS token at top level without receiver causes compile error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!NO_THIS!>this<!>
    return "OK"
}
